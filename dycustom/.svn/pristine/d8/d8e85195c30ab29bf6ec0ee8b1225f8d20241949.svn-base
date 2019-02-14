<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel= "stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav.css">
<link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
</head>
<body>

	<div class="nav-body-div">
		<div class="nav-main-image">
			<span onclick="location.href='${pageContext.request.contextPath}/views/productList.do'">shoes</span>
		</div>
		<div class="nav_menu" id="nav_menu">
			 <ul>
			 <c:choose>
		 		<c:when test = "${sessionScope.loginSession == null || sessionScope.loginSession == ''}">
					<li class=""><a href="${pageContext.request.contextPath}/views/login.do">로그인</a></li>
					<li class="nav-item"><a href="${pageContext.request.contextPath}/views/customInsert.do">회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">${sessionScope.custom.cId}님 환영합니다.</li>
					<li class="nav-item"><a href="${pageContext.request.contextPath}/views/customUpdate.do">회원정보 수정</a></li>
					<li class="nav-item"><a href="${pageContext.request.contextPath}/views/cart.do">장바구니</a></li>
					<li class="nav-item"><a href="${pageContext.request.contextPath}/views/orderList.do">주문내역</a></li>
					<li class="nav-item"><a href="${pageContext.request.contextPath}/views/logout.do">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
				<li class="nav-item"><a href="${pageContext.request.contextPath}/views/productList.do?pageNum=${sessionScope.pageNum}&keyWord=${sessionScope.keyWord}">목록으로</a></li>
			</ul>
		</div>
	</div>
</body>
</html>