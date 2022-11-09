<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="TipsBoardWritePostController.do">
	<table class="table">
		<tr>
			<td><input type="text" name="category" placeholder="카테고리" required="required"></td>
		</tr>
		<tr>
			<td><input type="text" name="title" placeholder="글제목"  required="required"></td>
		</tr>
		<tr>
			<td><textarea rows="10" class="form-control" name="content" placeholder="본문내용" required="required"></textarea></td>
		</tr>
	</table>
	<div class="text-center">
		<button type="submit" class="btn btn-success">등록</button>
		<button type="reset" class="btn btn-success">취소</button>
	</div>
</form>