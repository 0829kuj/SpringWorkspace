package com.myapp.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myapp.bbs.mapper.HumanMapper;
import com.myapp.bbs.model.Human;

@Controller
public class HumanContoller {

	@Autowired
	public HumanMapper mapper;
	
	@GetMapping("/")	// 메인페이지 출력
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/human/{num}")
	public String humanPage(@PathVariable("num") int num, Model model) {
		Human human = mapper.getHuman(num);
		model.addAttribute("user", human);
		
		return "human";
	}

	@GetMapping("/humans")	// 모든 사용자를 보여주는 페이지 왜 500에러뜨지???
	public String listPage(Model model) {
		List<Human> humanList = mapper.getHumanList();
		model.addAttribute("users", humanList);
		
		return "humanList";
	}
}
