<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table class="table">
  <tr>
    <td>글번호 ${qnaPostVO.postNo}</td>
    <td>카테고리 ${qnaPostVO.category}</td>
    <td>제목 ${qnaPostVO.title}</td>
    <td>작성자 ${qnaPostVO.memberVO.nickName}</td>
    <td>조회수 ${qnaPostVO.hits}</td>
    <td>작성일 ${qnaPostVO.timePosted}</td>
  </tr>
  <tr>
    <td colspan="5">
  	  <pre><font size="4">내용 ${qnaPostVO.content}</font></pre>
  	</td>
  </tr>
</table>