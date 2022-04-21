package com.myapp.bbs.service;

import java.util.List;

import com.myapp.bbs.dao.ReplyMapper;
import com.myapp.bbs.model.ReplyVO;

public class ReplyServiceImpl implements ReplyService{
	
	private ReplyMapper replyMapper;

	@Override
	public void enroll(ReplyVO reply) {
		replyMapper.enroll(reply);
	}

	@Override
	public List<ReplyVO> getReplyList(int reply_bno) {
		return replyMapper.getReplyList(reply_bno);
	}

	@Override
	public int modify(ReplyVO reply) {
		return replyMapper.modify(reply);
	}

	@Override
	public int delete(int reply_no) {
		return replyMapper.delete(reply_no);
	}

}
