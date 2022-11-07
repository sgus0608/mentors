package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringCommentDAO;

public class MentoringCommentDeleteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long commentNo = Long.parseLong(request.getParameter("commentNo"));
		long postNo = Long.parseLong(request.getParameter("postNo"));
		MentoringCommentDAO.getInstance().deleteComment(commentNo);
		return "redirect:MentoringBoardPostDetailController.do?postNo="+postNo;
	}

}
