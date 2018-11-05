<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/board/write" method="POST">
	<div><input type="text" name="title" placeholder="제목"></div>
	<div><textarea name="contents" placeholder="내용"></textarea></div>
	<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath }/board'"> 
	<input type="submit" value="작성">
</form>

</body>
</html>