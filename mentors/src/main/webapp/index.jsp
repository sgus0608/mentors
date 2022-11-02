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
						<h1><a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/mentors_logo1.png" width="200"></a></h1>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="current"><a href="${pageContext.request.contextPath}/FreeBoardFindPostListController.do">멘토링</a></li>
								<li><a href="${pageContext.request.contextPath}/FreeBoardFindPostListController.do">자유게시판</a></li>
								<li><a href="${pageContext.request.contextPath}/QnABoardFindPostListController.do">질문게시판</a></li>
								<li><a href="${pageContext.request.contextPath}/TipsBoardFindPostListController.do">유용한 정보</a></li>
								<li><a href="${pageContext.request.contextPath}/FreeBoardFindPostListController.do">오늘의 퀴즈</a></li>
							</ul>
						</nav>

					<!-- Banner -->
						<section id="banner">
							<header>
								<h2>우리 모두 멘토를 향해</h2>
								<p>달려가봐요</p>
							</header>
						</section>
				</section>
				
			<!-- Main -->
				<section id="main">
					<div class="container">
						<div class="row">
							<div class="col-12">
								아이디 : ${mvo.nickName}
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