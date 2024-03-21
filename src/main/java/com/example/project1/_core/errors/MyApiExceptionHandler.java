package com.example.project1._core.errors;

import com.example.project1._core.errors.exception.*;
import com.example.project1._core.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// RuntimeException이 터지면 해당 파일로 오류가 모인다
@RestControllerAdvice // 데이터 응답
public class MyApiExceptionHandler {

    @ExceptionHandler(ApiException400.class)
    public ResponseEntity<?> ex400(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); // http body -> 구성한 객체 body 에도 상태 코드를 넣는 이유는 프론트가 편하게 해주기 위해서!
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http body, http header
    }

    @ExceptionHandler(ApiException401.class)
    public ResponseEntity<?> ex401(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ApiException403.class)
    public ResponseEntity<?> ex403(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiException404.class)
    public ResponseEntity<?> ex404(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException500.class)
    public ResponseEntity<?> ex500(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
