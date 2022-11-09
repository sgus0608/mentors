<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<form method="post" action="TipsBoardUpdatePostController.do">
      <table class="table">
         <tr>
            <td>
            <input type="hidden" name="postNo" value="${tipsPostVO.postNo}">
            <select name="category" required="required">
               <option value="">--카테고리--</option>
               <c:forEach items="${categoryList}" var="cList">
                 <c:choose>
                   <c:when test="${cList == tipsPostVO.category}">
                   <option value="${cList}" selected="selected">${cList}</option>
                   </c:when>
                   <c:otherwise>
			       <option value="${cList}">${cList}</option>
			       </c:otherwise>
                 </c:choose>
               </c:forEach>
            </select>
            <input type="text" name="title" value="${tipsPostVO.title}" required="required">
            </td>
         </tr>
         <tr>
            <td><textarea rows="20" class="form-control" name="content"  required="required">${tipsPostVO.content}</textarea></td>
         </tr>
      </table>
      <div class="text-center">
         <button type="submit" class="btn btn-success">수정</button>
         <button type="reset" class="btn btn-success">취소</button>
      </div>
</form>





