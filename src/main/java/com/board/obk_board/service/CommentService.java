package com.board.obk_board.service;

import com.board.obk_board.domain.Board;
import com.board.obk_board.domain.Comment;
import com.board.obk_board.domain.User;
import com.board.obk_board.dto.CommentDto;
import com.board.obk_board.enums.CommentMessage;
import com.board.obk_board.exception.NotExistBoardException;
import com.board.obk_board.exception.NotExistCommentException;
import com.board.obk_board.repository.BoardRepository;
import com.board.obk_board.repository.CommentRepository;
import com.board.obk_board.repository.UserRepository;
import com.board.obk_board.session.LoginedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private LoginedUser loginedUser;

    @Autowired
    public CommentService(BoardRepository boardRepository,
                          CommentRepository commentRepository,
                          LoginedUser loginedUser,
                          UserRepository userRepository){
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.loginedUser = loginedUser;
        this.userRepository = userRepository;
    }


    public CommentMessage checkCreateMessage(CommentDto commentDto){
        CommentMessage msg;
        if(commentDto.getContent().equals("") || commentDto.getContent().trim().isEmpty()){
            msg = CommentMessage.EMPTY_INPUT;
        }
        else if(commentDto.getContent().indexOf("<script") == 0){ msg = CommentMessage.NOT_INPUT_JAVASCRIPT; }
        else msg = CommentMessage.SUCCESS_COMMENT;

        return msg;
    }

    @Transactional
    public Comment saveComment(CommentDto commentDto){
        Board board = boardRepository.selectBaord(commentDto.getBno());
        if(board == null){
            throw new NotExistBoardException("해당 게시판은 더 이상 게시판 존재하지 않습니다.");
        }

        User user = userRepository.findUserByEmail(loginedUser.getUserName());

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .board(board)
                .user(user)
                .build();

        return commentRepository.save(comment);
    }

    public CommentMessage checkUpdateMessage(CommentDto.update commentDto){
        CommentMessage msg;
        if(commentDto.getContent().equals("") || commentDto.getContent().trim().isEmpty()){
            msg = CommentMessage.EMPTY_INPUT;
        }
        else if(commentDto.getContent().indexOf("<script") == 0){ msg = CommentMessage.NOT_INPUT_JAVASCRIPT; }
        else if(!commentDto.getWriter().equals(loginedUser.getUserName())){ msg = CommentMessage.NOT_CORRECT_WRITER; }
        else msg = CommentMessage.SUCCESS_COMMENT;

        return msg;
    }

    @Transactional
    public Comment updateComment(Long id, CommentDto.update commentDto) {
        Board board = boardRepository.selectBaord(commentDto.getBno());
        if(board == null){
            throw new NotExistBoardException("해당 게시판은 더 이상 게시판 존재하지 않습니다.");
        }

        User user = userRepository.findUserByEmail(loginedUser.getUserName());

        Comment comment = Comment.builder()
                .seq(id)
                .content(commentDto.getContent())
                .board(board)
                .user(user)
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id){
        Comment comment = commentRepository.findCommentBySeq(id);
        if(comment == null){
            throw new NotExistCommentException("해당 댓글은 더 이상 존재하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}
