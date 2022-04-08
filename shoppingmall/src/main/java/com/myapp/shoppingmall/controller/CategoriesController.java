package com.myapp.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myapp.shoppingmall.ado.CategoryRepository;
import com.myapp.shoppingmall.ado.ProductRepository;
import com.myapp.shoppingmall.entitys.Category;
import com.myapp.shoppingmall.entitys.Product;

@Controller
@RequestMapping("/category")
public class CategoriesController {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * 입력된 slug 카페고리별로 상품리스트 표시(페이징 포함)
	 * @param slug 카테고리 slug
	 * @param page 표시할 페이지 번호
	 * @return products 페이지
	 * */
	@GetMapping("/{slug}")
	public String category(@PathVariable String slug, Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		
		int perPage = 6;	// 한 페이지에 최대 6까지 출력
		Pageable pageable = PageRequest.of(page, perPage);	// 표시할 페이지, 한 페이지에 몇개(6개)
		long count = 0;
		
		// 카테고리 선택(all, 그외 개별카테고리)
		if(slug.equals("all")) {		// all 카테고리 선택시 페이징
			Page<Product> products = productRepo.findAll(pageable);
			count = productRepo.count();	// 전체 제품 수
			
			model.addAttribute("products", products);	// 전체 제품들
			
		} else {	// 각 카테고리별 페이징
			Category category = categoryRepo.findBySlug(slug);
			if(category == null) {
				return "redirect:/";	// 카테고리가 없으면 홈으로
			}
			String categoryId = Integer.toString(category.getId());
			String categoryName = category.getName();
			List<Product> products = productRepo.findAllByCategoryId(categoryId, pageable);
			count = productRepo.countByCategoryId(categoryId);

			model.addAttribute("products", products);	// 선택한 카테고리의 제품들
			model.addAttribute("categoryName", categoryName);
		}
		
		List<Category> categories = categoryRepo.findAll();
		HashMap<Integer, String> cateIdAndName = new HashMap<>();
		for (Category category : categories) {
			cateIdAndName.put(category.getId(), category.getName());
		}
		
		// 페이지를 보여주기 위해 계산
		double pageCount = Math.ceil((double)count / (double)perPage);	// 페이지 갯수 계산 (예- 13/6 = 2.12=> 0페이지 6개, 1)
		
		model.addAttribute("pageCount", pageCount);	// 총 페이지수
		model.addAttribute("perPage", perPage);		// 페이지당 표시 아이템 수
		model.addAttribute("count", count);			// 총 아이템 갯수
		model.addAttribute("page", page);			// 현재 페이지
		
		return "products";
	}

}
