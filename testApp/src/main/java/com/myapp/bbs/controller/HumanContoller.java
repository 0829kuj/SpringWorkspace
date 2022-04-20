package com.myapp.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myapp.bbs.mapper.CommentMapper;
import com.myapp.bbs.mapper.HumanMapper;
import com.myapp.bbs.model.Comment;
import com.myapp.bbs.model.Human;

@Controller
public class HumanContoller {

	@Autowired
	public HumanMapper Hmapper;
	
	@Autowired
	public CommentMapper Cmapper;
	
	@GetMapping("/")	// 메인페이지 출력
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/human/{idNum}")
	public String humanPage(@PathVariable("idNum") int num, Model model) {
		Human human = Hmapper.getHuman(num);
		model.addAttribute("user", human);
		List<Comment> comm = Cmapper.getComment(num);
		model.addAttribute("comm", comm);
		
		return "human";
	}

	@GetMapping("/humans")
	public String listPage(Model model) {
		List<Human> humanList = Hmapper.getHumanList();
		model.addAttribute("users", humanList);
		
		return "humanList";
	}
}
