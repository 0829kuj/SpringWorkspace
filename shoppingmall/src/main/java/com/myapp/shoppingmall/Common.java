package com.myapp.shoppingmall;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.myapp.shoppingmall.dao.Cart;
import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.PageRepository;
import com.myapp.shoppingmall.entites.Category;
import com.myapp.shoppingmall.entites.Page;

// 모든 컨트롤러에 적용(모든 페이지에 적용됨)
@ControllerAdvice
public class Common {

	@Autowired
	private PageRepository pageRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	// 모델에 추가
	@ModelAttribute
	public void sharedData(Model model, HttpSession session, Principal principal) {
		
		if (principal != null) {	// 인증된 상태를 모든 페이지에 전달
			model.addAttribute("principal", principal.getName());	// username을 전달
		}
		
		// cpages에 모든 페이지들을 담아서 순서대로 전달
		List<Page> cpages = pageRepo.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepo.findAllByOrderBySortingAsc();
		// 현재 장바구니 상태 (없을때 false)
		boolean cartActive = false;
		
		if (session.getAttribute("cart") != null) {	// 세션에 장바구니가 있을 경우
			@SuppressWarnings("unchecked")
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			
			int size = 0;	// 장바구니 상품 갯수
			int total = 0;	// 총 가격
			
			for(Cart item : cart.values()) {	// 장바구니 cart객체들을 반복하여 상품 갯수와 총 가격 계산
				size += item.getQuantity();
				total += item.getQuantity() * Integer.parseInt(item.getPrice());
			}
			model.addAttribute("csize", size);		// view에 전달
			model.addAttribute("ctotal", total);	// view에 전달
			cartActive = true;	// 장바구니에 상품 담겨있음(false -> true)으로 변경
		}
		
		model.addAttribute("cpages", cpages);
		model.addAttribute("ccategories", categories);
		model.addAttribute("cartActive", cartActive);	// 세션에 장바구니가 없으면 false 있으면 true
	}
}
