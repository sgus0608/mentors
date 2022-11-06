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
  	  <%--1.로그인한 아이디와 글을 작성한 아이디가 같을때 수정,삭제 버튼이 보인다
  	        2. form 을 정의해서  javascript의 button onclick 이벤트를 이용해서 post 방식으로 전송한다--%>
  <c:if test="${sessionScope.mvo.id==qnaPostVO.memberVO.id}">
  <tr>
    <td colspan="5">
      <form action="QnABoardUpdatePostFormController.do" id="updateForm" method="post">
       	<input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
      </form>
      
    <%--oooooooooooooooooooooo구분선oooooooooooooooooo --%>  
      
      <form action="QnABoardDeletePostController.do" id="deleteForm" method="post">
      	 <input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
      </form>
    <%--oooooooooooooooooooooo구분선oooooooooooooooooo --%>  
    
	      <button type="button" onclick="updatePost()">수정</button>&nbsp;&nbsp;
	      <button type="button" onclick="deletePost()">삭제</button>
	  
	  <script type="text/javascript">
	    function deletePost() {
			if(confirm("삭제하시겠습니까?")){
				document.getElementById("deleteForm").submit();
			}
		}
	    function updatePost() {
			if(confirm("수정하시겠습니까?")){
				document.getElementById("updateForm").submit();
			}
		}
	  </script>
	</td>
  </tr>
  </c:if>
</table>



















