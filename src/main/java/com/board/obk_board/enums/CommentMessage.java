package com.board.obk_board.enums;

public enum CommentMessage {
    SUCCESS_COMMENT(0," 댓글 작성 성공"),
    FAIL_COMMENT(1,"댓글 작성 실패"),
    NOT_INPUT_JAVASCRIPT(2,"자바스크립트 작성 불가"),
    EMPTY_INPUT(3,"내용이 비었습니다"),
    NOT_CORRECT_WRITER(4,"글쓴이가 아닙니다.");

    private final int id;
    private final String message;

    CommentMessage(int id, String message) {
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
