package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean 등록이 된다.
// @Repository 생략 가능
public interface UserRepository  extends JpaRepository<User, Integer>{ // User 테이블을 관리하는 Repository, User 의 프라이머리 키는 Integer
     
	
}
