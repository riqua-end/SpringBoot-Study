package com.example.rest.Exception;

import com.example.rest.controller.RestApiBController;
import com.example.rest.controller.RestApiController;
import com.example.rest.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

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


    // @ExceptionHandler 어노테이션을 사용하여 특정 예외를 처리하는 핸들러 메서드
    // 예외 종류로 NoSuchElementException.class를 지정하여 해당 예외가 발생했을 때 이 메서드가 호출되도록 함.

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Api> noSuchElement(NoSuchElementException e) {
        // 예외 발생 시 에러 로그를 기록
        log.error("", e);

        // API 응답을 생성하는 빌더 패턴 활용
        var response =  Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))  // HTTP 상태 코드 설정
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())     // HTTP 상태 코드의 기본 메시지 설정
                .build();

        // ResponseEntity를 통해 HTTP 응답을 생성하고 API 응답을 반환
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // HTTP 상태 코드 설정
                .body(response);                // 생성한 API 응답을 응답 본문으로 설정하여 반환
    }

    // 위의 메서드는 NoSuchElementException이 발생했을 때 처리하는 핸들러
    // 로그에는 발생한 예외의 스택 트레이스 등을 상세히 기록하여 디버깅 및 문제 해결에 도움
    // API 응답은 빌더 패턴을 활용하여 간결하게 생성되며, HTTP 상태 코드와 메시지는 표준 상수 및 메서드를 활용하여 설정
    // ResponseEntity를 사용하여 HTTP 상태 코드와 응답 본문을 함께 반환함으로써 클라이언트에게 적절한 응답을 제공



}
