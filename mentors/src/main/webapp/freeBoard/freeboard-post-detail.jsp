<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table">
	<tr>
		<td>글번호 ${postVO.postNo}</td>
		<td>제목 ${postVO.title}</td>
		<td>작성자 ${postVO.memberVO.nickName}</td>
		<td>조회수 ${postVO.hits}</td>
		<td>작성일 ${postVO.timePosted}</td>
	</tr>
	<tr>
		<td colspan="5">
		<pre>
			<font size="4">${postVO.content}</font>
		</pre>
	</tr>

</table>
<c:if test="${postVO.memberVO.id==mvo.id}">
	<button type="button" onclick="modify()">수정</button>
	<button>삭제</button>
	<form id="updateForm" action="FreeBoardUpdatePostFormController.do?postNo=${postVO.postNo}" method="post">
		<input type="hidden" name="no" value="${postVO.postNo}">
	</form>
	<script>
	function modify(){
		let result=confirm("수정하시겠습니까?");
		if(result)
			document.getElementById("updateForm").submit();
	}
	</script>
</c:if>