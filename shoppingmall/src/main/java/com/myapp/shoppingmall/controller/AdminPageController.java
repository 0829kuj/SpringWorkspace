package com.myapp.shoppingmall.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.ado.PageRepository;
import com.myapp.shoppingmall.entitys.Page;

@Controller
@RequestMapping("/admin/pages")
public class AdminPageController {
	
	@Autowired
	private PageRepository pageRepo;
	
	@GetMapping
	public String index(Model model) {
		List<Page> pages = pageRepo.findAll();
		model.addAttribute("pages", pages);
		return "admin/pages/index";
	}
	
	@GetMapping("/add")
	public String add(@ModelAttribute Page page) {
//		model.addAttribute("page", new Page());
		return "admin/pages/add";
	}
	
	@PostMapping("/add")
	public String add(@Valid Page page, BindingResult bindingResult) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/pages/add";
		
		return "redirect:admin/pages/add";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model)
	Page page = pageRepo.findById(id);	// id로 page 객체 리턴 메서드 작성
	model.addAttribute("page", page);	// page 속성을 추가하여 edit.html 보냄
	return "admin/pages/edit";
}
