package com.myapp.bbs.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;

import com.myapp.bbs.dao.BoardMapper;
import com.myapp.bbs.model.BoardVO;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)	// 현재 연결된 실제DB로 테스트함을 의미
@Rollback(value = false)							// 테스트시 롤백 안함
public class BoardMapperTests {
	// Junit 5버전으로 테스트
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testEnroll() {
		BoardVO vo = new BoardVO();
		
		vo.setTitle("제목_테스트");
		vo.setContent("내용_테스트");
		vo.setWriter("작성자_테스트");
		
		boardMapper.enroll(vo);
	}
}
