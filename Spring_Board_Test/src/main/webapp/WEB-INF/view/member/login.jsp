<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/member/login" method="POST">
	<div><input type="text" name="id" placeholder="아이디"></div>
	<div><input type="password" name="pw" placeholder="비밀번호"></div>
	<input type="submit" value="로그인">
</form>

</body>
</html>