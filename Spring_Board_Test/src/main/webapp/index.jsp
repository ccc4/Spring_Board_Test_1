<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${empty sessionScope.user }">
	<div>로그인하세요.</div>
	<div>
		<input type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath }/member/login'">
		<input type="button" value="회원가입" onclick="location.href='${pageContext.request.contextPath }/member/join'">
	</div>
</c:if>
<c:if test="${!empty sessionScope.user }">
	<div>${sessionScope.user.name } 님, 안녕하세요</div>
	<div>
 		<input type="button" value="정보수정" onclick="location.href='${pageContext.request.contextPath }/member/modify'">
		<input type="button" value="로그아웃" onclick="location.href='${pageContext.request.contextPath }/member/logout'">
	</div>
</c:if>

<div><input type="button" value="게시판" onclick="location.href='${pageContext.request.contextPath }/board'"></div>

</body>
</html>