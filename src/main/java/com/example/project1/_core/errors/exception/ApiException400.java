package com.example.project1._core.errors.exception;

public class ApiException400 extends RuntimeException {
    public ApiException400(String msg){
        super(msg);
    }
}
