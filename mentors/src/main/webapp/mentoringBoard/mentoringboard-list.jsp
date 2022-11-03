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
								        <td>${post.title}</td>
								        <td>${post.role}</td>
								        <td>${post.memberVO.nickName}</td>
								        <td>${post.timePosted}</td>
								        <td>${post.hits}</td>
								      </tr>
								    </c:forEach>
								    </tbody>
								  </table>