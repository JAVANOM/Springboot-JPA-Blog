package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
     
	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	
	
	@DeleteMapping("/dummy/uesr/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제 되었습니다. : " +id;
	}
	
	
	//save함수는 id를 전달하지 않으면 insert를 하고 
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 함
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함
	// email, password
	@Transactional // 함수 종료시에 자동으로 commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {  // Json 데이터를 받으려면 RequestBody 필요, json 데이터를 요청  => Java Object(MessageConverter의 jackson 라이브러리)로 변환해서 받음
		   System.out.println("id : " +id);
		   System.out.println("password : " + requestUser.getPassword());
		   System.out.println("email : " + requestUser.getEmail());
		   
		   User user = userRepository.findById(id).orElseThrow(() -> {
			   return new IllegalArgumentException("수정에 실패 하였습니다.");
		   });
		   
		  user.setPassword(requestUser.getPassword());
		  user.setEmail(requestUser.getEmail());
		   
		  // uesrRepository.save(user);
		  
		  // 더티 체킹(업데이트 시 변경 감지를 통해 영속성 컨텍스트 1차 캐시에 저장된 오브젝트와 DB를 비교하여 다르면 변경)
		  
		   return user;
	}
	
	
	//전체 조회
	@GetMapping("/dummy/users")
	public List<User> list(){
		
		return userRepository.findAll();
	}
	
	//한페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Direction.DESC) Pageable pageable){
		
		List<User> user = userRepository.findAll(pageable).getContent();
		
	   //Page<User> users  = userRepository.findAll(pageable);
	   //if(users.isFirst());
		
		return user;
	}
	
	//{id} 주소로 파라미터를 전달 받을수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4 을 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null이 된다.
		// return이 null 인데 그럼 문제가 생긴다
		// optional로 너의 user 객체를 감싸서 가지고 올거니 null 인지 아닌지 판단해서 return을 해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
	
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id );
			}
		});
		
		//람다식
		//User user = userRepository.findById(id).orElseThrow(() -> {
		//	return new IllegalArgumentException("해당 사용자가 없습니다.");
		//});
		
		/*User user = userRepository.findById(id).orElseGet(new Supplier<User>() { //interface를 new를 하려면 익명클래스로 생성해야함

			@Override
			public User get() {
				// TODO Auto-generated method stub
				return  new User(); //해당하는 값이 없을 때 null로 반환
			}
		}); */
		// 요청 : 웹브라우저
		 // user 객체 = 자바  오브젝트
		// 변환 (웹브라우저가 이해 할 수 있는 데이터 ) -> json (Gson 라이브러리 사용했음)
		// 스프링부트 = MessageConverter 라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변호나해서 브라우저에게 전달
		return user;
	}
	
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	 public String join(User user) { // key = value (약속된 규칙) Object 형태로도 가능
		 
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		userRepository.save(user);
		
		return "회원가입이 완료 되었습니다.";
	 }
}
