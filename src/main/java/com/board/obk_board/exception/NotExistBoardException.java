package com.board.obk_board.exception;

import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotExistBoardException extends RuntimeException {
    public NotExistBoardException(String msg){
        super(msg);
    }
}
