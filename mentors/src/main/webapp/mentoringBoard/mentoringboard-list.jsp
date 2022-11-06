<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
								<table>
								    <thead>
								      <tr>
								        <th>글번호</th>
								        <th>카테고리</th>
								        <th>제목</th>
								        <th>역할</th>
								        <th>작성자</th>
								        <th>작성일</th>
								        <th>조회수</th>
								      </tr>
								    </thead>
								    <tbody>
								    <c:forEach items="${list}" var="post">
								      <tr>
								        <td>${post.postNo}</td>
								        <td>${post.category}</td>
								        <c:choose>
								        <c:when test="${sessionScope.mvo == null}">
								        <td>${post.title}</td>
								        </c:when>
								        <c:otherwise>
								        <td><a href="MentoringBoardPostDetailController.do?postNo=${post.postNo}">${post.title}</a></td>
								        </c:otherwise>
								        </c:choose>
								        <td>${post.role}</td>
								        <td>${post.memberVO.nickName}</td>
								        <td>${post.timePosted}</td>
								        <td>${post.hits}</td>
								      </tr>
								    </c:forEach>
								    </tbody>
								  </table>
								  <c:if test="${sessionScope.mvo != null}">
								    <form action="MentoringBoardWritePostFormController.do">
								      <button type="submit">글쓰기</button>
								    </form>
								  </c:if>
								  <!--
								        pagination 
								  -->
								  <ul class="pagination justify-content-center" style="margin:20px 0">
								  	  <c:if test="${pagination.previousPageGroup}">
									  <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${pagination.startPageOfPageGroup - 1}">Previous</a></li>
									  </c:if>
									  <c:forEach begin="${pagination.startPageOfPageGroup}" end="${pagination.endPageOfPageGroup}" var="page">
									  <c:choose>
									  <c:when test="${pagination.nowPage == page}">
									  <li class="page-item active"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${page}">${page}</a></li>
									  </c:when>
									  <c:otherwise>
									  <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${page}">${page}</a></li>
									  </c:otherwise>
									  </c:choose>
									  </c:forEach>
									  <c:if test="${pagination.nextPageGroup}">
								  	  <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${pagination.endPageOfPageGroup + 1}">Next</a></li>
								  	  </c:if>
								  </ul>