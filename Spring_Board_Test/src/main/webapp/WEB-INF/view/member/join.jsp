<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/member/join" method="POST">
	<div>id:<input type="text" name="id"></div>
	<div>pw:<input type="password" name="pw"></div>
	<div>name:<input type="text" name="name"></div>
	<input type="submit" value="가입">
</form>

</body>
</html>