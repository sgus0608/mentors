<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
  <tr>
    <td>글번호 ${postVO.postNo}</td>
    <td>카테고리 ${postVO.category}</td>
    <td>제목 ${postVO.title}</td>
    <td>역할 ${postVO.role}</td>
    <td>작성자 ${postVO.memberVO.nickName}</td>
    <td>작성일 ${postVO.timePosted}</td>
    <td>조회수 ${postVO.hits}</td>
  </tr>
  <tr>
    <td colspan="7">${postVO.content}</td>
  </tr>
</table>