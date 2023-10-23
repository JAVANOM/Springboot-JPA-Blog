package com.cos.blog.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //여러개의 답변은 하나의 개시글 
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답글에 하나의 유저
	@JoinColumn(name="userId")
	private User user;
	
	@CreatedDate
    private Timestamp createDate;
}
