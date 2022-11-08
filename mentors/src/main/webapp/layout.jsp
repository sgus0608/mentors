<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>멘토스 - 세미프로젝트(천하제일조)</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<%-- 부트스트랩 시작 --%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<%-- 부트스트랩 끝 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">
			<%-- header 영역 시작 --%>
			<c:import url="/header.jsp"></c:import>
			<%-- header 영역 끝 --%>
			<%-- main 영역 시작 --%>
				<section id="main">
					<div class="container">
						<div class="row">
							<div class="col-12">
								<%-- main 본문 내용 --%>
								<c:import url="${requestScope.url}"></c:import>
							</div>
						</div>
					</div>
				</section>
			<%-- main 영역 끝 --%>
			<%-- footer 영역 시작 --%>
			<c:import url="/footer.jsp"></c:import>
			<%-- footer 영역 끝 --%>
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