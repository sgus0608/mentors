<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<!-- Header -->
				<section id="header">
					<!-- Logo -->
						<h1><a href="${pageContext.request.contextPath}/layout.jsp"><img src="${pageContext.request.contextPath}/images/mentors_logo1.png" width="200"></a></h1>
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="current"><a href="${pageContext.request.contextPath}/mentoringboard/layout-board.jsp">멘토링</a></li>
								<li><a href="${pageContext.request.contextPath}/layout.jsp">자유게시판</a></li>
								<li><a href="${pageContext.request.contextPath}/layout.jsp">질문게시판</a></li>
								<li><a href="${pageContext.request.contextPath}/layout.jsp">유용한 정보</a></li>
								<li><a href="${pageContext.request.contextPath}/layout.jsp">오늘의 퀴즈</a></li>
							</ul>
						</nav>
				</section>