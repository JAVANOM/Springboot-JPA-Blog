package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴
//ORM -> Java (다른언어) Object -> 테이블로 매핑해주는 기술
@Entity  //User 클래스가 MySQL 테이블이 자동으로 생성
@DynamicInsert
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto_increment
    
	@Column(nullable = false, length = 30, unique = true)
	private String username; //아이디
	
	@Column(nullable = false, length = 1000) //해쉬(비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
    
	@ColumnDefault("'user'")
	private String role; // Enum을 쓰는게 좋다. 도메인 설정이 가능(범위를 정할 수 있음)
	
	
	@CreationTimestamp // 시간이 자동으로 입력
	private Timestamp createDate; //수정시 updateDate 필요
}
