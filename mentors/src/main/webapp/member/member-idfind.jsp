<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div  style="border: solid; width: 500px; height: 80px; margin: auto; padding: 20 20 20 20; text-align: center; margin-top: 250px">
	<div>
		아이디 찾기
	</div>
		email :
		<input type="text" name="email" id="email">
		<button type="submit" onclick="idFind()">아이디 찾기</button>
	
		<br><span id="id"></span>
	
</div>
<script type="text/javascript">
	function idFind() {
		let email = document.getElementById("email").value;
		let id = document.getElementById("id");
		let xhr = new XMLHttpRequest();
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4&&xhr.status==200){
			let result = JSON.parse(xhr.responseText);
				id.innerHTML= result.id;
			}
		}
		xhr.open("get","${pageContext.request.contextPath}/FindIdController.do?email="+email);
		xhr.send();
	}
</script>