package com.board.obk_board.enums;

public enum BoardMessage {
    SUCCESS_CREATE_BOARD(1,"게시판 생성."),
    SUCCESS_UPDATE_BOARD(2,"게시판 수정."),
    SUCCESS_DELETE_BOARD(3,"게시판 삭제."),
    EMPTY_TITLE(4, "제목이 비었습니다."),
    EMPTY_CONTENT(5, "내용이 비었습니다."),
    NOT_MATCH_WRITER(6, "작성자가 본인이 아닌 글입니다."),
    FAIL_DELETE_BOARD(7, "게시판 삭제 실패.");
    private final int id;
    private final String message;

    BoardMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}