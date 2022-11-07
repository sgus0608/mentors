<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class="table">
  <tr>
    <td>글번호 ${qnaPostVO.postNo}</td>
    <td>카테고리 ${qnaPostVO.category}</td>
    <td>제목 ${qnaPostVO.title}</td>
    <td>작성자 ${qnaPostVO.memberVO.nickName}</td>
    <td>조회수 ${qnaPostVO.hits}</td>
    <td>작성일 ${qnaPostVO.timePosted}</td>
  </tr>
  <tr>
    <td colspan="5">
  	  <pre><font size="4">내용 : ${qnaPostVO.content}</font></pre>
  	</td>
  </tr>

  <c:if test="${sessionScope.mvo.id==qnaPostVO.memberVO.id}">
  <tr>
    <td colspan="5">
      <form action="QnABoardUpdatePostFormController.do" id="updateForm" method="post">
       	<input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
      </form>
      
      <form action="QnABoardDeletePostController.do" id="deleteForm" method="post">
      	 <input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
      </form>
    
	      <button type="button" onclick="updatePost()">수정</button>&nbsp;&nbsp;
	      <button type="button" onclick="deletePost()">삭제</button>
	  
	  <script type="text/javascript">
	    function updatePost() {
			if(confirm("수정하시겠습니까?")){
				document.getElementById("updateForm").submit();
			}
		}
	    function deletePost() {
			if(confirm("삭제하시겠습니까?")){
				document.getElementById("deleteForm").submit();
			}
		}
	  </script>
	</td>
  </tr>
  </c:if>
</table>
<table>
  <c:forEach items="${commentList}" var="comment">
  <tr>
  	<td>${comment.memberVO.nickName}</td>
  	<td><pre>${comment.commentContent}</pre></td>
  	<td>${comment.commentTimePosted}</td>
  	<td>
  	<c:if test="${comment.memberVO.id == sessionScope.mvo.id}">
	  <form id="updateCommentForm" action="QnACommentUpdateCommentController.do" method="post">
	  	<input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
	  	<input type="hidden" name="commentNo" value="${comment.commentNo}">
	  </form>
	  <form id="deleteCommentForm" action="QnACommentDeleteCommentController.do" method="post">
	    <input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
	    <input type="hidden" name="commentNo" value="${comment.commentNo}">
	  </form>
	  <button type="button" onclick="updateComment()">수정</button>
	  <button type="button" onclick="deleteComment()">삭제</button>
	  <form action="/board/addReply.kh" method="post">
	  <input type="hidden" name="refBoardNo" value="${board.boardNo }">
	  </form>
	  
	  <script type="text/javascript">
	    function deleteComment(){
			if(confirm("삭제하시겠습니까?")){
				document.getElementById("deleteCommentForm").submit();
				
			}
		}
	    function updateComment() {
			if(confirm("수정하시겠습니까?")){
				document.getElementById("updateCommentForm").submit();
			}
		}
	  </script>
  	</c:if>
	</td>  	
  </tr>
  </c:forEach>
</table>
<form action="QnACommentWriteCommentController.do" method="post">
  <input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
  <textarea rows="2" class="form-control" name="commentContent" required="required" placeholder="댓글을 입력하세요"></textarea>
  <button type="submit">등록</button>
</form>


















