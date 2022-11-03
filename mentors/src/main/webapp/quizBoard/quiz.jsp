<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
[type="radio"] {
  vertical-align: middle;
  appearance: none;
  border: max(2px, 0.1em) solid gray;
  border-radius: 50%;
  width: 1em;
  height: 1em;
  transition: border 0.5s ease-in-out;
}
[type="radio"]:checked {
  border: 0.4em solid #467949;
}
label {
	font-size: larger;
	vertical-align: middle;
}
</style>


  <c:forEach items="${list }" var="post" >
  	관련분야 : ${post.category }
  	<div>
  		<b> ${post.no}번)</b>  ${post.content }
  		<br>
  		
  		<input type="radio" name="question${post.no }" value="${post.que1 }"><label>${post.que1 }</label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
  		<input type="radio" name="question${post.no }" value="${post.que2 }"><label>${post.que2 }</label>&nbsp; &nbsp;&nbsp; &nbsp;  &nbsp; 
  		<input type="radio" name="question${post.no }" value="${post.que3}"><label>${post.que3 }</label>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
  		<input type="radio" name="question${post.no }" value="${post.que4}"><label>${post.que4 }</label>
  	</div>
  <br>
  </c:forEach>

