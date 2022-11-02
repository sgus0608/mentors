<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class="table table-bordered table-hover ">
	<thead style="background-color: lime">
		<tr>
		  <th>글번호</th>
		  <th class="title">제목</th>
		  <th>카테고리</th>
		  <th>작성자</th>
		  <th>작성일</th>
		  <th>조회수</th>
		</tr>
	</thead>
	<tbody>
	  <c:forEach items="${requestScope.list}" var="list">
		<tr>
		  <td>${list.postNo}</td>
		  <td>${list.title}</td>
		  <td>${list.category}</td>
		  <td>${list.memberVO.nickName}</td>
		  <td>${list.timePosted}</td>
		  <td>${list.hits}</td>
		</tr>
	  </c:forEach>
	</tbody>
</table>