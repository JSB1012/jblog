package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(CategoryVo categoryVo, String id) {
		categoryVo.setBlog_id(id);
		sqlSession.insert("category.insert", categoryVo);
	}

}
