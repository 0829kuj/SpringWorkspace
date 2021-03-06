package com.myapp.maybeCafe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.maybeCafe.dao.StudyBoardMapper;
import com.myapp.maybeCafe.model.PageVO;
import com.myapp.maybeCafe.model.StudyBoardVO;

@Service
public class StudyBoardServiceImpl implements StudyBoardService{
	
	private StudyBoardMapper sBoardMapper;
	
	// 생성자 주입
	public StudyBoardServiceImpl(StudyBoardMapper studyBoardMapper) {
		this.sBoardMapper = studyBoardMapper;
	}

	@Override
	public List<StudyBoardVO> getStudyBoardList() {
		// 공부게시판 전체 가져오기
		return sBoardMapper.getStudyBoardList();
	}

	@Override
	public List<StudyBoardVO> getListPaging(PageVO page) {
		// 페이징 적용 공부게시판 전체 가져오기 
		return sBoardMapper.getListPaging(page);
	}

	@Override
	public int getTotal() {
		// 전체 게시글 수
		return sBoardMapper.getTotal();
	}


	@Override
	public StudyBoardVO getPage(int sno) {
		// 공부게시판 하나의 게시글 가져오기
		return sBoardMapper.getPage(sno);
	}

	@Override
	public void write(StudyBoardVO sBoardVO) {
		// 공부게시판 게시글 작성
		sBoardMapper.write(sBoardVO);
	}

	@Override
	public void delete(int sno) {
		// 공부게시판 게시글 삭제
		sBoardMapper.delete(sno);
	}

	@Override
	public void modify(StudyBoardVO sBoardVO) {
		sBoardMapper.modify(sBoardVO);
	}



}
