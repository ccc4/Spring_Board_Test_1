<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/member/modify" method="POST">
	<div>id: <input type="text" name="id" value="${sessionScope.user.id }" disabled></div>
	<div>pw: <input type="text" name="pw" value="${sessionScope.user.pw }"></div>
	<div>name:<input type="text" name="name" value="${sessionScope.user.name }"></div>
	<input type="submit" value="수정">
</form>
</body>
</html>