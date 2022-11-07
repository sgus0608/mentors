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
button{
	height: 40px;
	width: 80px;
	font-size: small;
	text-align: center;
  	float: right;
}
</style>
  <c:if test="${sessionScope.mvo.memberType=='QuizAdmin'}">
  <div align="right">
    <form action="QuizWritePostFormController.do" method="post">
      <button>등록</button>
    </form>
    <br>
    <hr>
    <form action="QuizUpdatePostFormController.do" method="post">
      <input type="number" name="postNo" min="1" placeholder="수정할 문제번호" required="required">
      <button>수정</button>
    </form>
    <hr>
    <form action="QuizDeletePostController.do" method="post">
      <input type="number" name="postNo" min="1" placeholder="삭제할 문제번호" required="required">
      <button>삭제</button>
    </form>
    <br>
  </div>
  </c:if>
  <c:forEach items="${list }" var="post" >
  	관련분야 : ${post.category}
  	<div>
  		<b> ${post.no}번)</b>  ${post.content}
  		<br>
  		<input type="radio" name="${post.no}" value="${post.question1 }"><label>${post.question1 }</label>  &nbsp; &nbsp; &nbsp;
  		<input type="radio" name="${post.no}" value="${post.question2 }"><label>${post.question2 }</label> &nbsp; &nbsp; &nbsp;
  		<input type="radio" name="${post.no}" value="${post.question3}"><label>${post.question3 }</label>  &nbsp; &nbsp; &nbsp;
  		<input type="radio" name="${post.no}" value="${post.question4}"><label>${post.question4 }</label>
  		<br>
  		<span id="${post.no }"></span>
  		
  		<button onclick="checkAnswer('${post.no}')">제출</button>
  	</div>
  <br>
  
  
 </c:forEach>
<script type="text/javascript">
	function checkAnswer(postNo) {
		let pn = document.getElementsByName(postNo);
		let checkResultSpan= document.getElementById(postNo);
	
		 let answer ="";
		for(let i =0; i<pn.length;i++){
			if(pn[i].checked){
				answer = pn[i].value;
				break;
			}
		}
		
		
		if(answer==""){
			alert("답안을 선택하세요");
		}
		else{
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if(xhr.readyState==4&&xhr.status==200){
					if(xhr.responseText=="ok"){
						checkResultSpan.innerHTML = "<font color=blue>정답입니다</font>";
					}else{
						checkResultSpan.innerHTML="<font color=red>틀렸습니다</font>";
					}
				}
			}
			xhr.open("get","QuizCheckResultController.do?answer="+answer+"&postNo="+postNo);
			xhr.send();
		}
	}
	
</script>
<ul class="pagination justify-content-center" style="margin:20px 0">
	<c:if test="${pagination.previousPageGroup}">
	<li class="page-item"><a class="page-link" href="QuizFindPostListController.do?quiz_no=${pagination.startPageOfPageGroup-1 }">이전</a></li>
	</c:if>
	
	<c:forEach begin="${pagination.startPageOfPageGroup}" end="${pagination.endPageOfPageGroup }" var="page">
	<c:choose>
		<c:when test="${pagination.nowPage==page }">
			<li class="page-item active"><a class="page-link" href="QuizFindPostListController.do?quiz_no=${page}">${page }</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="QuizFindPostListController.do?quiz_no=${page }">${page }</a></li>
		</c:otherwise>
	</c:choose>
	
	</c:forEach>
	
	<c:if test="${pagination.nextPageGroup}">
	<li class="page-item"><a class="page-link" href="QuizFindPostListController.do?quiz_no=${pagination.endPageOfPageGroup+1 }">Next</a></li>
	</c:if>
</ul>

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

