<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
	<tr>
		<td>글번호 ${tipsPostVO.postNo}</td>
		<td>카테고리  ${tipsPostVO.category}</td>
		<td>제목 : ${tipsPostVO.title}</td>
		<td>작성자 ${tipsPostVO.memberVO.nickName}</td>
		<td>조회수 :${tipsPostVO.hits}</td>
		<td>작성일 :${tipsPostVO.timePosted}</td>
	</tr>
	
	<tr >
		<td colspan="5">
		<pre>
		<font size="4">
		내용 : ${tipsPostVO.content}
		</font>
		</pre>
		</td>
	</tr>
</table>