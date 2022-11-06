<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class="table table-bordered table-hover ">
	<thead style="background-color: lime">
		<tr>
		  <th>글번호</th>
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
		  <td> 
		    <c:choose>
		        <c:when test="${sessionScope.mvo==null}">
		          ${list.title}
		        </c:when>
		        <c:otherwise>
		            <a href="QnABoardPostDetailController.do?postNo=${list.postNo}">${list.title}</a>
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
<c:if test="${sessionScope.mvo!=null}">
	  <a href="QnABoardWritePostFormController.do">
	  	<button type="submit">글쓰기</button>
	  </a>
</c:if><br><br>




<form action="QnABoardSearchPostListCotroller.do" method="get">
	<select name="category">
		<option value="제목">제목</option>
		<option value="내용">내용</option>
		<option value="작성자">작성자</option>
	</select>
	<input type="text" name="searchText" placeholder="검색 : " required="required">
	<button type="submit">검색</button>
</form>



<!--
      pagination 
-->
<ul class="pagination justify-content-center" style="margin:20px 0">
	<c:if test="${pagination.previousPageGroup}">
	<li class="page-item"><a class="page-link" href="QnABoardSearchPostListCotroller.do?pageNo=${pagination.startPageOfPageGroup-1}&category=${category}&searchText=${searchText}">Previous</a></li>
	</c:if>
	<c:forEach begin="${pagination.startPageOfPageGroup}" end="${pagination.endPageOfPageGroup}" var="page">
	<c:choose>
	<c:when test="${pagination.nowPage == page}">
	<li class="page-item active"><a class="page-link" href="QnABoardSearchPostListCotroller.do?pageNo=${page}&category=${category}&searchText=${searchText}">${page}</a></li>
	</c:when>
	<c:otherwise>
	<li class="page-item"><a class="page-link" href="QnABoardSearchPostListCotroller.do?pageNo=${page}&category=${category}&searchText=${searchText}">${page}</a></li>
	</c:otherwise>
	</c:choose>
	</c:forEach>		
  	<c:if test="${pagination.nextPageGroup}">
  	<li class="page-item"><a class="page-link" href="QnABoardSearchPostListCotroller.do?pageNo=${pagination.endPageOfPageGroup+1}&category=${category}&searchText=${searchText}">Next</a></li>   
  	 </c:if>
</ul>








					
					
					
					