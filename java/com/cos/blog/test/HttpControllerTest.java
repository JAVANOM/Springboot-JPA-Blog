package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답HTML 파일)
//@Controller 

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : " ;
    
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m = new Member(1, "ssar", "1234", "test@test.com");
		//빌더패턴은 순서가 상관이 없음
		Member m = Member.builder()
				             .username("ssar")
				             .password("1234")
				             .email("ssar@nate.com")
				             .build();
		System.out.println(TAG +"getter : " + m.getId());
		m.setId(5000);
		System.out.println(TAG +"getter : " + m.getId());
		
		return "lombok test 완료";
		
	}
	
	//인터넷 브라우저 요청은 get만 가능
	//@RequestParam int id, @RequestParam String username
	//application/json -> MessageConverter (스프링부트)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + "," + m.getUsername() +","+m.getPassword();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m ) {
		return "post 요청 : " + m.getId() + "," + m.getUsername() +","+m.getPassword();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청:" + m.getId() + "," + m.getUsername() +","+m.getPassword();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
