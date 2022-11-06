<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table">
	<tr>
		<td>글번호 ${tipsPostVO.postNo}</td>
		<td>카테고리 ${tipsPostVO.category}</td>
		<td>제목 : ${tipsPostVO.title}</td>
		<td>작성자 ${tipsPostVO.memberVO.nickName}</td>
		<td>조회수 :${tipsPostVO.hits}</td>
		<td>작성일 :${tipsPostVO.timePosted}</td>
	</tr>

	<tr>
		<td colspan="5"><pre>
		<font size="4">
		내용 : ${tipsPostVO.content}
		</font>
		</pre></td>
	</tr>

	<c:if test="${sessionScope.mvo.id==tipsPostVO.memberVO.id}">
		<tr>
			<td colspan="5">
				<form action="TipsBoardUpdatePostFormController.do" id="updateForm">
					<input type="hidden" name="postNo" value="${tipsPostVO.postNo }">
				</form>

				<form action="TipsBoardDeletePostController.do" id="deleteForm">
					<input type="hidden" name="postNo" value="${tipsPostVO.postNo}">

				
				</form>

				<button type="submit" onclick="updatePost()">수정</button>&nbsp;&nbsp;
				<button type="submit" onclick="deletePost()">삭제</button>
			

				<script>
				 function deletePost(){
					 let result=confirm("삭제하시겠습니까?");
					 if(result)
						 document.getElementById("deleteForm").submit();
				 }
				 
				 function updatePost(){
					 let result=confirm("수정하시겠습니까?");
					 if(result)
						 document.getElementById("updateForm").submit();
				 }
				</script>		

			</td>
		</tr>
	</c:if>
댓글은 게시판이랑 같음
1. 작성자/댓글내용/작성일에 대한 댓글 리스트
2. 댓글 쓸수 있는 작성 폼
3. 삭제랑 수정

</table>
