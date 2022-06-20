package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
