<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div>
		비밀번호 찾기
	</div>

		아이디 :
		<input type="text" name="id" id="id">
		email :
		<input type="text" name="email" id="email">
		<button type="submit" onclick="pwFind()">비밀번호 찾기</button>
	
		<br><span id="pw"></span>
	
</div>
<script type="text/javascript">
	function pwFind() {
		let id = document.getElementById("id").value;
		let email = document.getElementById("email").value;
		let pw = document.getElementById("pw");
		let xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4&&xhr.status==200){
			let result = JSON.parse(xhr.responseText);
				pw.innerHTML= result.pw;
			}
		}
		xhr.open("get","${pageContext.request.contextPath}/FindPwController.do?id="+id+"&email="+email);
		xhr.send();
	}
</script>