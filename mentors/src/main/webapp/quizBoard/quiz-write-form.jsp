<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="QuizWritePostController.do">
  <table class="table">
    <tr>
      <td><input type="text" name="category" placeholder="카테고리" required="required"></td>
      <td><input type="text" name="answer" placeholder="정답" required="required"></td>
    </tr>
    <tr>
      <td colspan="4"><textarea rows="20" class="form-control" name="content" placeholder="질문내용" required="required"></textarea></td>
    </tr>
    <tr>
      <td><input type="text" name="question1" placeholder="질문1" required="required"></td>    
      <td><input type="text" name="question2" placeholder="질문2" required="required"></td>    
      <td><input type="text" name="question3" placeholder="질문3" required="required"></td>    
      <td><input type="text" name="question4" placeholder="질문4" required="required"></td>    
    </tr>
  </table>
  <div class="text-center">
    <button type="submit" class="btn btn-success">등록</button>
    <button type="reset" class="btn btn-success">취소</button>
  </div>
</form>