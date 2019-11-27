package com.project_sem4.fe.controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value ={Exception.class} )
    public String  exceptionHandler(Exception ex){
        System.out.println(ex);
        return "exception/error404";
    }
}
