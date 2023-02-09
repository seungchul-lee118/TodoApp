package com.soloproject.todo.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_NOT_EXIST(404, "Todo not exist!");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
