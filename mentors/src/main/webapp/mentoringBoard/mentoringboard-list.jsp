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

<div class="row">
  <div class="col-4 offset-4" style="display: inline;">
  <!--
     검색
  -->
  <nav class="navbar navbar-expand-sm">
  <form class="form-inline" action="MentoringBoardFindPostListController.do" method="get">
    <select class="form-control mr-2" name="category">
      <option value="제목">제목</option>
      <option value="내용">내용</option>
      <option value="작성자">작성자</option>
    </select>
    <input class="form-control mr-2" type="search" name="searchText" required="required" style="display: inline;">
    <button class="btn btn-success" type="submit" style="display: inline;">검색</button>
  </form>
  </nav>
  </div>
  <!--
	 글쓰기 버튼
   -->
  <c:if test="${sessionScope.mvo != null}">
    <div class="col-2 offset-2" align="right" style="display: inline;">
      <form action="MentoringBoardWritePostFormController.do" method="post" style="display: inline;">
        <button class="btn btn-success" type="submit" style="display: inline;">글쓰기</button>
      </form>
    </div>
  </c:if>
</div>
<hr>

<!--
     Pagination
     검색 category 유무에 따라 페이징네이션 컨트롤러 경로 변경
 -->
<ul class="pagination justify-content-center" style="margin:20px 0">
  <c:choose>
    <c:when test="${category == null}">
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
    </c:when>
    <c:otherwise>
      <c:if test="${pagination.previousPageGroup}">
        <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${pagination.startPageOfPageGroup - 1}&category=${category}&searchText=${searchText}">Previous</a></li>
      </c:if>
      <c:forEach begin="${pagination.startPageOfPageGroup}" end="${pagination.endPageOfPageGroup}" var="page">
      <c:choose>
        <c:when test="${pagination.nowPage == page}">
          <li class="page-item active"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${page}&category=${category}&searchText=${searchText}">${page}</a></li>
        </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${page}&category=${category}&searchText=${searchText}">${page}</a></li>
      </c:otherwise>
      </c:choose>
      </c:forEach>
      <c:if test="${pagination.nextPageGroup}">
	    <li class="page-item"><a class="page-link" href="MentoringBoardFindPostListController.do?pageNo=${pagination.endPageOfPageGroup + 1}&category=${category}&searchText=${searchText}">Next</a></li>
	  </c:if>
    </c:otherwise>
  </c:choose>
</ul>