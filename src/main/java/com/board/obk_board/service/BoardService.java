package com.board.obk_board.service;

import com.board.obk_board.domain.Board;
import com.board.obk_board.domain.User;
import com.board.obk_board.dto.BoardDto;
import com.board.obk_board.dto.MessageDto;
import com.board.obk_board.enums.BoardMessage;
import com.board.obk_board.repository.BoardRepository;
import com.board.obk_board.repository.UserRepository;
import com.board.obk_board.session.LoginedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BoardService {
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    private LoginedUser loginedUser;
    @Autowired
    public BoardService(BoardRepository boardRepository,
                        UserRepository userRepository,
                        LoginedUser loginedUser) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.loginedUser = loginedUser;
    }

    public BoardDto getBoard(int page){
        BoardDto boardDto = new BoardDto();

        long first, last;

        if(boardRepository.findAll().size() == 0){
            first = 0;
            last = 0;
        }
        else {
            first = boardRepository.selectFirstIndex();
            last = boardRepository.selectLastIndex();
        }

        long totalPage = ((first + last) / 5) + 1;

        boardDto.setFirstIndex(first);
        boardDto.setLastIndex(last);
        boardDto.setTotalPage(totalPage);
        boardDto.setBoard(boardRepository.findAll(PageRequest.of(page, 5, Sort.by("seq").ascending())));

        return boardDto;
    }

    public Board selectBoard(Long id){
        return boardRepository.selectBaord(id);
    }

    public MessageDto.create checkCreateError(BoardDto.create create){
        BoardMessage msg;
        Board board = null;

        if(create.getTitle().equals("")) msg = BoardMessage.EMPTY_TITLE;
        else if(create.getContents().equals("")) msg = BoardMessage.EMPTY_CONTENT;
        else{
            msg = BoardMessage.SUCCESS_CREATE_BOARD;

            User user = userRepository.findUserByEmail(loginedUser.getUserName());

            board = Board.builder()
                    .seq(boardRepository.selectLastIndex()+1)
                    .title(create.getTitle())
                    .content(create.getContents())
                    .writer(user.getUsername())
                    .user(user)
                    .build();

            boardRepository.save(board);
        }

        return MessageDto.create.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .msg(msg.getMessage())
                .build();
    }

    public MessageDto.update checkUpdateError(BoardDto.update update, Long id, String username){
        BoardMessage msg;
        Board board = boardRepository.selectBaord(id);

        if(!board.getWriter().equals(username)) msg = BoardMessage.NOT_MATCH_WRITER;
        else if(update.getTitle().equals("")) msg = BoardMessage.EMPTY_TITLE;
        else if(update.getContents().equals("")) msg = BoardMessage.EMPTY_CONTENT;
        else{
            msg = BoardMessage.SUCCESS_UPDATE_BOARD;
            board.setTitle(update.getTitle());
            board.setContent(update.getContents());

            boardRepository.save(board);
        }

        return MessageDto.update.builder()
                .title(update.getTitle())
                .content(update.getContents())
                .msg(msg.getMessage())
                .build();
    }

    public MessageDto.delete checkDeleteError(Long id){
        BoardMessage msg;
        Board board = boardRepository.selectBaord(id);
        if(board == null)
            msg = BoardMessage.FAIL_DELETE_BOARD;
        else if(!board.getWriter().equals(loginedUser.getUserName()))
            msg = BoardMessage.NOT_MATCH_WRITER;
        else{
            msg = BoardMessage.SUCCESS_DELETE_BOARD;
            boardRepository.delete(board);
        }

        return MessageDto.delete.builder()
                .msg(msg.getMessage())
                .build();
    }
}
