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

import com.myapp.shoppingmall.ado.PageRepository;
import com.myapp.shoppingmall.entitys.Page;

@Controller
@RequestMapping("/admin/pages")
public class AdminPageController {
	
	@Autowired
	private PageRepository pageRepo;
	
	@GetMapping
	public String index(Model model) {
		List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
		model.addAttribute("pages", pages);
		return "admin/pages/index";
	}
	
	@GetMapping("/add")	// 	/admin/pages/add
	public String add(@ModelAttribute Page page) {
//		model.addAttribute("page", new Page());
		return "admin/pages/add";
	}
	
	// model은 요청이 그대로 들어갈때만 사용할 수 있으므로 여기서는 RedirectAttributes 사용
	@PostMapping("/add") 
	public String add(@Valid Page page, BindingResult bindingResult, RedirectAttributes attr) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/pages/add";
		// 유효성 검사 통과 시
		attr.addFlashAttribute("message", "성공적으로 페이지 추가됨");
		attr.addFlashAttribute("alertClass", "alert-success");	// 부트스트랩 경고창(color: succeess)
		
		// 슬러그 검사 (슬러그 미입력시 title의 소문자로 하고, 공백은 - 로 대체), 입력시 소문자공백은 -로 대체
		String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug();
		Page slugExist = pageRepo.findBySlug(slug);	// 슬러그는 유일한 값이어야하므로 유일한지 검사. 슬러그로 DB검색 후 있으면 Page로 리턴
		
		if (slugExist != null) {	// 동일한 slug가 존재하면 저장x
			attr.addFlashAttribute("message", "입력한 slug가 이미 존재합니다.");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("page", page);	// 입력된 데이터가 저장된 페이지객체를 그대로 유지함
			
		} else {
			page.setSlug(slug);	// 소문자, '-' 수정 된 slug를 업데이트
			page.setSorting(100);	// 기본 sorting값
			
			pageRepo.save(page);	// 위의 과정은 page를 저장하기 전 유효성을 검사한 것이므로 저장되는건 page객체이다
		}
		return "redirect:/admin/pages/add";	// post-redirect-get(새로고침시 다시 반복된요청이 가는걸 방지)
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Page page = pageRepo.getById(id);	// 테이블에서 id로 page검색
		model.addAttribute("page", page);	// 수정페이지에 page정보 객체를 전달
		return "admin/pages/edit";	// 수정 페이지로 보냄
	}
	
	// 수정하기를 통해 작성한 값을 검사 후 업데이트
	@PostMapping("/edit")
	public String edit(@Valid Page page, BindingResult bindingResult, RedirectAttributes attr) {
		//유효성검사 결과 에러가 있으면 다시 돌아감
		if (bindingResult.hasErrors()) return "admin/pages/edit";
		
		attr.addFlashAttribute("message", "성공적으로 수정됨");
		attr.addFlashAttribute("alertClass", "alert-success");	// 부트스트랩 경고창(color: succeess)
		
		// 슬러그 검사
		String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-") : page.getSlug();
		Page slugExist = pageRepo.findBySlugAndIdNot(slug, page.getId());	// 슬러그로 DB검색 후 있으면 Page로 리턴 - 단, 현재 id의 slug는 중복에서 제외
		
		if (slugExist != null) {	// 동일한 slug가 존재하면 저장x
			attr.addFlashAttribute("message", "입력한 slug가 이미 존재합니다.");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("page", page);
			
		} else {
			page.setSlug(slug);	// 소문자, '-' 수정 된 slug를 업데이트
			page.setSorting(100);
			
			pageRepo.save(page);
		}
		return "redirect:/admin/pages/edit/" + page.getId();	// /edit/{id} 로 주소가 작성되어있으므로 id값도 같이 넘겨야함
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes attr) {
		pageRepo.deleteById(id);
		
		attr.addFlashAttribute("message", "성공적으로 삭제되었습니다.");
		attr.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/admin/pages";		// 인덱스 페이지로 돌아감
	}

    @PostMapping("/reorder")
    public @ResponseBody String reorder(@RequestParam("id[]") int[] id) {
        
        int count = 1;
        Page page;

        for (int pageId : id) {
            page = pageRepo.getById(pageId);	// DB에서 id로 page객체 검색
            page.setSorting(count);				// setSorting에 count값을 넣어줌
            pageRepo.save(page); 	//sorting 값을 순서대로 저장
            count++;
        }
        return "ok";	// view페이지가 아니라 ok문자열로 리턴
    }
    
}
