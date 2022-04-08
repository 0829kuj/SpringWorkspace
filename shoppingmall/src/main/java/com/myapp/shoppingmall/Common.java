package com.myapp.shoppingmall;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.shoppingmall.ado.Cart;
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

//	// 모델에 추가
//	@ModelAttribute
//	public void sharedData(Model model) {
//		// cpages에 모든 페이지들을 담아서 순서대로 전달
//		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
//		List<Category> categories = categoryRepo.findAll();
//		model.addAttribute("cpages", cpages);
//		model.addAttribute("ccategories", categories);
//	}

	// 모델에 추가
	@ModelAttribute
	public void sharedData(Model model, HttpSession session) {
		
		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepo.findAll();
		
		boolean cartActive = false;
		
		if (session.getAttribute("cart") != null) {
			@SuppressWarnings("unchecked")
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			
			int size = 0;	// 장바구니 상품 갯수
			int total = 0;	// 총 가격
			
			for(Cart item : cart.values()) {
				size += item.getQuantitiy();
				total += item.getQuantitiy() * Integer.parseInt(item.getPrice());
			}
			model.addAttribute("csize", size);
			model.addAttribute("ctotal", total);
			cartActive = true;	// 장바구니에 상품 담겨있음
		}
		
		model.addAttribute("cpages", cpages);
		model.addAttribute("ccategories", categories);
		model.addAttribute("cartActive", cartActive);
	}
}
