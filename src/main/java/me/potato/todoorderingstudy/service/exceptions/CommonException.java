package me.potato.todoorderingstudy.service.exceptions;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{

    private final String code;
    private final String message;


    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
