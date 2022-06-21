<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog_header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content" style="height: 300px;">
					<c:if test="${!empty postVo.title }">
						<h4>${postVo.title }  / 카테고리 - ${postVo.name } /</h4>
					</c:if>
					<p>${fn:replace(postVo.contents, newLine, "<br>")}<p>
				</div>
				<ul class="blog-list">
					<li>카테고리 포스트 목록 </li>
					<c:forEach items="${postList }" var="post" varStatus="status">
						<c:choose>
							<c:when test="${post.no eq postVo.no }">
								<li style="color: black; font-weight: bold;"><a>${post.title } - 현재글</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${post.categoryNo}/${post.no}">${post.title }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>