package com.myapp.bbs.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.myapp.bbs.dao.BoardMapper;
import com.myapp.bbs.model.BoardVO;

import lombok.extern.java.Log;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)	// 현재 연결된 실제DB로 테스트함을 의미
@Rollback(value = false)							// 테스트시 롤백 안함
@Log
public class BoardMapperTests {
	// Junit 5버전으로 테스트
	@Autowired
	private BoardMapper boardMapper;
	
//	@Test
//	public void testEnroll() {
//		BoardVO vo = new BoardVO();
		// 게시글 등록
//		vo.setTitle("제목_테스트");
//		vo.setContent("내용_테스트");
//		vo.setWriter("작성자_테스트");
//		
//		boardMapper.enroll(vo);
		
		// 게시글 목록
//		List<BoardVO> list = boardMapper.getList();
		// for문으로 테스트
//		for(int i=0; i < list.size(); i++) {
//			log.info("" + list.get(i));
//		}
		// forEach문		
//		for(BoardVO vo :list) {
//			log.info("" + vo);
//		}
		// foreach메서드와 람다식
//		list.forEach(board -> log.info(""+board));
		
		// 게시글 조회
//		int bno = 1;
//		log.info("" + boardMapper.getPage(1));
//	}
	
//	@Test
//	public void testModify() {
//		BoardVO board = new BoardVO();
//		board.setBno(1);
//		board.setTitle("제목_수정");
//		board.setContent("내용_수정된 내용입니다");
//		
//		int result = boardMapper.modify(board);
//		log.info("result : " + result);
//	}
	
	@Test
	public void testDelete() {
		int result = boardMapper.delete(5);
		log.info("result: " + result);
	}
	

	
}
