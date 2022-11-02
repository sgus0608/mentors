<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>멘토스 - 세미프로젝트(천하제일조)</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">

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
				
			<!-- Main -->
				<section id="main">
					<div class="container">
						<div class="row">
							<div class="col-12">
								<table>
								    <thead>
								      <tr>
								        <th>Firstname</th>
								        <th>Lastname</th>
								        <th>Email</th>
								      </tr>
								    </thead>
								    <tbody>
								      <tr>
								        <td>John</td>
								        <td>Doe</td>
								        <td>john@example.com</td>
								      </tr>
								      <tr>
								        <td>Mary</td>
								        <td>Moe</td>
								        <td>mary@example.com</td>
								      </tr>
								      <tr>
								        <td>July</td>
								        <td>Dooley</td>
								        <td>july@example.com</td>
								      </tr>
								    </tbody>
								  </table>
							</div>
						</div>
					</div>
				</section>
				
			<!-- Footer -->
				<section id="footer">
					<div class="container">
						<div class="row">
							<div class="col-12">
							
								<!-- Copyright -->
									<div id="copyright">
										<ul class="links">
											<li>Copyright(c) 2022 KOSTA 천하제일 All right reserved</li>
										</ul>
									</div>
									
							</div>
						</div>
					</div>
				</section>
				
		</div>

		<!-- Scripts -->
			<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/jquery.dropotron.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/browser.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/breakpoints.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

	</body>
</html>