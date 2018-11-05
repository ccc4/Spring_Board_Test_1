<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/board/modify" method="POST">
	<input type="hidden" name="idx" value="${board.idx }">
	<div><input type="text" name="title" value="${board.title }"></div>
	<div><textarea name="contents">${board.contents }</textarea></div>
	<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath }/board'">
	<input type="submit" value="수정">
</form>

</body>
</html>