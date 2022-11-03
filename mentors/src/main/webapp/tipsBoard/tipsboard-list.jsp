<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="table table-hover table-bordered boardlist">
	<thead>
		<tr style="background-color: #ccff66">
			<th>번호</th>
			<th>카테고리</th>
			<th class="title">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.postNo}</td>
				<td>${list.category}</td>
				<td>
				<c:choose>
						<c:when test="${sessionScope.mvo!=null}">
        					${list.title}
        				</c:when>
						<c:otherwise>
							<a href="TipsPostDetailController.do?postNo=${list.postNo}">${list.title}</a>
						</c:otherwise>
				</c:choose>
				</td>
				<td>${list.memberVO.nickName}</td>
				<td>${list.timePosted}</td>
				<td>${list.hits}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>