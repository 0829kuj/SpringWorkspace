package com.myapp.miniBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.miniBlog.entities.Guest;
import com.myapp.miniBlog.service.GuestService;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	@GetMapping
	public String index() {
		return "guestIndex";
	}
	
	@GetMapping("/list")
	public String displayGuestBookList(Model model) {
		List<Guest> GuestList = guestService.findAll();
		model.addAttribute("GuestList", GuestList);
		
		return "guestBook";
	}

	
	
}
