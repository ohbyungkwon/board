package com.board.obk_board.controller;

import com.board.obk_board.domain.User;
import com.board.obk_board.dto.UserDto;
import com.board.obk_board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto.createUser createUser, BindingResult bindingResult){
        User userTemp = userService.saveUser(createUser, bindingResult);

        if(userTemp == null)
            return ResponseEntity.badRequest().build();

        UserDto status = UserDto.builder()
                .isSuccess(true)
                .build();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto.updateUser updateUser,
                                              @PathVariable int id,
                                              BindingResult bindingResult){
        User userTemp = userService.updateUser(updateUser, id);

        if(userTemp == null)
            return ResponseEntity.badRequest().build();

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        UserDto status = UserDto.builder()
                .isSuccess(true)
                .build();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> deleteUser(@PathVariable int id){
        userService.deleteUser(id);

        UserDto status = UserDto.builder()
                .isSuccess(true)
                .build();

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/register")
    public String register(){
        return "Form";
    }
}
