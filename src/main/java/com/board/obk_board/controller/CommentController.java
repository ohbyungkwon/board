package com.board.obk_board.controller;

import com.board.obk_board.dto.CommentDto;
import com.board.obk_board.enums.CommentMessage;
import com.board.obk_board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    @ResponseBody
    public ResponseEntity<?> createComment(@RequestBody CommentDto commentDto){
        CommentMessage commentMessage = commentService.checkCreateMessage(commentDto);

        if(commentMessage != CommentMessage.SUCCESS_COMMENT)
            return new ResponseEntity<>(commentService, HttpStatus.BAD_REQUEST);

        commentService.saveComment(commentDto);

        return new ResponseEntity<>(commentMessage, HttpStatus.OK);
    }

    @PutMapping("/comment/{id}")
    @ResponseBody
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDto.update commentDto){
        CommentMessage commentMessage = commentService.checkUpdateMessage(commentDto);

        if(commentMessage != CommentMessage.SUCCESS_COMMENT)
            return new ResponseEntity<>(commentService, HttpStatus.BAD_REQUEST);

        commentService.updateComment(id, commentDto);

        return new ResponseEntity<>(commentMessage, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        return ResponseEntity.ok().build();
    }
}
