package com.example.rest.controller;

import com.example.rest.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestApiController {

    @GetMapping(path="/hello")
    public String hello(){

        var html = "<html> <body> <h1> Hello Spring boot </h1> </body> </html>";
        return html;
    }


    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(@PathVariable(name = "message") String msg,@PathVariable int age,@PathVariable boolean isMan){

        System.out.println("echo message : " + msg);
        System.out.println("echo age : " +age);
        System.out.println("echo isMan : " + isMan);


        //TODO 소문자를 대문자로 변경 => toUpperCase()

        return msg.toUpperCase();

    }

    // http://localhost:8080/api/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping(path = "/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name = "issued-month") String issuedMonth, //파싱 하는데 제일 기본적인 형태
            //@RequestParam String issued_day 추천하지 않는 방식
            @RequestParam(name = "issued_day") String isseudDay
            ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(isseudDay);
    }

    //http://localhost:8080/api/book2?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    @GetMapping(path = "/book2")
    public void queryParamDTO(
            BookQueryParam bookQueryParam
    ){
        System.out.println(bookQueryParam);
    }

    @DeleteMapping(path = {"/user/{userName}/delete","/user/{userName}/del"})
    public void delete(@PathVariable String userName){
        log.info("user-name : {}", userName);
    }

}
