<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form method="post" action="MentoringBoardWritePostController.do">
  <table class="table">
    <tr>
      <td>
	    <select name="category" required="required">
	  	  <option value="">-카테고리-</option>
	  	  <option value="프로그래밍">프로그래밍</option>
	  	  <option value="알고리즘">알고리즘</option>
	  	  <option value="전공지식">전공지식</option>
	  	  <option value="취업">취업</option>
	  	  <option value="자격증">자격증</option>
	    </select>
	  </td>
	  <td>
	    <select name="role" required="required">
	  	  <option value="">-역할-</option>
	  	  <option value="멘토">멘토</option>
	  	  <option value="멘티">멘티</option>
	    </select>
	  </td>
    </tr>
    <tr>
      <td colspan="2"><input type="text" name="title" placeholder="제목" required="required"></td>    
    </tr>
    <tr>
      <td colspan="2"><textarea rows="10" class="form-control" name="content" placeholder="본문내용" required="required"></textarea></td>
    </tr>
  </table>
  <div class="text-center">
    <button type="submit" class="btn btn-success">등록</button>
    <button type="reset" class="btn btn-success">취소</button>
  </div>
</form>