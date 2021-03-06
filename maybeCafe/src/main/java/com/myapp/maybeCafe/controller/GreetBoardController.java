package com.myapp.maybeCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.maybeCafe.dao.GreetBoardMapper;
import com.myapp.maybeCafe.model.GreetBoardVO;
import com.myapp.maybeCafe.model.PageMakerDTO;
import com.myapp.maybeCafe.model.PageVO;
import com.myapp.maybeCafe.service.GreetBoardService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/greetboard")
@Log		// 콘솔에 로그 출력 (print out 대신 로그출력)
public class GreetBoardController {
	
	private GreetBoardService gBoardService;
	
	public GreetBoardController(GreetBoardService greetBoardService) {
		this.gBoardService = greetBoardService;
	}

//	@GetMapping("list")
//	public String greetListGET(Model model) {
//		model.addAttribute("greetboard", gBoardService.getGreetBoardList());
//		model.addAttribute("board", new GreetBoardVO());
//		return "greetBoard/greetList";
//	}
	
	@GetMapping("/list")
	public String greetListGET(PageVO page, Model model) {
		// 페이징 적용 모든 가입인사 가져오기
		model.addAttribute("greetboard", gBoardService.getListPaging(page));
		
		int total = gBoardService.getTotal();
		PageMakerDTO pmk = new PageMakerDTO(total, page);
		
		page.setAmount(5);
		model.addAttribute("pmk", pmk);
		model.addAttribute("page", page);
		model.addAttribute("board", new GreetBoardVO());
		return "greetBoard/greetList";
	}
	
	@PostMapping("/write")
	public String greetWritePOST(GreetBoardVO board, RedirectAttributes attr) {
		gBoardService.write(board);
		return "redirect:/greetboard/list";
	}
	
	@GetMapping("/delete")
	public String greetDelete(@RequestParam("gno") int gno, RedirectAttributes attr) {
		gBoardService.delete(gno);
		attr.addFlashAttribute("message", "삭제되었습니다.");
		return "redirect:/greetboard/list";
	}
}
