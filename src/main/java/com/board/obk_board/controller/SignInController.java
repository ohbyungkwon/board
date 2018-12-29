package com.board.obk_board.controller;

import com.board.obk_board.dto.SignInDto;
import com.board.obk_board.enums.SignInMessage;
import com.board.obk_board.service.SignInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SignInController {
    private SignInService signInService;

    public SignInController(SignInService signInService){
        this.signInService = signInService;
    }

    @GetMapping("/signIn")
    public String signIn(){
        return "SignIn";
    }

    @PostMapping("/checkEmail")
    @ResponseBody
    public ResponseEntity<String> checkId(@Valid @RequestBody SignInDto.checkEmail checkEmail,
                                                 BindingResult bindingResult){
        SignInMessage msg = signInService.checkDuplicateEmail(checkEmail.getUserEmail(), bindingResult);
        if(msg != SignInMessage.SUCCESS_EMAIL){
            return new ResponseEntity<>(msg.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.OK);
    }

    @PostMapping("/checkPwd")
    @ResponseBody
    public ResponseEntity<String> checkId(@Valid @RequestBody SignInDto.checkPwd checkPwd,
                                     BindingResult bindingResult) {
        SignInMessage msg = signInService.checkPassword(checkPwd.getPassword(), bindingResult);
        if(msg != SignInMessage.SUCCESS_PASSWORD){
            return new ResponseEntity<>(msg.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(msg.getMessage(), HttpStatus.OK);
    }
}
