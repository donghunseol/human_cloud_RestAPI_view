package com.example.project1._core.errors.exception;

public class ApiException404 extends RuntimeException {
    public ApiException404(String msg){
        super(msg);
    }
}
