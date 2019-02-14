<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src='https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.min.js'></script>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<link rel= "stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/customInfo.css?ver=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/searchCustom.css?ver=1">

<!DOCTYPE html>
<html>
<head>


<!-- idSearch -->
<script>
	function idSearch(){
		
		var blank_pattern = /\s | /gi ;
		
		var cName = $("#cName").val();
		var cPhone1 = $("#cPhone1").val();
		var cPhone2 = $("#cPhone2").val();
		var cPhone3 = $("#cPhone3").val();
	
		
		if( cName == null || cName == ""){
			alert("이름을 입력해주세요");
			return false;
		}

		if( cPhone1 == null || cPhone1== ""){
			alert("전화번호를 입력해주세요");
			return false;
		}
		if( cPhone2 == null || cPhone2 == ""){
			alert("전화번호를 입력해주세요");
			return false;
		}
		if( cPhone3 == null || cPhone3== ""){
			alert("전화번호를 입력해주세요");
			return false;
		}
		
		if (cName.replace(blank_pattern, '') == "") {
			alert("이름에 공백을 사용할 수 없습니다 ");
			return false;
		}

		if (cPhone1.replace(blank_pattern, '') == "") {
			alert("전화번호는 공백을 사용할 수 없습니다 ");
			return false;
		}
		if (cPhone2.replace(blank_pattern, '') == "") {
			alert("전화번호는 공백을 사용할 수 없습니다 ");
			return false;
		}
		
		if (cPhone3.replace(blank_pattern, '') == "") {
			alert("전화번호는 공백을 사용할 수 없습니다 ");
			return false;
		}
		
		var pattern = /^[0-9]*$/
		
		var specialPattern = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\*]+$/
		    		
		if (!specialPattern.test(cName)) {
   		    alert("이름은 영문자와 한글,숫자만을 입력하세요");
   		    return false;
    	}
		
      	if (!pattern.test(cPhone1)) {
    	    alert("전화번호는 숫자만 입력할 수 있습니다");
    	    $('#cPhone1').val(bankNum.replace(pattern,''));
    	    return false;
    	}
        if (!pattern.test(cPhone2)) {
	    	alert("전화번호는 숫자만 입력할 수 있습니다");
	    	 $('#cPhone2').val(bankNum.replace(pattern,''));
	    	return false;
	    }
        if (!pattern.test(cPhone3)) {
	    	alert("전화번호는 숫자만 입력할 수 있습니다");
	    	 $('#cPhone3').val(bankNum.replace(pattern,''));
	    	return false;
	    }
	        
        
        //alert(cName);
     	if(!fnChkByte(cName, 50,"이름"))
        	return false;
        if(!fnChkByte(cPhone1, 4,"전화번호"))
        	return false;
        if(!fnChkByte(cPhone2, 4,"전화번호"))
        	return false;
        if(!fnChkByte(cPhone3, 4,"전화번호"))
        	return false;
        
       
        
    	var data = {
    		cName: cName,
   			cPhone1: cPhone1,
   			cPhone2: cPhone2,
   			cPhone3: cPhone3,
   			customInfoCheck: "idSearch"
    	};
    	
    	$.ajax({
            async: true,
            type : 'POST',
            data : data,
            url : "idSearch.ajax",
            
            success : function(mav) {
            	
            	$("#login_result").hide();
            	$("#div_result").empty();
            	if(mav.msg == false){
            		location.href = mav.url;
            	}
            	else{
	               // var source = $("#idSearchTemplate").html();
	        	    //var template = Handlebars.compile(source);
	        	    alert(mav.msg);
	        	    var data  = [
	        	    	{
	        	    		result:mav.result,
	        	    		msg:mav.msg
	        	    	}
	        	    ];
	        	    
	        	    //$("#div_result").append(template(data));
            	}
            },
            error : function(request,status,error) {
           	   alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

            }
        });
	}
	
</script>


<!-- checkByte -->
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
			if(cvalue == '전화번호'){
				alert(cvalue + "은(는) 숫자 " + maxByte					
						+ "자를 초과 입력할 수 없습니다.");
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



<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="idSearch-div">
		<div class="nav-div">
			<%@ include file = "nav/nav.jsp" %>
		</div> 
		
		<div class="content-div">
			<div class="main-div">
				<h1>아이디 찾기</h1>
			
				<div class="input_area">
					<span>이름</span> 
					
					<div class="input-text-area">
						<input type="text" class="cName" id="cName"
							name="cName" required="required" />
					</div>
				</div>
		
				<div class="input_area">
					<span>전화번호</span>
					 
					<div class="input-text-area">
						<input type="text" class="phone" size="5"
							id="cPhone1" name="cPhone1" required="required" /> -
				
						<input type="text" class="phone" size="5"
							id="cPhone2" name="cPhone3" required="required" /> -
				
						<input type="text" class="phone" size="5"
							id="cPhone3" name="cPhone3" required="required" />
					</div>
				</div>
		
				<div id = "div_result" name = "div_result">
					
				</div>
				<script id="idSearchTemplate" type="text/x-handlebars-template">
    		{{#each .}}
				<div id="login_result" name="loin_result">
					<p style="color:#f00;">{{msg}}</p>
				</div>
   	 		{{/each}}
		</script>
				
				
				<div class="btn-div">
					<div class="btn">
						<input type="button"  value="아이디 찾기"
							onclick="idSearch();">
					</div>
					
					<div class="btn">
						<input type="button" value="비밀번호 찾기"
							onclick="location.href='${pageContext.request.contextPath}/passSearch.do'">
					</div>
					
					<div class="btn">
						<input type="button"  value="로그인 하러가기 "
							onclick="location.href='${pageContext.request.contextPath}/views/login.do'">
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