package com.board.obk_board.service;

import com.board.obk_board.enums.SignInMessage;
import com.board.obk_board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class SignInService {
    private UserRepository userRepository;

    public SignInService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public SignInMessage checkDuplicateEmail(String userEmail, BindingResult bindingResult){
        SignInMessage msg;
        if(userRepository.findUserByEmail(userEmail) != null)
            msg = SignInMessage.DUPLICATE_EMAIL;
        else if(userEmail.trim().equals("")){
            msg = SignInMessage.EMPTY_EMAIL;
        }else if(bindingResult.hasErrors()){
            msg = SignInMessage.INCORRECT_EMAIL;
        }else{
            msg = SignInMessage.SUCCESS_EMAIL;
        }
        return msg;
    }

    public SignInMessage checkPassword(String password, BindingResult bindingResult){
        SignInMessage msg;
        if(bindingResult.hasErrors()){
            msg = SignInMessage.INCORRECT_PASSWORD;
        }else if(password.trim().equals("")){
            msg = SignInMessage.EMPTY_PASSWORD;
        }else{
            msg = SignInMessage.SUCCESS_PASSWORD;
        }
        return msg;
    }
}
