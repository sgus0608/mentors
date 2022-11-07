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
				<button type="submit" onclick="deletePost()">삭제</button> <script>
					function deletePost() {
						let result = confirm("삭제하시겠습니까?");
						if (result)
							document.getElementById("deleteForm").submit();
					}

					function updatePost() {
						let result = confirm("수정하시겠습니까?");
						if (result)
							document.getElementById("updateForm").submit();
					}
				</script>

			</td>
		</tr>
	</c:if>
</table>

<table>
	<c:forEach items="${commentList }" var="comment">
		<tr>
			<td>${comment.memberVO.nickName}</td>
			<td><pre>${comment.commentContent }</pre></td>
			<td>${comment.commentTimePosted }</td>
			<td><c:if test="${comment.memberVO.id==sessionScope.mvo.id }">
					<form id="updateCommentForm" action="" method="post">
						<input type="hidden" name="commentNo" value="${comment.commentNo}">
					</form>
					<form id="deleteCommentForm" action="" method="post">
						<input type="hidden" name="commentNo" value="${comment.commentNo}">
					</form>
					<button type="submit" onclick="updateComment()">수정</button>
					<button type="submit" onclick="deleteComment()">삭제</button>
				</c:if>
				</td>
		</tr>
	</c:forEach>
</table>
<tr>
	<td>
		<form action="TipsCommentWriteCommentController.do" method="post">
			<input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
			<textarea rows="2" class="form-control" name="commentContent"
				required="required" placeholder="댓글을 입력하세요"></textarea>
			<br>
			<button type="submit">등록</button>
		</form>
	</td>
</tr>



