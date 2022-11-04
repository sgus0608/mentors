<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
  <tr>
    <td>글번호 ${postVO.postNo}</td>
    <td>카테고리 ${postVO.category}</td>
    <td>제목 ${postVO.title}</td>
    <td>역할 ${postVO.role}</td>
    <td>작성자 ${postVO.memberVO.nickName}</td>
    <td>작성일 ${postVO.timePosted}</td>
    <td>조회수 ${postVO.hits}</td>
  </tr>
  <tr>
    <td colspan="7">${postVO.content}</td>
  </tr>
  <c:if test="${postVO.memberVO.id == sessionScope.mvo.id}">
  <tr>
    <td colspan="7">
      <form id="updateForm" action="MentoringBoardUpdatePostFormController.do" method="post">
        <input type="hidden" name="postNo" value="${postVO.postNo}">
      </form>
      <form id="deleteForm" action="MentoringBoardDeletePostController.do" method="post">
        <input type="hidden" name="postNo" value="${postVO.postNo}">
      </form>
      <button type="button" onclick="updatePost()">수정</button>
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
    </td>
  </tr>
  </c:if>
</table>