package com.example.rest.Exception;

import com.example.rest.controller.RestApiBController;
import com.example.rest.controller.RestApiController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice(basePackages = "com.example.rest.controller") // 특정 패키지 안에서 예외 처리를 지정해 줄 수 있음
@RestControllerAdvice(basePackageClasses = {RestApiBController.class , RestApiController.class}) // 특정 클래스를 지정하는 방식도 있음
public class RestApiExceptionHandler {

    // ExceptionHandler로 예외처리 하기
    //ERROR 2996 --- [nio-8181-exec-1] c.e.r.Exception.RestApiExceptionHandler  : RestApiExceptionHandler
    @ExceptionHandler(value = {Exception.class}) // 모든 예외처리는 Exception을 상속받기 때문
    public ResponseEntity exception(Exception e){
        log.error("RestApiExceptionHandler",e);
        return ResponseEntity.status(200).build();
    }

    //해당 Exception만 예외처리 하기
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity outOfBound(IndexOutOfBoundsException e){
        log.error("IndexOutOfBoundsException",e);
        return ResponseEntity.status(200).build();
    }


}
