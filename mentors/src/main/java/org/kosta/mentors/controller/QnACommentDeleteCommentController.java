package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnACommentDAO;

public class QnACommentDeleteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new Exception(getClass().getName()+" POST 방식으로만 접근가능");
		}
		long postNo=Long.parseLong(request.getParameter("postNo"));
		long commentNo=Long.parseLong(request.getParameter("commentNo"));
		QnACommentDAO.getInstance().deleteComment(commentNo);
		return "redirect:QnABoardPostDetailController.do?postNo="+postNo;
	}
}
