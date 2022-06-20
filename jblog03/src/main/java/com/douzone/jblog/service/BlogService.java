package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;

	public void DefaultContent(String id) {
		blogRepository.DefaultContent(id);
	}

	public ModelMap findAll(String id, Long categoryNo, Long postNo) {
		ModelMap modelmap = new ModelMap();
		Map map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("categoryNo",categoryNo);
		map.put("postNo",postNo);
		
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
}
