package com.myapp.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.bbs.model.BoardVO;
import com.myapp.bbs.model.Criteria;
import com.myapp.bbs.model.PageMakerDTO;
import com.myapp.bbs.service.BoardService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log		// 콘솔에 로그 출력 (print out 대신 로그출력)
public class BoardController {
	
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
//	@GetMapping("/list")
//	public String boardListGet() {
//		log.info("게시판 리스트 페이지 진입");
//		return "list";
//	}
	@GetMapping("/enroll")
	public String boardEnrollGet(Model model) {
		model.addAttribute("board", new BoardVO());
		return "enroll";
	}
	
	@PostMapping("/enroll")
	public String boardEnrollPost(BoardVO board, RedirectAttributes attr) {
		log.info("BoardVO : " + board);
		boardService.enroll(board);
		attr.addFlashAttribute("message", "게시물 등록 성공!");
		return "redirect:/board/list";	 //포스트 다음에 리다이렉트
	}
	
//	@GetMapping("/list")
//	public String boardListGet(Model model) {
//		// boardList에 모든 게시글을 전달
//		model.addAttribute("boardList", boardService.getList());
//		return "list";
//	}
	

	// 게시글 목록 페이지 (페이징 적용)
	@GetMapping("/list")
	public String boardListGet(Criteria cri, Model model) {
		// boardList에 페이징 된 모든 게시글을 전달
		model.addAttribute("boardList", boardService.getListPaging(cri));
		
		int total = boardService.getTotal();
		PageMakerDTO pmk = new PageMakerDTO(total, cri);	// 객체 생성 시 모든 변수 계산됨
		
		model.addAttribute("pmk", pmk);		// 페이지네이션을 위한 pmk객체 전달
		
		return "list";
	}
	
	/**
	 * 게시판 글 조회
	 * @param bno 조회할 글 번호
	 * @param model
	 * @return 조회 페이지로 이동
	 * */
	@GetMapping("/get")
	public String boardPageGet(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("board", boardService.getPage(bno));
		return "get";
	}
	
	// 수정페이지 출력
	@GetMapping("/modify")
	public String boardModifyGet(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("board", boardService.getPage(bno));
		return "modify";
	}
	
	// 수정
	@PostMapping("/modify")
	public String boardModifyPost(BoardVO board, RedirectAttributes attr) {
		boardService.modify(board);		// modify페이지에서 수정된 내용으로 업데이트
		attr.addFlashAttribute("message", "수정 성공!");
		return "redirect:/board/list";	// post 후 새로고침하면 다시 post가 될 수 있으므로 새로운요청인 redirect로 처리
	}
	
	@GetMapping("/delete")
	public String boardDeleteGet(@RequestParam("bno") int bno, RedirectAttributes attr) {
		boardService.delete(bno);
		attr.addFlashAttribute("message", "삭제 성공!");
		return "redirect:/board/list";
	}
}
