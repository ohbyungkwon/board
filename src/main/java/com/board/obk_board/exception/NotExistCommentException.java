package com.board.obk_board.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotExistCommentException extends RuntimeException {
    public NotExistCommentException(String msg){
        super(msg);
    }
}
