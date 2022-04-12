package com.myapp.shoppingmall.controller;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.shoppingmall.dao.Cart;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entites.Product;

@Controller
@RequestMapping("/cart")
@SuppressWarnings("unchecked")		// 오브젝트 -> hashmap형변환 시 발생하는 warnning을 제거하기위해 추가
public class CartController {
	
	@Autowired
	private ProductRepository productRepo;	// 상품을 카트에 담을것이므로 필요
	
	/**
	 * 상품의 id를 입력받아 세션에 카트리스트를 저장
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 * */
	@GetMapping("/add/{id}")	// 상품을 DB에서 가져와 세션의 카트에 담는 과정
	public String add(@PathVariable int id, HttpSession session, Model model, 
						@RequestParam(required = false) String cartPage) {
		// 0. id로 상품의 정보를 DB에서 가져와 product객체에 저장
		Product product = productRepo.getById(id);
		
		// 1. 세션에 이미 만들어진 카트(장바구니)가 없을 경우 세션에 가져온 제품을 저장
		if(session.getAttribute("cart") == null) {	// 
			HashMap<Integer, Cart> cart = new HashMap<>();	// map을 통해 <id, 카트> 로 리스트를 만듦
			cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage())); // 새로저장하는 것이므로 무조건 1번으로 저장
			session.setAttribute("cart", cart);		// 세션에 카트객체를 새로 만들어 저장
		} else {	// 2. 세션에 이미 만들어진 카트가 있을 경우	( (1)그 상품이 담겨있을 경우 (2)없을경우 )
			// 세션에 저장될 땐 무조건 오브젝트 타입으로 저장되므로 hashmap타입으로 저장하려면 형변환을 거쳐야 함
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			if(cart.containsKey(id)) {		// 2 (1).같은 상품id가 이미 카트에 있을 경우
				int qty = cart.get(id).getQuantity();		// 카드객체를 불러와 그 객체의 수량을 get으로 검색해 해당상품이 현재 몇개 담겨있는지 체크
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), ++qty, product.getImage()));	// 현재 카트의 수량에 +1 해준 후 업데이트
			} else {		// 2 (2). 같은 상품id가 이미 카트에 없을 경우
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
				session.setAttribute("cart", cart);
				// 2 (1)은 있는 카트에 담긴 상품객체를 업데이트하므로 세션에 저장할필요없음, 2(2)의 경우 업데이트가 아닌 신규저장이므로 세션에 저장필요
			}
			
		}
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		
		int size = 0;	// 장바구니 상품 갯수
		int total = 0;	// 총 가격
		
		for(Cart item : cart.values()) {	// 장바구니 cart객체들을 반복하여 상품 갯수와 총 가격 계산
			size += item.getQuantity();
			total += item.getQuantity() * Integer.parseInt(item.getPrice());
		}
		model.addAttribute("size", size);		// view에 전달
		model.addAttribute("total", total);	// view에 전달
		
		if(cartPage != null) {	// cart.html 페이지에서 (+)버튼을 눌렀을 때 다시 카트페이지로 돌아감
			return "redirect:/cart/view";
		}
		
		return "cart_view";	// cart_view.html에 size total을 넣어서 리턴
	}
	
	@GetMapping("/subtract/{id}")		// (-) 버튼을 눌렀을 때
	public String subtract(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
		// 세션에서 카트 호출해오기
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		// cart에서 현재 선택한 상품(id) 의 수량(getQuantity)을 가져옴
		int qty = cart.get(id).getQuantity();
		
		if(qty == 1) {	// 상품이 1개일때 -1 -> cart에서 상품제거
			cart.remove(id);	// key값으로 해당 상품을 cart에서 제거
			if(cart.size() == 0) {
				session.removeAttribute("cart");	// 카트에 상품이 하나도 없으면 session에 있는 cart객체를 제거
			}
		} else {
			cart.get(id).setQuantity(--qty);		// 수량만 -1
		}
		
		String refererLink = httpServletRequest.getHeader("Referer");	// 요청된 이전 주소의 정보가 들어있음
		
		//		return "redirect:/cart/view";
		return "redirect:" + refererLink;	// 다시 이전페이지로 이동
	}
	
	@GetMapping("/remove/{id}")		// 상품 삭제
	public String remove(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
		// 세션에서 카트 호출해오기
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		
		cart.remove(id);	// id로 삭제
		
		if(cart.size() == 0) {
			session.removeAttribute("cart");	// 카트에 상품이 하나도 없으면 session에 있는 cart객체를 제거
		}
		
		String refererLink = httpServletRequest.getHeader("Referer");	// 요청된 이전 주소의 정보가 들어있음
		
		return "redirect:" + refererLink;	// 다시 이전페이지로 이동
	}
	
	@GetMapping("/clear")		// 카트 전체 삭제
	public String clear(HttpSession session, HttpServletRequest httpServletRequest) {
		session.removeAttribute("cart");	// 세션에 저장된 cart객체를 제거
		String refererLink = httpServletRequest.getHeader("Referer");	// 요청된 이전 주소의 정보가 들어있음
		
		return "redirect:" + refererLink;	// 다시 이전페이지로 이동
	}

	@GetMapping("/view")
	public String view(HttpSession session, Model model) {
		
		if(session.getAttribute("cart") == null) {
			return "redirect:/";	//장바구니가 없을경우 홈페이지로 이동
		}
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		
		model.addAttribute("cart", cart);	// 장바구니가 있을경우 세션에서 가져와 cart객체에 담은 후 model을 통해 보내줌
		model.addAttribute("noCartView", true);		// 카테고리아래 카트뷰는 출력하지 않음
		
		
		return "cart";	// cart.html 페이지로 리턴
	}
}
