<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
  <div class="col-12">
    <p style="font-size: 2rem; font-weight: bold">${tipsPostVO.title}</p>
  </div>
</div>
<div class="row">
  <div class="col-2" align="center">
    <b style="font-size: 1.3rem">글번호</b><br>${tipsPostVO.postNo}
  </div>
  <div class="col-2" align="center">
    <b style="font-size: 1.3rem">카테고리</b><br>${tipsPostVO.category}
  </div>
  <div class="col-2" align="center">
    <b style="font-size: 1.3rem">작성자</b><br>${tipsPostVO.memberVO.nickName}
  </div>
  <div class="col-2" align="center">
    <b style="font-size: 1.3rem">작성일</b><br>${tipsPostVO.timePosted}
  </div>
  <div class="col-2" align="center">
    <b style="font-size: 1.3rem">조회수</b><br>${tipsPostVO.hits}
  </div>
</div>
<hr>
<div class="row">
  <div class="col-12">
    <pre>${tipsPostVO.content}</pre>
  </div>
</div>
<c:if test="${tipsPostVO.memberVO.id == sessionScope.mvo.id}">
<div class="row">
  <div class="col-3 offset-9" align="right">
  <form id="updateForm" action="TipsBoardUpdatePostFormController.do" method="post">
    <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
  </form>
  <form id="deleteForm" action="TipsBoardDeletePostController.do" method="post">
    <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
  </form>
  <button class="btn btn-success" type="button" onclick="updatePost()">글수정</button>
  <button class="btn btn-success" type="button" onclick="deletePost()">삭제</button>
  <script type="text/javascript">
    function updatePost() {
	  if(confirm("수정하시겠습니까?"))
	    document.getElementById("updateForm").submit();
	  }
    function deletePost() {
      if(confirm("삭제하시겠습니까?"))
	    document.getElementById("deleteForm").submit();
	  }
  </script>
  </div>
</div>
</c:if>
<hr>
<div class="row">
  <div class="col-12">
    <c:forEach items="${commentList}" var="comment">
    <div class="row">
      <div class="col-3" align="center">
        ${comment.memberVO.nickName}
      </div>
      <div class="col-6">
        <pre>${comment.commentContent}</pre>
      </div>
      <div class="col-3">
        ${comment.commentTimePosted}
      </div>
    </div>
    <c:if test="${comment.memberVO.id == sessionScope.mvo.id}">
    <div class="row">
      <div class="col-6 offset-3" align="right">
        <form id="updateCommentForm_${comment.commentNo}" action="TipsCommentUpdateCommentController.do" method="post" style="display: none">
          <input type="hidden" name="commentNo" value="${comment.commentNo}">
          <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
          <textarea rows="3" class="form-control" name="updateCommentContent_${comment.commentNo}" placeholder="댓글을 입력하세요" required="required" style="margin: 10px 0 10px 0">${comment.commentContent}</textarea>
          <button class="btn btn-success" type="submit">수정완료</button>
          <button class="btn btn-success" type="reset">취소</button>
        </form>
      </div>
      <div class="col-3">
      <form id="deleteCommentForm_${comment.commentNo}" action="TipsCommentDeleteCommentController.do" method="post">
        <input type="hidden" name="commentNo" value="${comment.commentNo}">
        <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
      </form>
      <button class="btn btn-success" type="button" onclick="updateComment_${comment.commentNo}()">댓글수정</button>
      <button class="btn btn-success" type="button" onclick="deleteComment_${comment.commentNo}()">삭제</button>
      <script type="text/javascript">
        let updateBtnFlag_${comment.commentNo} = false;
        function updateComment_${comment.commentNo}() {
        	if(updateBtnFlag_${comment.commentNo} == false){
        		document.getElementById("updateCommentForm_${comment.commentNo}").style.display = "";
        		updateBtnFlag_${comment.commentNo} = true;
        	} else{
        		document.getElementById("updateCommentForm_${comment.commentNo}").style.display = "none";
        		updateBtnFlag_${comment.commentNo} = false;
        	}
		}
        function deleteComment_${comment.commentNo}() {
        	if(confirm("댓글을 삭제하시겠습니까?"))
				document.getElementById("deleteCommentForm_${comment.commentNo}").submit();
		}
      </script>
      </div>
    </div>
    </c:if>
    <hr>
  </c:forEach>
  </div>
</div>
<div class="row">
  <div class="col-12" align="right">
    <form action="TipsCommentWriteCommentController.do" method="post">
      <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
      <textarea rows="3" class="form-control" name="commentContent" placeholder="댓글을 입력하세요" required="required" style="margin: 0 0 10px 0"></textarea>
      <button class="btn btn-success" type="submit">등록</button>
    </form>
  </div>
</div>