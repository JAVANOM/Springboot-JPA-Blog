package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;




// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
public class BoardService {
    
	@Autowired
	private BoardRepository boardRepository;
	

	@Transactional
	public void 글쓰기(Board board, User user ) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		
		return boardRepository.findAll(pageable);
	}
	
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(() -> { return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		
		boardRepository.deleteById(id);
				
	}
	
	@Transactional //영속화 시키기
	public void 글수정하기(int id, Board requestBoard) {
		  Board board = boardRepository.findById(id)
		  .orElseThrow(() -> {
			  return new IllegalArgumentException("글찾기 실패 : 아디디를 찾을 수 없습니다.");
		  });
		  
		  board.setTitle(requestBoard.getTitle());
		  board.setContent(requestBoard.getContent());
	}
	
	/*
	 * @Transactional(readOnly = true) //select 할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
	 * public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
