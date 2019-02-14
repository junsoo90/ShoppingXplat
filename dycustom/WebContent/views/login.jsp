<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script	src='https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.min.js'></script>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel= "stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css?ver=1">

<!-- checkLogin -->
<script type="text/javascript">
	var idcheck = false;
	var overlap = false;
	$("#login_result").remove();

	function checkLogin() {
		idcheck = true;
		//userid 를 param.
		var userId = $("#userId").val();
		var userPass = $("#userPass").val();
		var bol = true;

		if(!fnChkByte(userId,50,"아이디")){
  			return false;
  		}
		if(!fnChkByte(userPass,50,"비밀번호")){
  			return false;
  		}
		var form_data = {
			customInfoCheck : "customInfo",
			userId : userId,
			userPass : userPass
		};

		$.ajax({
			async : true,
			type : 'POST',
			data : form_data,
			url : "login.ajax",
			
			success : function(mav) {
				var Ca = /\+/g;
				if (mav.result == 1) {
					//alert(mav.url);
					alert( decodeURI(mav.msg).replace(Ca, " "));
					bol = true;
					location.href ='${pageContext.request.contextPath}'+ mav.url;            			
				}

				else {
					
					alert(decodeURI(mav.msg).replace(Ca, " "));
					
					bol = false;
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
		return bol;
	}
</script>



<!-- fnChkByte -->
<script>

	function fnChkByte(obj, maxByte, cvalue) {
		
		var str = obj.toString();
		//var str_len = str.length;

		var rbyte = 0;
		var rlen = 0;
		var one_char = "";
		var str2 = "";

		for (var i = 0; i < str.length; i++) {
			one_char = str.charAt(i);

			if (escape(one_char).length > 4) {
				rbyte += 3; //한글2Byte
			} else {
				rbyte++; //영문 등 나머지 1Byte
			}

			if (rbyte <= maxByte) {
				rlen = i + 1; //return할 문자열 갯수
			}
		}
		if (rbyte > maxByte) {
			if(cvalue == "핸드폰번호"){
				alert("핸드폰 번호는 4자 이하로 입력해 주세요");
			}
			else{
				
				alert(cvalue + "은(는) 한글 " + (maxByte / 2) +
					"자 / 영문 " + maxByte
					+ "자를 초과 입력할 수 없습니다.");
			}
			str2 = str.substr(0, rlen); //문자열 자르기
			obj.value = str2;
			//fnChkByte1(obj, maxByte);
			return false;
		}
		
		
			return true;
	}
</script>


<body>
	<div class="login-div">
		
		<div class="nav-div">
			<%@ include file="nav/nav.jsp"%>
		</div>
		
		<div class="content-div">
			<div class="login-main-div">
				<h1>로그인</h1>
				
				<div class="input_area">
					<span> 아이디 </span> 
					<div class="input-text-area">
						<input type="text" class="input" id="userId"
							name="userId" required="required" />
					</div>
				</div>
		
				<div class="input_area">
					<span>패스워드</span> 
					
					<div class="input-text-area">
						<input type="text" class="input" id="userPass"
							name="userPass" required="required" />
					</div>
				</div>
		
				<div id="div_result" name="div_result"></div>
		
				<div class="btn-div">
					<div class="btn">
						<input type="button" id="signin_btn" name="signin_btn"
							onclick="return checkLogin();" value="로그인" />
					</div>
		
					<div class="btn">
						<input type="button" value="아이디 찾기"
							onclick="location.href='${pageContext.request.contextPath}/idSearch.do'">
					</div>
		
					<div class="btn">
						<input type="button" value="비밀번호 찾기"
							onclick="location.href='${pageContext.request.contextPath}/passSearch.do'">
					</div>
					
					<div class="btn">
						<input type="button" value="회원가입"
							onclick="location.href='${pageContext.request.contextPath}/views/customInsert.do'">
					</div>
				</div>
		
			</div>
		</div>
		
		<div class="footer-div">
			<%@ include file = "footer/footer.jsp" %>
		</div>
	</div>
</body>
</html>