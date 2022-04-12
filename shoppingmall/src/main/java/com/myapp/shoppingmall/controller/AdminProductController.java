package com.myapp.shoppingmall.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.shoppingmall.dao.CategoryRepository;
import com.myapp.shoppingmall.dao.ProductRepository;
import com.myapp.shoppingmall.entites.Category;
import com.myapp.shoppingmall.entites.Product;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping
	public String index(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		
		int perPage = 6;	// 한 페이지에 최대 6까지 출력
		Pageable pageable = PageRequest.of(page, perPage);	// 표시할 페이지, 한 페이지에 몇개(6개)
		// .data.domain.Page
		Page<Product> products = productRepo.findAll(pageable);
		List<Category> categories = categoryRepo.findAll();
		
		HashMap<Integer, String> cateIdAndName = new HashMap<>();
		for (Category category : categories) {
			cateIdAndName.put(category.getId(), category.getName());
		}
		
		model.addAttribute("products", products);
		model.addAttribute("cateIdAndName", cateIdAndName);
		
		// 페이지를 보여주기 위해 계산.
		long count = productRepo.count();	// 전체 갯수(long타입 리턴)
		double pageCount = Math.ceil((double)count / (double)perPage);	// 페이지 갯수 계산 (예- 13/6 = 2.12=> 0페이지 6개, 1)
		
		model.addAttribute("pageCount", pageCount);	// 총 페이지수
		model.addAttribute("perPage", perPage);		// 페이지당 표시 아이템 수
		model.addAttribute("count", count);			// 총 아이템 갯수
		model.addAttribute("page", page);			// 현재 페이지
		
		return "admin/products/index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute Product product, Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		// 상품을 추가하는 add 페이지에 상품객체와 상품의 카테고리를 선택할수있도록 카테고리 리스트도 전달
		return "admin/products/add";
	}
	
	@PostMapping("/add") 
	public String add(@Valid Product product, BindingResult bindingResult, MultipartFile file,
											RedirectAttributes attr, Model model) throws IOException {
		// 미리 id로 수정 전의 상품데이터를 불러옴
		Product currentProduct = productRepo.getById(product.getId());
		
		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryRepo.findAll();
			model.addAttribute("categories", categories);
			return "admin/products/add";	// 유효성검사 에러발생시 되돌아감
		}
		
		boolean fileOk = false;
		byte[] bytes = file.getBytes();	// 업로드된 img파일의 데이터
		String fileName = file.getOriginalFilename();	// 파일의 이름
		Path path = Paths.get("src/main/resources/static/media/" + fileName); // 파일을 저장할 위치와 이름까지
		
		if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
			fileOk = true;	// 확장자가 jpg, png인 파일만 true
		}
		// 성공적으로 추가됨
		attr.addFlashAttribute("message", "상품이 성공적으로 추가됨!");
		attr.addFlashAttribute("alertClass", "alert-success");
		// 슬러그 만들기
		String slug = product.getName().toLowerCase().replace(" ", "-");
		// 동일한 상품명이 있는지 검사
		Product productExists = productRepo.findByName(product.getName());
		
		if(!fileOk) {	// 파일 업로드가 안됐거나 확장자가 jpg, png가 아닌경우
			attr.addFlashAttribute("message", "이미지는 jpg나 png파일을 사용해주세요!");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
			
		} else if (productExists != null) {	// 동일한 상품명이 DB에 존재
			attr.addFlashAttribute("message", "이미 존재하는 상품명입니다.");
			attr.addFlashAttribute("alertClass", "alert-danger");
			attr.addFlashAttribute("product", product);
			
		} else {	// 상품과 이미지 파일을 저장함
			product.setSlug(slug);
			product.setImage(fileName);	// img는 파일의 이름만 입력(주소는 /media/폴더 이므로 동일)
			productRepo.save(product);
			
			Files.write(path, bytes);	// (저장주소, 데이터)
		}
		return "redirect:/admin/products/add";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		
		Product product = productRepo.getById(id);
		List<Category> categories = categoryRepo.findAll(); 
		
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "admin/products/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@Valid Product product, BindingResult bindingResult, MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) throws IOException {
        //우선 수정하기전의 상품의 객체를 DB에서 읽어오기 ( id 로 검색 )
		Product currentProduct = productRepo.getById(product.getId());

		if (bindingResult.hasErrors()) {
			List<Category> categories = categoryRepo.findAll();
			model.addAttribute("categories", categories);
			if(product.getImage() == null)	product.setImage(currentProduct.getImage());	// 저장된 이미지 불러오기
			return "/admin/products/edit";
		}

		boolean fileOk = false;
		byte[] bytes = file.getBytes(); // 업로드한 파일의 데이터
		String fileName = file.getOriginalFilename(); // 업로드한 파일의 이름
		Path path = Paths.get("src/main/resources/static/media/" + fileName); // 파일을 저장할 컨텍스트 안의 경로

		if (!file.isEmpty()) { // 새 이미지 파일이 있을경우
			if (fileName.endsWith("jpg") || fileName.endsWith("png")) { // 파일의 확장자 jpg , png
				fileOk = true;
			}
		} else { // 파일이 없을경우 ( 수정 이므로 이미지 파일이 없어도 OK )
			fileOk = true;
		}

		// 성공적으로 product 수정 되는 경우
		redirectAttributes.addFlashAttribute("message", "상품이 수정됨");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		String slug = product.getName().toLowerCase().replace(" ", "-");
		//제품이름을 수정했을 경우에 slug가 다름 제품과 같지 않는지 검사
		Product productExists = productRepo.findBySlugAndIdNot(slug, product.getId());

		if (!fileOk) { // file 업로드 안되거나 확장자가 틀림

			redirectAttributes.addFlashAttribute("message", "이미지는 jpg나 png를 사용해 주세요");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);

		} else if (productExists != null) { // 이미 등록된 상품 있음

			redirectAttributes.addFlashAttribute("message", "상품이 이미 있습니다. 다른것을 고르세요");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);

		} else { // 상품과 이미지 파일을 저장한다.

			product.setSlug(slug); // 슬러그 저장
			
			if (!file.isEmpty()) { // 수정할 이미지 파일이 있을 경우에만 저장(이때 이전 파일 삭제)
				Path currentPath = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
				Files.delete(currentPath);
				product.setImage(fileName);				
				Files.write(path, bytes);	//Files 클래스를 사용해 파일을 저장		
			} else {
				product.setImage(currentProduct.getImage());	
			}
			
			productRepo.save(product);
			
		}
		return "redirect:/admin/products/edit/" + product.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
		// id로 상품을 삭제하기 전 먼저 id로 상품객체를 불러와 이미지파일을 삭제한 후 삭제진행
		Product currentProduct = productRepo.getById(id) ;
		Path currentPath = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
		
		Files.delete(currentPath);		// 파일먼저 삭제
		productRepo.deleteById(id);		// 상품 삭제
		
		redirectAttributes.addFlashAttribute("message", "성공적으로 삭제 되었습니다.");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		
		return "redirect:/admin/products";
	}
	
	
}
