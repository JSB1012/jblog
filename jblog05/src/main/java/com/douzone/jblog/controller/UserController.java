package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	/** 회원가입 화면 - @ModelAttribute 사용 UerVo 객체로 담기 위해  **/
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo){
		return"user/join";
	}
	
	/** 회원가입 시 자동으로 블로그생성과 카테고리 Default값 추가 **/
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo , BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		blogService.DefaultContent(userVo.getId());
		categoryService.DefaultContent(userVo.getId());
	
		return "user/joinsuccess";
	}
	/** Login, Logout : spring-servlet.xml Interceptor 처리 **/
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "user/logout";
	}
	
}
