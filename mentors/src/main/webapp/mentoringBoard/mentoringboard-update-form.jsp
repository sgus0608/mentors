<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="MentoringBoardUpdatePostController.do" method="post">
  <table class="table">
    <tr>
      <td>
        <input type="hidden" name="postNo" value="${postVO.postNo}">
        <select name="category" required="required">
	  	  <option value="">-카테고리-</option>
	  	  <c:forEach items="${categoryList}" var="cList">
	  	    <c:choose>
	  	      <c:when test="${cList == postVO.category}">
	  	        <option value="${cList}" selected="selected">${cList}</option>
	  	      </c:when>
	  	      <c:otherwise>
	  	        <option value="${cList}">${cList}</option>
	  	      </c:otherwise>
	  	    </c:choose>
	  	  </c:forEach>
	    </select>
      </td>
      <td>
        <select name="role" required="required">
	  	  <option value="">-역할-</option>
	  	  <c:forEach items="${roleList}" var="rList">
	  	    <c:choose>
	  	      <c:when test="${rList == postVO.role}">
	  	        <option value="${rList}" selected="selected">${rList}</option>
	  	      </c:when>
	  	      <c:otherwise>
	  	        <option value="${rList}">${rList}</option>
	  	      </c:otherwise>
	  	    </c:choose>
	  	  </c:forEach>
	    </select>
      </td>
    </tr>
    <tr>
      <td colspan="2"><input type="text" name="title" placeholder="제목" value="${postVO.title }" required="required"></td>    
    </tr>
    <tr>
      <td colspan="2"><textarea rows="10" class="form-control" name="content" placeholder="본문내용" required="required">${postVO.content}</textarea></td>
    </tr>
  </table>
  <div class="text-center">
    <button type="submit" class="btn btn-success">수정</button>
    <button type="reset" class="btn btn-success">취소</button>
  </div>
</form>