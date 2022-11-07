<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="QuizUpdatePostController.do">
  <table class="table">
    <tr>
      <td>
        <input type="hidden" name="quizNo" value="${quizVO.no}">
        <input type="text" name="category" placeholder="카테고리" value="${quizVO.category}" required="required">
      </td>
      <td><input type="text" name="answer" placeholder="정답" value="${quizVO.answer}" required="required"></td>
    </tr>
    <tr>
      <td colspan="4"><textarea rows="10" class="form-control" name="content" placeholder="질문내용" required="required">${quizVO.content}</textarea></td>
    </tr>
    <tr>
      <td><input type="text" name="question1" placeholder="질문1" value="${quizVO.question1}" required="required"></td>    
      <td><input type="text" name="question2" placeholder="질문2" value="${quizVO.question2}" required="required"></td>    
      <td><input type="text" name="question3" placeholder="질문3" value="${quizVO.question3}" required="required"></td>    
      <td><input type="text" name="question4" placeholder="질문4" value="${quizVO.question4}" required="required"></td>    
  </table>
  <div class="text-center">
    <button type="submit" class="btn btn-success">등록</button>
    <button type="reset" class="btn btn-success">취소</button>
  </div>
</form>