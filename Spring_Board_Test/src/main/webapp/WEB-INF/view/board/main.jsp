<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<input type="button" value="메인" onclick="location.href='${pageContext.request.contextPath}/'">
<c:if test="${!empty sessionScope.user }">
	<input type="button" value="글쓰기" onclick="location.href='${pageContext.request.contextPath}/board/write'">
</c:if>
<c:if test="${empty sessionScope.user }">
	<input type="button" value="글쓰기" disabled>
</c:if>

<div>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>아이디</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="l" items="${p.list }">
				<tr>
					<td><p>${l.idx }</p></td>
					<td><a href="${pageContext.request.contextPath}/board/view/${l.idx }">${l.title }</a></td>
					<td><p>${l.id }</p></td>
					<td><p>${l.writeDate }</p></td>
					<td><p>${l.read_cnt }</p></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<nav style="text-align: center;">
	<ul class="pagination pagination-sm">
		<c:if test="${p.page != 1 }">
			<li><a href="${pageContext.request.contextPath }/board">처음</a></li>
		</c:if>
		<c:if test="${p.page == 1 }">
			<li class="disabled"><a href="#">처음</a></li>
		</c:if>
		
		<c:if test="${p.startPage != 1 }">
			<li><a href="${pageContext.request.contextPath }/board?page=${p.startPage - 1}">이전</a></li>
		</c:if>
		<c:if test="${p.startPage == 1 }">
			<li class="disabled"><a href="#">이전</a></li>
		</c:if>
		
		<c:forEach var="i" begin="${p.startPage }" end="${p.endPage }">
			<c:if test="${i == p.page }">
				<li class="active"><a href="#"><b>${i }</b></a></li>
			</c:if>
			<c:if test="${i != p.page }">
				<li><a href="${pageContext.request.contextPath }/board?page=${i}">${i }</a></li>
			</c:if>
		</c:forEach>
		
		<c:if test="${p.endPage != p.totalPage }">
			<li><a href="${pageContext.request.contextPath }/board?page=${p.endPage + 1}">다음</a></li>
		</c:if>
		<c:if test="${p.endPage == p.totalPage }">
			<li class="disabled"><a href="#">다음</a></li>
		</c:if>
		
		<c:if test="${p.page != p.totalPage }">
			<li><a href="${pageContext.request.contextPath }/board?page=${p.totalPage}">끝</a></li>
		</c:if>
		<c:if test="${p.page == p.totalPage }">
			<li class="disabled"><a href="#">끝</a></li>
		</c:if>
	</ul>
</nav>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>