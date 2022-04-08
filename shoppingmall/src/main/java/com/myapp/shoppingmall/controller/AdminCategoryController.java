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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.shoppingmall.ado.CategoryRepository;
import com.myapp.shoppingmall.entitys.Category;
import com.myapp.shoppingmall.entitys.Page;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	private String index(Model model) {
		List<Category> categories = categoryRepo.findAllByOrderBySortingAsc();
		model.addAttribute("categories", categories);
		return "admin/categories/index";
	}
	
	@GetMapping("/add")	// 	/admin/categories/add
	public String add(@ModelAttribute Category category) {
		return "admin/categories/add";
	}
	
	@PostMapping("/add") 
	public String add(@Valid Category category, BindingResult bindingResult, RedirectAttributes attr) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/categories/add";
		// 유효성 검사 통과 시
		attr.addFlashAttribute("message", "성공적으로 페이지 추가됨");
		attr.addFlashAttribute("alertClass", "alert-success");	// 부트스트랩 경고창(color: succeess)
		
		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category nameExist = categoryRepo.findByName(category.getName());	// DB의 name을 검색하여 있으면 Category에 저장
		
		if (nameExist != null) {	// 동일한 이름의 category가 이미 있을 경우
			attr.addFlashAttribute("message", "입력한 category가 이미 존재합니다.");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("category", category);	// 입력된 데이터가 저장된 페이지객체를 그대로 유지함
			
		} else {
			category.setSlug(slug);	// 소문자, '-' 수정 된 slug를 업데이트
			category.setSorting(100);	// 기본 sorting값
			
			categoryRepo.save(category);
		}
		return "redirect:/admin/categories/add";	// post-redirect-get
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Category category = categoryRepo.getById(id);	// 테이블에서 id로 category검색
		model.addAttribute("category", category);
		return "admin/categories/edit";	// 수정 페이지로 보냄
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Category category, BindingResult bindingResult, RedirectAttributes attr) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/categories/edit";
		
		attr.addFlashAttribute("message", "성공적으로 category 수정됨");
		attr.addFlashAttribute("alertClass", "alert-success");	// 부트스트랩 경고창(color: succeess)
		
		String slug = category.getName().toLowerCase().replace(" ", "-");
		
		Category nameExist = categoryRepo.findByName(category.getName());

		if (nameExist != null) {	// 동일한 slug가 존재하면 저장x
			attr.addFlashAttribute("message", "입력한 category가 이미 존재합니다.");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("category", category);
			
		} else {
			category.setSlug(slug);	// 소문자, '-' 수정 된 slug를 업데이트
			category.setSorting(100);
			
			categoryRepo.save(category);
		}
		return "redirect:/admin/categories/edit/" + category.getId();	// /edit/{id} 로 주소가 작성되어있으므로 id값도 같이 넘겨야함
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes attr) {
		categoryRepo.deleteById(id);

		attr.addFlashAttribute("message", "성공적으로 삭제되었습니다.");
		attr.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/admin/categories";
	}

    @PostMapping("/reorder")
    public @ResponseBody String reorder(@RequestParam("id[]") int[] id) {
        
        int count = 1;
        Category category;

        for (int categoryId : id) {
        	category = categoryRepo.getById(categoryId);	// DB에서 id로 category객체 검색
        	category.setSorting(count);				// setSorting에 count값을 넣어줌
        	categoryRepo.save(category); 	//sorting 값을 순서대로 저장
            count++;
        }
        return "ok";	// view페이지가 아니라 문자열 ok로 리턴
    }
}
