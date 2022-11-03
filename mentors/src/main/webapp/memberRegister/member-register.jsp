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
  <link href="member-register/member-register.css" rel="stylesheet">
</head>
<body>
<div class="container pt-3">
<form action="doJoin" method="POST" class="joinForm" onsubmit="DoJoinForm__submit(this); return false;">                                                                              
      <h2>회원가입</h2>
      <div class="textForm">
        <input name="id" type="text" class="id" placeholder="아이디"></input>
      </div>
      <div class="textForm">
        <input name="password" type="password" class="pw" placeholder="비밀번호">
      </div>
       <div class="textForm">
        <input name="pwconfirm" type="password" class="pw" placeholder="비밀번호 확인">
      </div>
      <div class="textForm">
        <input name="nickName" type="text" class="nickname" placeholder="닉네임">
      </div>
       <div class="textForm">
        <input name="email" type="email" class="email" placeholder="이메일">
      </div>
      <div class="textForm">
        <input name="address" type="text" class="nickname" placeholder="주소">
      </div>
      	
      	<h4>
      	 <b>관심분야</b>
      	</h4>
      <div class="selectForm">
        <input name="interest" type="radio" class="select" value="프로그래밍">프로그래밍 
        <input name="interest" type="radio" class="select" value="자격증">자격증
        <input name="interest" type="radio" class="select"  value="취업" >취업
      </div>
      <input type="submit" class="btn" value="J O I N"/>
    </form>
</div>
</body>
</html>