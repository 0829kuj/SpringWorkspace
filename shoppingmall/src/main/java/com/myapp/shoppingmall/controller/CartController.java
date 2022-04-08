package com.myapp.shoppingmall.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.shoppingmall.ado.Cart;
import com.myapp.shoppingmall.ado.ProductRepository;
import com.myapp.shoppingmall.entitys.Product;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ProductRepository productRepo;

	@GetMapping("/add/{id}")	// 카트에 담는 과정???
	public String add(@PathVariable int id, HttpSession session, Model model) {
		
		Product product = productRepo.getById(id);
		
		if(session.getAttribute("cart") == null) {	// 카트에 입력된 값이 없을 경우
			HashMap<Integer, Cart> cart = new HashMap<>();		
			cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
			session.setAttribute("cart", cart);
		} else {
			@SuppressWarnings("unchecked")
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			if(cart.containsKey(id)) {	// 같은 상품id가 이미 카트에 있을 경우
				int qty = cart.get(id).getQuantitiy();
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), qty, product.getImage()));
			} else {		// 같은 상품id가 이미 카트에 없을 경우
				cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
				session.setAttribute("cart", cart);
			}
			
		}
		
		
		return "";
	}
}
