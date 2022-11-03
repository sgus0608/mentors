<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- main 본문 내용 
<form>
	<a href="login/login.jsp">로그인</a>
	<c:choose>
		<c:when test="${sessionScope.mvo==null}">
        	${list.title}				
        </c:when>
		<c:otherwise>
			<a href="FreeBoardPostDetailController.do?postNo=${list.postNo}">${list.title}</a>
		</c:otherwise>
	</c:choose>
</form>
--%>
<table class="table table-hover table-bordered boardlist">
	<thead>
		<tr style="background-color: #ccff66">
			<th>번호</th>
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
				<td>
				<c:choose>
						<c:when test="${sessionScope.mvo==null}">
        					${list.title}				
        				</c:when>
						<c:otherwise>
							<a href="FreeBoardPostDetailController.do?postNo=${list.postNo}">${list.title}</a>
						</c:otherwise>
				</c:choose>
				</td>
				<td>${list.memberVO.nickName}</td>
				<td>${list.timePosted}</td>
				<td>${list.hits}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
					<form action="FreeBoardWritePostFormController.do" method="get">
						<button type="submit">글쓰기</button> 
					</form>
				</td>
			</tr>
	</tbody>
</table>