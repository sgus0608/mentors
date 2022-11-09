<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="FreeBoardUpdatePostController.do">
		<table class="table">
			<tr>
				<td>
				<input type="hidden" name="postNo" value="${postVO.postNo}">
				<input type="text" name="title" value="${postVO.title}" required="required">
				</td>
			</tr>
			<tr>
				<td><textarea rows="20" class="form-control" name="content"  required="required">${postVO.content}</textarea></td>
			</tr>
		</table>
		<div class="text-center">
			<button type="submit" class="btn btn-success">확인</button>
			<button type="reset" class="btn btn-success">취소</button>
		</div>
</form>