<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
  <thead>
  	<tr>
  	  <th>번호</th>
  	  <th>문제</th>
  	  <th>정답</th>
  	  <th>분야</th>
  	</tr>
  </thead>
  <tbody>
  <c:forEach items="${list }" var="post" >
  	<tr>
  	  <td>${post.no }</td>
  	  <td>${post.content }</td>
  	  <td>${post.answer }</td>
  	  <td>${post.category }</td>
  	</tr>
  	</c:forEach>
  </tbody>
</table>
