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
              <option value="뉴스">뉴스</option>
		<option value="후기">후기</option>
		<option value="추천">추천</option>
		<option value="꿀팁">꿀팁</option>
            </select>
            <input type="text" name="title" value="${tipsPostVO.title}" required="required">
            </td>
         </tr>
         <tr>
            <td><textarea rows="10" class="form-control" name="content"  required="required">${tipsPostVO.content}</textarea></td>
         </tr>
      </table>
      <div class="text-center">
         <button type="submit" class="btn btn-success">수정</button>
         <button type="reset" class="btn btn-success">취소</button>
      </div>
</form>





