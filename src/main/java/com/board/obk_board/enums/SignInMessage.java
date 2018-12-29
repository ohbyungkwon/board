package com.board.obk_board.enums;

public enum SignInMessage {
    DUPLICATE_EMAIL(1,"중복 이메일"),
    EMPTY_EMAIL(2,"이메일 공백"),
    INCORRECT_PASSWORD(3,"비밀번호 형식 문제"),
    EMPTY_PASSWORD(4,"비밀번호 공백"),
    SUCCESS_EMAIL(5,"사용 가능한 이메일"),
    SUCCESS_PASSWORD(6,"사용 가능한 비밀번호"),
    INCORRECT_EMAIL(7,"이메일 형식 문제");

    private final int id;
    private final String message;

    SignInMessage(int id, String message) {
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
