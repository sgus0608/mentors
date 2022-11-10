<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멘토스 회원가입</title>
  <style type="text/css">
  #deletebtn{
  	margin-left: 1000px;
  	 
  	
  }
  .deleteForm{
  	margin-bottom: 1000px;
  }
  </style>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link href="member-register.css" rel="stylesheet">
</head>
<body>
<div class="container pt-3">
<form action="${pageContext.request.contextPath}/UpdateMemberController.do" method="POST" class="joinForm" onsubmit="checkRegisterForm(event)">                                                                              
  <h2><b>개인정보 수정</b></h2>
      
      <div class="textForm">
      아이디
        <input id="memberId" name="id" type="text" class="id" required="required" onkeyup="checkId()" readonly="readonly" value="${mvo.id }">
      </div>
      
      <div class="textForm">
      비밀번호
        <input id="password1" name="password" type="password" class="pw" required="required" onkeyup="chkPW()" value="${mvo.password }"> 
        <span id="checkPw"></span>
      </div>
       <div class="textForm">
       
        <input id = "password2" name="pwconfirm" type="password" class="pw" required="required" onkeyup="checkPw()">
        <span id="checkPassword"></span>
      </div>
      <div class="textForm">
      닉네임
        <input name="nickName" type="text" class="nickname" required="required" value="${mvo.nickName }">
      </div>
       <div class="textForm">
       이메일
        <input id="email" name="email" type="email" class="email" required="required" onkeyup="checkEmail()" value="${mvo.email }">
      	<span id="checkEmail"></span>
      </div>
      <div class="textForm">
      주소
        <input name="address" type="text" class="nickname"  required="required" value="${mvo.address }">
      </div>
      	<h4><b>관심분야</b></h4>
      <div class="selectForm">
        <input name="interest" type="radio" class="select" value="프로그래밍" <c:if test="${mvo.interest eq '프로그래밍'}">checked</c:if>/> 프로그래밍
        <input name="interest" type="radio" class="select" value="자격증"   <c:if test="${mvo.interest eq '자격증'}">checked</c:if>/>자격증
        <input name="interest" type="radio" class="select"  value="취업" <c:if test="${mvo.interest eq '취업'}">checked</c:if>/>취업
      </div>
      <input type="submit" class="btn" value="개인정보 수정"/>
    </form>
    <form id="deleteForm" action="${pageContext.request.contextPath}/DeleteMemberController.do" method="post"> 
    
	    <button type="submit" class="btn" id="deletebtn" onclick="deleteMember()" style="width: 100px">회원 탈퇴</button> 
	    <input type="hidden" name="id" value=${mvo.id }>
  
    </form>
    <br>
    <script type="text/javascript">
    	function deleteMember() {
			if(confirm("정말 회원 탈퇴 하시겠습니까?")){
				document.getElementById("deleteForm").submit();
			}
		}
    	function checkRegisterForm(event){
    	let password1 = document.getElementById("password1").value;
		let password2= document.getElementById("password2").value;
		let email = document.getElementById("email").value;
		let pw = document.getElementById("password1").value;
		let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/
		let num = pw.search(/[0-9]/g);
    	let eng = pw.search(/[a-z]/ig);
    	let spe = pw.search(/[`~!@@#$%^&*|\\\'\";:\/?]/gi);
    		if(password1 != password2){
    			alert("비밀번호가 일치하지 않습니다");
    			event.preventDefault();
    		}else if(exptext.test(email)==false){
    			alert("이메일 형식에 맞게 입력해 주세요");;
    			event.preventDefault();
    		}else if(pw.length<8 || pw.length>20){
    			alert("비밀번호 형식에 맞게 입력해주세요");
    			event.preventDefault();
    		}else if(pw.search(/\s/)!= -1){
    			alert("비밀번호 형식에 맞게 입력해주세요");
    			event.preventDefault();
    		}else if(num<0||eng<0||spe<0){
    			alert("비밀번호 형식에 맞게 입력해주세요");
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
    	
    </script>
</div>
</body>
</html>