<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>
	function goList(){
		var keyWord = "${sessionScope.keyWord}";
		document.location.href = "${pageContext.request.contextPath}/views/productList.do";
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
	.empty-div{
		min-height:100%;
		position:relative;
	}
	
	.empty-content-div{
		float:left;
		height: 70%;
		width: 100%;
	}
	
	.empty-main-div{
		margin-left:40%;
		height: 100%;
		float: left;
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
			<%@ include file = "nav/nav.jsp" %>
		</div>
		
		<div class="empty-content-div">
			<div class="empty-main-div"> 
			
				<div class="empty-image-div">
				<img src="${pageContext.request.contextPath}/img/2018081001669_0.jpg" width="300" height="300"
							onclick="location.href='${pageContext.request.contextPath}/views/productList.do'">
				</div>
					더이상 판매하지 않는 상품입니다.
				<div class="btn-productList">
					<input type="button" class="btn-div-option" value="목록"
						onclick="goList();">
				</div>
			</div>
		</div>
		
		<div class="footer-div">
			<%@ include file = "footer/footer.jsp" %>
		</div>
		
</body>
</html>