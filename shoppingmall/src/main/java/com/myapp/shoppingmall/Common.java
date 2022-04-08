package com.myapp.shoppingmall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.shoppingmall.ado.CategoryRepository;
import com.myapp.shoppingmall.ado.PageRepository;
import com.myapp.shoppingmall.entitys.Category;
import com.myapp.shoppingmall.entitys.Page;

// 모든 컨트롤러에 적용(모든 페이지에 적용됨)
@ControllerAdvice
public class Common {
	
	@Autowired
	private PageRepository pageRepo;
	
	@Autowired
	private CategoryRepository categoryRepo; 
	
	// 모델에 추가
	@ModelAttribute
	public void sharedData(Model model) {
		// cpages에 모든 페이지들을 담아서 순서대로 전달
		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("cpages", cpages);
		model.addAttribute("ccategories", categories);
	}

}
