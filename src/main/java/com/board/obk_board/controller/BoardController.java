package com.board.obk_board.controller;

import com.board.obk_board.domain.Board;
import com.board.obk_board.domain.Comment;
import com.board.obk_board.dto.BoardDto;
import com.board.obk_board.dto.MessageDto;
import com.board.obk_board.enums.BoardMessage;
import com.board.obk_board.service.BoardService;
import com.board.obk_board.session.LoginedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;
    private LoginedUser loginedUser;

    @Autowired
    public BoardController(BoardService boardService,
                           LoginedUser loginedUser){
        this.boardService = boardService;
        this.loginedUser = loginedUser;
    }

    @GetMapping("/board")
    public String getBoard(@RequestParam("page") int page,
                                      Model model){
        String username = loginedUser.getUserName();

        BoardDto boardDto = boardService.getBoard(page);

        model.addAttribute("totalPage", boardDto.getTotalPage());
        model.addAttribute("boards", boardDto.getBoard().getContent());
        model.addAttribute("loginedUser", username);

        return "board";
    }

    @GetMapping("/board/{id}")
    public String readBoard(@PathVariable Long id, Model model){
        Board board  = boardService.selectBoard(id);
        List<Comment> commentList= board.getComment();

        model.addAttribute(board);
        model.addAttribute("comments", commentList);
        return "readBoard";
    }

    @PostMapping("/board")
    @ResponseBody
    public ResponseEntity<MessageDto.create> createBoard(@RequestBody BoardDto.create create){
        MessageDto.create msg = boardService.checkCreateError(create);

        if(!msg.getMsg().equals(BoardMessage.SUCCESS_CREATE_BOARD.getMessage()))
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/board/{id}")
    @ResponseBody
    public ResponseEntity<MessageDto.update> updateBoard(@PathVariable Long id, @RequestBody BoardDto.update update){
        MessageDto.update msg = boardService.checkUpdateError(update, id, loginedUser.getUserName());

        if(!msg.getMsg().equals(BoardMessage.SUCCESS_UPDATE_BOARD.getMessage())){
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/board/{id}")
    @ResponseBody
    public ResponseEntity<MessageDto.delete> deleteBoard(@PathVariable Long id){
        MessageDto.delete msg = boardService.checkDeleteError(id);

        if(!msg.getMsg().equals(BoardMessage.SUCCESS_DELETE_BOARD.getMessage())){
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
