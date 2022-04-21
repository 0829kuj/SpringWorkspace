package com.myapp.bbs.service;

import java.util.List;

import com.myapp.bbs.model.ReplyVO;

public interface ReplyService {
	
	public void enroll(ReplyVO reply);	// 덧글 등록
	public List<ReplyVO> getReplyList(int reply_bno);	// 덧글 목록(board 글번호 필요)

	public int modify(ReplyVO reply);	// 덧글 수정
	public int delete(int reply_no);	// 덧글 삭제
}
