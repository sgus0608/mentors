<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<div id="login-bar">
				<c:choose>
					<c:when test="${sessionScope.mvo == null}">
						<a href="login/login.jsp">로그인</a>
					</c:when>
					<c:otherwise>
						${sessionScope.mvo.nickName}님&nbsp;&nbsp;
						<a href="javascript:logout()" style="text-decoration: none;">로그아웃</a>
						<form id="logoutForm" action="LogoutController.do" method="post" style="display: inline;"></form>
						<a onclick="memberUpdate()" style="cursor: pointer;" style="display: inline;">회원정보수정</a>
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
							<c:choose>
								<c:when test="${mentoringMenuBar != null}">
								<li class="current"><a href="MentoringBoardFindPostListController.do">멘토링</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="MentoringBoardFindPostListController.do">멘토링</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${freeMenuBar != null}">
								<li class="current"><a href="FreeBoardFindPostListController.do">자유게시판</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="FreeBoardFindPostListController.do">자유게시판</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${qnaMenuBar != null}">
								<li class="current"><a href="QnABoardFindPostListController.do">질문게시판</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="QnABoardFindPostListController.do">질문게시판</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${tipsMenuBar != null}">
								<li class="current"><a href="TipsBoardFindPostListController.do">유용한 정보</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="TipsBoardFindPostListController.do">유용한 정보</a></li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${quizMenuBar != null}">
								<li class="current"><a href="QuizFindPostListController.do">오늘의 퀴즈</a></li>
								</c:when>
								<c:otherwise>
								<li><a href="QuizFindPostListController.do">오늘의 퀴즈</a></li>
								</c:otherwise>
							</c:choose>
							</ul>
						</nav>
				</section>