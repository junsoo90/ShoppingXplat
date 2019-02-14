<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- goList -->
<script>
	function goList(){
		document.location.href="productList.do";
	}
	
	function goOrderList(){
		document.location.href="orderList.do";
	}
</script>

<style>

	html,
	body {
		margin:0;
		padding:0;
		height:100%;
		width: 100%;
	}
	
	.result-div{
		margin-left:40%;
		height: 100%;
		float: left;	
	}
	
	.order-result{	
		float:left;
		height: 70%;
		width: 100%;
	}
	
	.result-text{
		font-size: 20px;
	}
	
	.result-btn{
		margin: 10%;
		float: left;
	}
	button{
		font-size: 20px;
		margin: 10%;
	}
		
	.nav-div{
		 height:100px; 
	}
	
	
	.footer-div {
		width:100%;
		height:100px;
		bottom:0;
		float:left;
	}
</style>

<body>
	
	<div class="nav-div">
		<%@ include file="nav/nav.jsp"%>
	</div>
	
	<div class="order-result">
		<div class="result-div">
			<div class="result-img">
			<img src="${pageContext.request.contextPath}/img/374770_19176_2153.jpg" width="300" height="300"
					onclick="location.href='${pageContext.request.contextPath}/views/productList.do'">
			</div>
			<div class="result-text">
				<h1> 결제가 완료되었습니다. </h1>
			</div>
			
			<div class="result-btn">
				<input type="button" value="목록"
						onclick="goList();">
						
				<input type="button" value="구매목록"
						onclick="goOrderList();">
			</div>
		</div>
	</div>
	<div class="footer-div">
		<%@ include file = "footer/footer.jsp" %>
	</div>
	
</body>
</html>