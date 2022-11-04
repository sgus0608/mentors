<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<form method="post" action="QnABoardUpdatePostController.do">
		<table class="table">
			<tr>
				<td>
				<input type="hidden" name="postNo" value="${qnaPostVO.postNo}">
				<input type="text" name="category" value="${qnaPostVO.category}" required="required">
				<input type="text" name="title" value="${qnaPostVO.title}" required="required">
				</td>
			</tr>
			<tr>
				<td><textarea rows="10" class="form-control" name="content"  required="required">${qnaPostVO.content}</textarea></td>
			</tr>
		</table>
		<div class="text-center">
			<button type="submit" class="btn btn-success">수정</button>
			<button type="reset" class="btn btn-success">취소</button>
		</div>
</form>






