package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

//import javax.servlet.http.HttpSession;

//데이터만 
@RestController
public class UserApiController {
    
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("APIController");
		
		// 실제로 DB에 insert를 하고 아래에서 return이 되게 한다

		int result = userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		
		//return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	
	
	
	
	
	
	// 스프링 시큐리티 이용해서 로그인!!
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>login(@RequestBody
	 * User user, HttpSession session){
	 * System.out.println("UserApiController : login 호출됨"); User principal =
	 * userService.로그인(user); // principal 접근 주체
	 * 
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * 
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
