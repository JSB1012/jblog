package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller

@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
		
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;

	/** BLOG 메인 페이지 **/
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, 
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, ModelMap model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		model.addAllAttributes(blogService.findAll(id, categoryNo, postNo));
		//model.putAll 사용도 O
		return "blog/main";
	}
	
	/** BLOG 기본 관리자 화면 **/
	@RequestMapping(value="/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		BlogVo blogVo = blogService.findBasic(id);
		model.addAttribute("BlogVo", blogVo);
		return "blog/basic";
		
	}
	
	/** BLOG 기본 관리자 화면 Update **/
	@RequestMapping(value="/admin/basic", method= RequestMethod.POST)
	public String adminBasic(
			@PathVariable String id, String title,
			@RequestParam("logo-file") MultipartFile multipartFile,
			@AuthUser UserVo authUser) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		
		String url = fileUploadService.restoreImage(multipartFile);
		// WEB-INF/spring-servlet.xml 맵핑 확인!
		
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(url);
		
		blogService.updateBasic(blogVo);
		
		return "redirect:basic";	
	}
	
	/** Category 관리자 화면 **/
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(
			@PathVariable("id") String id, 
			@AuthUser UserVo authUser, Model model) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		
		List<CategoryVo> categoryList = blogService.findCategoryPost(id);
		model.addAttribute("categoryList", categoryList);
		return "blog/category";
	}
	
	/** Category 관리자 화면 카테고리 추가 **/
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(
			@PathVariable("id") String id, 
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			Model model) {
		// 매개변수 일일이 받은 경우 < - > Vo 객체로 바로 받는 것도 가능 -> 생성자로 따로 Vo 객체 생성 필요 없게 됨
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlog_id(id);
		categoryVo.setName(name);
		categoryVo.setDescription(description);
		
		blogService.insertCategory(categoryVo);
		return "redirect:category";
	}
	
	/** Category 관리자 화면 카테고리 삭제 **/
	@RequestMapping("/admin/category/delete/{no}")
	public String adminCategory(
			@PathVariable String id, 
			@PathVariable ("no") Long no, 
			@AuthUser UserVo authUser) {
		if (authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		
		blogService.deleteCategory(id, no);
		return "redirect:/" + id + "/admin/category";
	}
	
	/** Post 관리자 화면 글 작성 및 카테고리 목록 가져오기 위한 메서드 **/
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String postWriteCategoryOption(
			@PathVariable String id, 
			Model model, 
			@AuthUser UserVo authUser) {
		if (authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		List<CategoryVo> category = blogService.postWriteCategoryOption(id);
		model.addAttribute("category", category);
		return "blog/write";
	}
	
	/** Post 관리자 화면 글 작성 **/
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String writePost(@PathVariable("id") String id, @AuthUser UserVo authUser, PostVo postVo) {
		// 매개변수 객체로 받은 경우
		
		if (authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/main";
		}
		blogService.writePost(id, postVo);
		
		return "redirect:/" + id;
	}
	
	
			
}