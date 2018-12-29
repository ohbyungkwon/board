package com.board.obk_board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CantFindUserException extends RuntimeException{
    public CantFindUserException(String msg){
        super(msg);
    }
}
