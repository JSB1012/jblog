package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public void DefaultContent(String id) {
		blogRepository.DefaultContent(id);
	}
	
	/** 블로그 Main화면 (Blog,Category,Post)Repository BlogRepository 한 곳에서 처리 **/
	public ModelMap findAll(String id, Long categoryNo, Long postNo) {
		
		ModelMap modelmap = new ModelMap();
		//Model은 인터페이스, ModelMap은 클래스 기능은 같고 개발자의 선호도에 따라 사용
		
		Map<String, Object> map = new HashMap<String, Object>();
		//Map<String, Object> map = new HashMap<>(); 타입 파라미터 생략 가능
		
		map.put("id",id);
		map.put("categoryNo",categoryNo);
		map.put("postNo",postNo);
		//put으로 데이터 추가 Key, Value 값
		
		BlogVo blogVo = blogRepository.findBlog(id);
		List<CategoryVo> categoryList = blogRepository.findCategory(id);
		List<PostVo> postList = blogRepository.findPost(map);
		PostVo postVo = blogRepository.getPost(map);

		modelmap.put("blogVo", blogVo);
		modelmap.put("categoryList", categoryList);
		modelmap.put("postList", postList);
		modelmap.put("postVo", postVo);

		return modelmap;
	}

	/** BLOG 기본 관리자 화면 **/
	public BlogVo findBasic(String id) {
		return blogRepository.findBasic(id);
	}
	
	/** BLOG 기본 관리자 화면 Update **/
	public void updateBasic(BlogVo blogVo) {
		blogRepository.updateBasic(blogVo);
	}
	
	/** BLOG Category 관리자 화면 **/
	public List<CategoryVo> findCategoryPost(String id) {
		return blogRepository.findCategoryPost(id);
	}

	/** BLOG Category 관리자 화면 카테고리 추가 **/
	public void insertCategory(CategoryVo categoryVo) {
		blogRepository.insertCategory(categoryVo);
	}
	
	/** BLOG Category 관리자 화면 카테고리 삭제 **/
	public void deleteCategory(String id, Long no) {
		blogRepository.deleteCategory(id,no);
	}
	
	/** Post 관리자 화면 글 작성 및 카테도길 목록 **/
	public List<CategoryVo> postWriteCategoryOption(String id) {
		return blogRepository.postWriteCategoryOption(id);
	}
	
	/** Post 관리자 화면 글 작성 **/
	public void writePost(String id, PostVo postVo) {
		blogRepository.writePost(id, postVo);
	}
	
	
	
	
	




	
	

}
