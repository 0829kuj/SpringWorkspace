package com.myapp.bbs.service;

import org.springframework.stereotype.Service;

import com.myapp.bbs.dao.BoardMapper;
import com.myapp.bbs.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	
	// 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	@Override
	public void enroll(BoardVO board) {
		boardMapper.enroll(board);
		
	}
}
