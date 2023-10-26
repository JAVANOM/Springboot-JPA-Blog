package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

//데이터만 
@RestController
public class UserApiController {
    
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		System.out.println("APIController");
		
		// 실제로 DB에 insert를 하고 아래에서 return이 되게 한다
		return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson)
		
		//return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
