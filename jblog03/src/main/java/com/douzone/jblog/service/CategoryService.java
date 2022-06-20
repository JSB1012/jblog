package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	/** Category Default값 지정후 categoryInsert() 실행 **/
	public void DefaultContent(String id) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setDescription("카테고리를 지정하지 않은 경우");
		categoryVo.setName("미분류");
		categoryInsert(categoryVo, id);
	}

	private void categoryInsert(CategoryVo categoryVo, String id) {
		categoryRepository.insert(categoryVo, id);
	}
}
