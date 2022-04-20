package com.myapp.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.myapp.bbs.model.BoardVO;
import com.myapp.bbs.model.Criteria;

@Mapper
public interface BoardMapper {
	
	public void enroll(BoardVO board);	// 게시글 등록
	public List<BoardVO> getList();	// 게시글 목록
	
	// 게시판 목록(페이징 적용). pageNum과 amount를 입력받아 객체 cri생성. 없으면 기본값(1, 10)적용, keyword가 추가됨
	public List<BoardVO> getListPaging(Criteria cri);	
	
	public BoardVO getPage(int bno);	// 게시글 조회
	public int modify(BoardVO board);	// 게시글 수정
	public int delete(int bno);		// 게시글 삭제
	
	public int getTotal(Criteria cri);	// 게시글 총 갯수
}
