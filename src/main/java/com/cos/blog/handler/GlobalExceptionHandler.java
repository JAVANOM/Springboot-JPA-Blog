package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 어느 경로에서 exception이 발생 하던지 해당 handler 클래스에서 처리
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		
		return "<h1>"+ e.getMessage() +"</h1>";
	}
}
