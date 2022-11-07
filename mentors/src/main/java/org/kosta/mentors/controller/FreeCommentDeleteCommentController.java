package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kosta.mentors.model.FreeCommentDAO;

public class FreeCommentDeleteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("POST") == false) 
			throw new ServletException(getClass().getName()+"는 POST METHOD 방식만 가능");
		long commentNo=Long.parseLong(request.getParameter("commentNo"));
		System.out.println(commentNo);
		FreeCommentDAO.getInstance().deleteComment(commentNo);
		return "redirect:FreeBoardFindPostListController.do";
	}

}
