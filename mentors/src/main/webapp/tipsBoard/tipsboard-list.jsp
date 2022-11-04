<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<c:forEach items="${requestScope.list}" var="list">
			<tr>
				<td>${list.postNo}</td>
				<td>${list.category}</td>
				<td><c:choose>
						<c:when test="${sessionScope.mvo==null}">
        					${list.title}
        				</c:when>
						<c:otherwise>
							<a href="TipsBoardPostDetailController.do?postNo=${list.postNo}">${list.title}</a>
						</c:otherwise>
					</c:choose></td>
				<td>${list.memberVO.nickName}</td>
				<td>${list.timePosted}</td>
				<td>${list.hits}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${sessionScope.mvo!=null}">
	<a href="TipsBoardWritePostFormController.do">글쓰기</a>
</c:if>

<!--
      pagination 
-->
<ul class="pagination justify-content-center" style="margin: 20px 0">
	<c:if  test="${pagination.previousPageGroup}">
	<li class="page-item"><a class="page-link" href="TipsBoardFindPostListController.do?pageNo=${pagination.startPageOfPageGroup-1}">Previous</a></li>
	</c:if>
	<c:forEach begin="${pagination.startPageOfPageGroup}" end="${pagination.endPageOfPageGroup}"  var="page">
	<c:choose>
	<c:when test="${pagination.nowPage == page}">
	<li class="page-item active"><a class="page-link" href="TipsBoardFindPostListController.do?pageNo=${page}">${page}</a></li>
	</c:when>
	<c:otherwise>
	<li class="page-item"><a class="page-link" href="TipsBoardFindPostListController.do?pageNo=${page}">${page}</a></li>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	<c:if test="${pagination.nextPageGroup}">
	<li class="page-item"><a class="page-link" href="TipsBoardFindPostListController.do?pageNo=${pagination.endPageOfPageGroup+1}">Next</a></li>
	</c:if>
</ul>