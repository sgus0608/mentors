<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>멘토스 - 세미프로젝트(천하제일조)</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<%-- 부트스트랩 시작 --%>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
		<%-- 부트스트랩 끝 --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">
		
			<div id="login-bar">
				<c:choose>
					<c:when test="${sessionScope.mvo == null}">
						<a href="login/login.jsp">로그인</a>
					</c:when>
					<c:otherwise>
						${sessionScope.mvo.nickName}님&nbsp;&nbsp;
						<a href="javascript:logout()">로그아웃</a>
						<form id="logoutForm" action="LogoutController.do" method="post"></form>
						<a onclick="memberUpdate()" style="cursor: pointer;">회원정보수정</a>
						<form id="memberUpdateForm" action="member/memberUpdatForm.jsp"></form>
						<script type="text/javascript">
							function logout() {
								let result = confirm("로그아웃 하시겠습니까?");
								if(result)
									document.getElementById("logoutForm").submit();
							}
							function memberUpdate() {
								
								document.getElementById("memberUpdateForm").submit();
						}
						</script>
					</c:otherwise>
				</c:choose>
			</div>
			
			<!-- Header -->
				<section id="header">

					<!-- Logo -->
						<h1><a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/mentros_logo2.png" width="320"></a></h1>

					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li class="current"><a href="MentoringBoardFindPostListController.do">멘토링</a></li>
								<li><a href="FreeBoardFindPostListController.do">자유게시판</a></li>
								<li><a href="QnABoardFindPostListController.do">질문게시판</a></li>
								<li><a href="TipsBoardFindPostListController.do">유용한 정보</a></li>
								<li><a href="QuizFindPostListController.do">오늘의 퀴즈</a></li>
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
							<div class="col-4 offset-2">
								<a style="font-size: 1.5rem; font-weight: bold; text-decoration-line: none;" href="MentoringBoardFindPostListController.do">멘토링게시판</a>
								<ul>
								<c:forEach items="${mentoringList}" var="mentoring">
								  <li><a href="MentoringBoardPostDetailController.do?postNo=${mentoring.postNo}">${mentoring.title}</a></li>
								</c:forEach>
								</ul>
							</div>	
							<div class="col-4 offset-2">
								<a style="font-size: 1.5rem; font-weight: bold; text-decoration-line: none;" href="FreeBoardFindPostListController.do">자유게시판</a>
								<c:forEach items="${freeList}" var="free">
								  <li><a href="FreeBoardPostDetailController.do?postNo=${free.postNo}">${free.title}</a></li>
								</c:forEach>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-4 offset-2">
								<a style="font-size: 1.5rem; font-weight: bold; text-decoration-line: none;" href="QnABoardFindPostListController.do">QnA</a>
								<c:forEach items="${qnaList}" var="qna">
								  <li><a href="TipsBoardPostDetailController.do?postNo=${qna.postNo}">${qna.title}</a></li>
								</c:forEach>
							</div>	
							<div class="col-4 offset-2">
								<a style="font-size: 1.5rem; font-weight: bold; text-decoration-line: none;" href="TipsBoardFindPostListController.do">유용한정보</a>
								<c:forEach items="${tipsList}" var="tips">
								  <li><a href="QnABoardPostDetailController.do?postNo=${tips.postNo}">${tips.title}</a></li>
								</c:forEach>
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