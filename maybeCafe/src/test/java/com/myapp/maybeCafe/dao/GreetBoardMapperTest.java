package com.myapp.maybeCafe.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.myapp.maybeCafe.dao.GreetBoardMapperTest;
import com.myapp.maybeCafe.model.GreetBoardVO;
import com.myapp.maybeCafe.model.PageVO;
import com.myapp.maybeCafe.model.StudyBoardVO;

import lombok.extern.java.Log;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)	// 현재 연결된 실제DB로 테스트함을 의미
@Rollback(value = false)							// 테스트시 롤백 안함
@Log
public class GreetBoardMapperTest {
	// JUnit 5버전으로 테스트
	@Autowired
	private GreetBoardMapper gBoardMapper;
	
//	@Test
//	public void testGetGreetBoardList() {
//		List<GreetBoardVO> list = gBoardMapper.getGreetBoardList();
//		list.forEach(board -> log.info("" + board));
//	}
	
//	@Test
//	public void testDeleteGreetBoard() {
//		gBoardMapper.delete(4);
//	}
	
//	@Test	// 총 게시글 구하기
//	public void testGetTotal() {
//		int result = gBoardMapper.getTotal();
//		log.info("총 게시글 수: " + result);
//	}
	
	@Test
	public void testGetListPaging() {
		PageVO page = new PageVO(1, 5);
		List<GreetBoardVO> list = gBoardMapper.getListPaging(page);
		list.forEach(board -> log.info("" + board));
	}
}
