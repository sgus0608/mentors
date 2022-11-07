package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnACommentUpdateCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long commentNo=Long.parseLong(request.getParameter("commentNo"));
		return "redirect:QnABoardPostDetailController?commentNo="+commentNo;
	}
}
