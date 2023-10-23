package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//파일 리턴
@Controller
public class TempControllerTest {
    
	@GetMapping("/temp/home")
	public String testHome() {
		System.out.println("temphome");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴 명을 : /home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix : /WEB-INF/views
		//suffix : .jsp
		// 풀네임 : /WEB-INF/views/test.jsp
		
		return "test";
	}
}
