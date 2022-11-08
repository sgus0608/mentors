<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-12">
		<p style="font-size: 2rem; font-weight: bold">${postVO.title}</p>
	</div>
</div>
<div class="row">
	<div class="col-2">
		글번호 : ${postVO.postNo}
	</div>
	<div class="col-3">
		작성자 : ${postVO.memberVO.nickName}
	</div>
	<div class="col-3">
		작성일 : ${postVO.timePosted}
	</div>
	<div class="col-2">
		조회수 : ${postVO.hits}
	</div>
	<div>
		<form>
		<c:choose>
			<c:when test="${likeFlag == false}">
				<button type="button" id="like" onclick="checkLike()">♡</button>
			</c:when>
			<c:otherwise>
				<button type="button" id="disLike" onclick="checkDisLike()">♥</button>
			</c:otherwise>
		</c:choose>	
		</form>		
		<script type="text/javascript">
			function checkLike(){
				let like=document.getElementById("like");
				let xhr=new XMLHttpRequest();
				xhr.onreadystatechange=function(){
					if(xhr.readyState==4&&xhr.status==200){
						if(xhr.responseText=="ok"){
							like.innerHTML="♥";	
							likePush();
							likeFlag=true;
						}else{
							like.innerHTML="♡";
						}
					}//if
				}//function
				xhr.open("get","CheckLikeController.do?postNo=${postVO.postNo}&id=${sessionScope.mvo.id}");
				xhr.send();
			}
			function checkDisLike(){
				let disLike=document.getElementById("disLike");
				let xhr2=new XMLHttpRequest();
				xhr2.onreadystatechange=function(){
					if(xhr2.readyState==4&&xhr2.status==200){
						if(xhr2.responseText=="fail"){
							disLike.innerHTML="♡";
							likeFlag=false;
						}else{
							disLike.innerHTML="♥"
						}
					}//if
				}//function
				xhr2.open("get","CheckLikeController.do?postNo=${postVO.postNo}&id=${sessionScope.mvo.id}");
				xhr2.send();
			}
		</script>
	</div>
</div>
<hr>
<div class="row">
  <div class="col-12">
    <pre>${postVO.content}</pre>
  </div>
</div>
<c:if test="${postVO.memberVO.id == sessionScope.mvo.id}">
<div class="row">
	<div class="col-3 offset-9">
	<form id="updateForm" action="FreeBoardUpdatePostFormController.do" method="post">
		<input type="hidden" name="postNo" value="${postVO.postNo}">
	</form>
	<form id="deleteForm" action="FreeBoardDeletePostController.do" method="post">
		<input type="hidden" name="postNo" value="${postVO.postNo}">
	</form>
	<button type="button" onclick="updatePost()">글수정</button>
	<button type="button" onclick="deletePost()">삭제</button>
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
				<div class="col-3">
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
					<div class="col-6 offset-3">
						<form id="updateCommentForm_${comment.commentNo}" action="FreeCommentUpdateCommentController.do" method="post" style="display: none">
							<input type="hidden" name="commentNo" value="${comment.commentNo}">
							<input type="hidden" name="postNo" value="${postVO.postNo}">
							<textarea rows="3" class="form-control" name="updateCommentContent_${comment.commentNo}" placeholder="댓글을 입력하세요" required="required">${comment.commentContent}</textarea>
							<button type="submit">수정완료</button>
							<button type="reset">취소</button>
						</form>
					</div>
					<div class="col-3">
						<form id="deleteCommentForm_${comment.commentNo}" action="FreeCommentDeleteCommentController.do" method="post">
							<input type="hidden" name="commentNo" value="${comment.commentNo}">
							<input type="hidden" name="postNo" value="${postVO.postNo}">
						</form>
						<button type="button" onclick="updateComment_${comment.commentNo}()">댓글수정</button>
						<button type="button" onclick="deleteComment_${comment.commentNo}()">삭제</button>
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
  <div class="col-12">
    <form action="FreeCommentWriteCommentController.do" method="post">
      <input type="hidden" name="postNo" value="${postVO.postNo}">
      <textarea rows="3" class="form-control" name="commentContent" placeholder="댓글을 입력하세요" required="required"></textarea>
      <br>
      <button type="submit">등록</button>
    </form>
  </div>
</div>


