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
<table border="1">
	<tr>
		<td>${board.title }</td>
	</tr>
	<tr>
		<td>id: ${board.id } / 작성일: ${board.writeDate } / 조회수: ${board.read_cnt }</td>
	</tr>
	<tr>
		<td>${board.contents }</td>
	</tr>
	<tr>
		<td>
			<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath }/board'">

			<c:if test="${!empty sessionScope.user && sessionScope.user.id == board.id }" var="check">
				<input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/board/modify?idx=${board.idx }'">
				<input type="button" value="삭제" onclick="deleteConfirm()">
				<!-- <input type="button" value="수정" onclick="alert('준비중입니다.')">
				<input type="button" value="삭제" onclick="alert('준비중입니다.')"> -->
			</c:if>
			<c:if test="${!check }">
				<input type="button" value="수정" disabled>
				<input type="button" value="삭제" disabled>
			</c:if>
		
		</td>
	</tr>
</table>


<script type="text/javascript">
	function deleteConfirm(){
		var check = confirm("정말 삭제하시겠습니까?");
		if(check){
			location.href='${pageContext.request.contextPath}/board/delete?idx=${board.idx }';
		}
	}
</script>
</body>
</html>