package com.myapp.maybeCafe.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.myapp.maybeCafe.dao.StudyBoardMapperTest;
import com.myapp.maybeCafe.model.StudyBoardVO;

import lombok.extern.java.Log;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)	// 현재 연결된 실제DB로 테스트함을 의미
@Rollback(value = false)							// 테스트시 롤백 안함
@Log
public class StudyBoardMapperTest {
	// JUnit 5버전으로 테스트
	@Autowired
	private StudyBoardMapper sBoardMapper;

//	@Test	// 공부게시판 단일게시글 조회
//	public void teststudyBoardGet() {
//		int sno = 1;
//		log.info("" + sBoardMapper.getPage(sno));
//	}
	
//	@Test	// 공부게시판 새게시글 등록
//	public void testWrite() {
//		StudyBoardVO vo = new StudyBoardVO();
//		vo.setTitle("JUnit5테스트_제목");
//		vo.setContent("JUnit5테스트_컨텐츠내용");
//		vo.setWriter("JUnit5테스트_작성자");
//		vo.setHit(0);
//		vo.setLike_no(0);
//		sBoardMapper.write(vo);
//	}
	
//	@Test	// 공부게시판 게시글삭제
//	public void testDelete() {
//		int sno = 5;
//		sBoardMapper.delete(sno);
//	}
	
}
