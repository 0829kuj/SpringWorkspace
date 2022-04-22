package com.myapp.bbs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.bbs.model.ReplyVO;
import com.myapp.bbs.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	// Replyservice 객체를 생성자 주입
	private ReplyService replyService;
	
	// 생성자 객체 주입시에는 @Autowired 필요없음
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@GetMapping("/{bno}")	// 게시글에 달린 덧글들을 불러오기
	public List<ReplyVO> replyListGet(@PathVariable int bno) {
		return replyService.getReplyList(bno);
	}

	@PostMapping
	public ReplyVO replyEnrollPost(@RequestBody ReplyVO reply) {
		// 입력된 json타입 데이터를 받아서 reply객체 리턴(json타입으로 리턴)
		replyService.enroll(reply);		// DB에 덧글 저장
		return reply;
	}
	
	@PutMapping
	public ReplyVO replyUpdatePut(@RequestBody ReplyVO reply) {
		replyService.modify(reply);		// DB의 덧글데이터 수정하기
		return reply;					// 테스트로 reply 리턴
	}
	
	@DeleteMapping("/{id}")
	public void replyDelete(@PathVariable("id") int reply_no) {
		System.out.println(reply_no);
		replyService.delete(reply_no);
	}

}
