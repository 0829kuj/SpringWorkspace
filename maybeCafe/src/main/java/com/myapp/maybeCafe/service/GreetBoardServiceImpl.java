package com.myapp.maybeCafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.maybeCafe.dao.GreetBoardMapper;
import com.myapp.maybeCafe.model.GreetBoardVO;
import com.myapp.maybeCafe.model.PageVO;

@Service
public class GreetBoardServiceImpl implements GreetBoardService {
	
	private GreetBoardMapper gBoardMapper;
	
	// 생성자 주입
	public GreetBoardServiceImpl(GreetBoardMapper greetBoardMapper) {
		this.gBoardMapper = greetBoardMapper;
	}

	@Override
	public List<GreetBoardVO> getGreetBoardList() {
		// 가입인사 전체 가져오기
		return gBoardMapper.getGreetBoardList();
	}
	
	@Override
	public List<GreetBoardVO> getListPaging(PageVO page) {
		// 페이징 적용 가입인사 전체 가져오기
//		page.setAmount(5);
		return gBoardMapper.getListPaging(page);
	}
	
	@Override
	public int getTotal() {
		// 전체 가입인사 수
		return gBoardMapper.getTotal();
	}

	@Override
	public void write(GreetBoardVO gBoardVO) {
		// 가입인사 작성
		gBoardMapper.write(gBoardVO);
	}

	@Override
	public void delete(int gno) {
		gBoardMapper.delete(gno);
	}

}
