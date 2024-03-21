package com.example.project1._core.errors.exception;

public class ApiException500 extends RuntimeException {
    public ApiException500(String msg){
        super(msg);
    }
}
