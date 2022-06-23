package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void DefaultContent(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("title", "환영합니다.자신만의 블로그를 운영하세요!");
		map.put("logo", "/assets/images/spring-logo.jpg");
		
		sqlSession.insert("blog.default", map);
	}

	public BlogVo select(String id) {
		return sqlSession.selectOne("blog.select", id);
	}

	public BlogVo findBlog(String id) {
		return sqlSession.selectOne("blog.findBlog", id);
	}

	public List<CategoryVo> findCategory(String id) {
		return sqlSession.selectList("blog.findCategory", id);
	}

	public List<PostVo> findPost(Map map) {
		return sqlSession.selectList("blog.findPost", map);
	}

	public PostVo getPost(Map map) {
		return sqlSession.selectOne("blog.getPost", map);
	}

	public BlogVo findBasic(String id) {
		return sqlSession.selectOne("blog.findBasic", id);
	}

	public void updateBasic(BlogVo blogVo) {
		sqlSession.update("blog.updateBasic", blogVo);
	}

	public List<CategoryVo> findCategoryPost(String id) {
		return sqlSession.selectList("blog.findCategoryPost", id);
	}

	public void insertCategory(CategoryVo categoryVo) {
		sqlSession.insert("blog.insertCategory", categoryVo);
	}
	
	/** 일반적으로 안되서 Map으로 바꿔서 넣으니까 됨 이유는 아직 잘 모름.**/
	public boolean deleteCategory(String id, Long no) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("no", no);
		return sqlSession.delete("blog.deleteCategory", map) == 1;
	}

	public List<CategoryVo> postWriteCategoryOption(String id) {
		return sqlSession.selectList("blog.postWriteCategoryOption", id);
	}

	public void writePost(String id, PostVo postVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("postVo", postVo);
		sqlSession.insert("blog.writePost", map);
	}

	




}
