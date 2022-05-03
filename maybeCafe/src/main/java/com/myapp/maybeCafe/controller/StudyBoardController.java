package com.myapp.maybeCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.maybeCafe.model.StudyBoardVO;
import com.myapp.maybeCafe.service.StudyBoardService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/studyboard")
@Log		// 콘솔에 로그 출력 (print out 대신 로그출력)
public class StudyBoardController {
	
	private StudyBoardService sBoardService;
	
	public StudyBoardController(StudyBoardService studyBoardService) {
		this.sBoardService = studyBoardService;
	}

	@GetMapping("list")
	public String studyListGET(Model model) {
		model.addAttribute("studyboard", sBoardService.getStudyBoardList());
		return "studyBoard/studyList";
	}

	@GetMapping("/get")
	public String studyPageGET(@RequestParam("sno") int sno, Model model) {
		model.addAttribute("board", sBoardService.getPage(sno));
		return "studyBoard/studyGet";
	}
	
	@GetMapping("/write")
	public String studyWriteGET(Model model) {
		model.addAttribute("board", new StudyBoardVO());
		return "studyBoard/studyWrite";
	}
	
	@PostMapping("/write")
	public String studyWritePOST(StudyBoardVO board, RedirectAttributes attr) {
		sBoardService.write(board);
		attr.addFlashAttribute("message", "게시글이 등록되었습니다.");
		return "redirect:/studyboard/list";
	}
	
	@GetMapping("/modify")
	public String studyModifyGET(@RequestParam("sno") int sno, Model model) {
		model.addAttribute("board", sBoardService.getPage(sno));
		return "studyBoard/studyModify";
	}
	
	@PostMapping("/modify")
	public String studyModifyPOST(StudyBoardVO board, RedirectAttributes attr) {
		sBoardService.modify(board);
		attr.addFlashAttribute("message", "수정이 완료되었습니다.");
		return "redirect:/studyboard/list";
	}
	
	@GetMapping("/delete")
	public String studyDeleteGET(@RequestParam("sno") int sno, RedirectAttributes attr) {
		sBoardService.delete(sno);
		return "redirect:/studyboard/list";
	}
	
}
