package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean 등록이 된다.
// @Repository 생략 가능
public interface UserRepository  extends JpaRepository<User, Integer>{ // User 테이블을 관리하는 Repository, User 의 프라이머리 키는 Integer
     
	
}



//JPA Naming 쿼리 전략
	//SELECT * FROM user WHERE username = ?1 AND password = ?2;
	//User findByUsernameAndPassword(String username, String password);
	
	//@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	//User login(String username, String password);