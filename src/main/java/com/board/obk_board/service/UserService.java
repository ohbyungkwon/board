package com.board.obk_board.service;

import com.board.obk_board.domain.User;
import com.board.obk_board.dto.UserDto;
import com.board.obk_board.exception.CantFindUserException;
import com.board.obk_board.exception.DuplicateUserException;
import com.board.obk_board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(UserDto.createUser userDto, BindingResult bindingResult){
        User userTemp = userRepository.findUserByEmail(userDto.getUsername());
        if(userTemp != null){
            throw new DuplicateUserException("유저 겹침");
        }
        if(bindingResult.hasErrors()){
            return null;
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .build();

        return userRepository.save(user);
    }

    public User updateUser(UserDto.updateUser userDto, int seq) {
        User userTemp = userRepository.findUserById(seq);
        if(userTemp == null){
            throw new CantFindUserException("유저 없음");
        }

        User user = User.builder()
                .seq(userTemp.getSeq())
                .username(userTemp.getUsername())
                .password(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()))
                .build();

        return userRepository.save(user);
    }

    public void deleteUser(int seq){
        User userTemp = userRepository.findUserById(seq);
        if(userTemp == null){
            throw new CantFindUserException("유저 없음");
        }

        userRepository.delete(userTemp);
    }
}
