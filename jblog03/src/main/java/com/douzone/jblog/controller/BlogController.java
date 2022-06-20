package com.douzone.jblog.controller;

import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;

@Controller

@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, ModelMap model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		model.putAll(blogService.findAll(id, categoryNo, postNo));
		return "blog/main";
	}
	
//	@RequestMapping("/admin/basic")
//	public String adminBasic(@PathVariable("id") String id) {
//		
//	}
}