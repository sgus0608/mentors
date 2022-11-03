<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link href="member-register.css" rel="stylesheet">
</head>
<body>
<div class="container pt-3">
<form action="${pageContext.request.contextPath}/RegisterMemberController.do" method="POST" class="joinForm" onsubmit="checkRegisterForm(event)">                                                                              
  <h2><b>회원가입</b></h2>
      
      <div class="textForm">
        <input id="memberId" name="id" type="text" class="id" placeholder="아이디" required="required" onkeyup="checkId()"></input>
      	<span id="checkResult"></span> <br> 
      </div>
      
      <div class="textForm">
        <input id="password1" name="password" type="password" class="pw" placeholder="비밀번호"required="required" onkeyup="chkPW()">
        <span id="checkPw"></span>
      </div>
       <div class="textForm">
        <input id = "password2" name="pwconfirm" type="password" class="pw" placeholder="비밀번호 확인"required="required" onkeyup="checkPw()">
        <span id="checkPassword"></span>
      </div>
      <div class="textForm">
        <input name="nickName" type="text" class="nickname" placeholder="닉네임"required="required">
      </div>
       <div class="textForm">
        <input id="email" name="email" type="email" class="email" placeholder="이메일"required="required" onkeyup="checkEmail()">
      	<span id="checkEmail"></span>
      </div>
      <div class="textForm">
        <input name="address" type="text" class="nickname" placeholder="주소" required="required">
      </div>
      	<h4><b>관심분야</b></h4>
      <div class="selectForm">
        <input name="interest" type="radio" class="select" value="프로그래밍">프로그래밍 
        <input name="interest" type="radio" class="select" value="자격증">자격증
        <input name="interest" type="radio" class="select"  value="취업" >취업
      </div>
      <input type="submit" class="btn" value="J O I N"/>
    </form>
    <script type="text/javascript">
    	let checkFlag = false;
    	function checkRegisterForm(event){
    	let password1 = document.getElementById("password1").value;
		let password2= document.getElementById("password2").value;
    		if(checkFlag==false){
    			alert("아이디가 중복되었습니다");
    			event.preventDefault();
    		}else if(password1 != password2){
    			alert("비밀번호가 일치하지 않습니다");
    			event.preventDefault();
    		}
    	}
    	function checkEmail() {
			let email = document.getElementById("email").value;
			let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
			let checkEmailSpan = document.getElementById("checkEmail");
			if(exptext.test(email)==false){
				checkEmailSpan.innerHTML="<font color=pink>형식에 맞는 이메일을 입력해주세요</font>"
			}else{
				checkEmailSpan.innerHTML="<font color=green>올바른 이메일 입니다</font>"
			}
		}
    	function chkPW(){
    		let pw = document.getElementById("password1").value;
    		let num = pw.search(/[0-9]/g);
    		let eng = pw.search(/[a-z]/ig);
    		let spe = pw.search(/[`~!@@#$%^&*|\\\'\";:\/?]/gi);
    		let checkPwSpan = document.getElementById("checkPw");
    		if(pw.length<8 || pw.length>20){
    			checkPwSpan.innerHTML="<font color=pink>8자리 ~ 20자리 이내로 입력해주세요</font>"
    		}else if(pw.search(/\s/)!= -1){
    			checkPwSpan.innerHTML="<font color=pink>비밀번호는 공백 없이 입력해주세요</font>"
    		}else if(num<0||eng<0||spe<0){
    			checkPwSpan.innerHTML="<font color=pink>영문,숫자,특수문자를 혼합하여 입력해주세요</font>"
    		}else{
    			checkPwSpan.innerHTML="<font color=blue>올바른 형식입니다.</font>"
    		}
    	}
    	function checkPw(){
    		let password1 = document.getElementById("password1").value;
    		let password2= document.getElementById("password2").value;
    		let checkPasswordSpan = document.getElementById("checkPassword");
    		if(password1!=password2){
    			checkPasswordSpan.innerHTML = "<font color=pink>비밀번호가 일치하지 않습니다</font>" 
    		}else{
    			checkPasswordSpan.innerHTML = "<font color=green>비밀번호가 일치합니다</font>" 
    		}
    	}
    	function checkId(){
    		checkFlag=false;
    		let regx = /^[a-zA-Z0-9]*$/;
    		let memberId= document.getElementById("memberId").value;
    		let checkResultSpan = document.getElementById("checkResult");
    		if(!regx.test(memberId)){
    			checkResultSpan.innerHTML = "<font color=pink>아이디는 영어,숫자만 입력 가능합니다</font>";
    		}
    		else if(memberId.length<4){
    			checkResultSpan.innerHTML = "<font color=pink>아이디는 4자 이상</font>";
    		}else{
    			let xhr = new XMLHttpRequest();
    			xhr.onreadystatechange= function(){
    				if(xhr.readyState==4&&xhr.status==200){
    					if(xhr.responseText=="ok"){
    						checkResultSpan.innerHTML= "<font color= blue>아이디는 사용가능</font>";
    						checkFlag = true;
    					}
    					else{
    						checkResultSpan.innerHTML="<font color=red>중복된아이디</font>";
    					}
    				}
    			}
    			xhr.open("get","${pageContext.request.contextPath}/CheckIdController.do?id="+memberId);
    			xhr.send();
    		}
    	}
    </script>
</div>
</body>
</html>