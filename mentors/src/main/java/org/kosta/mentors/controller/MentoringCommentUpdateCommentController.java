package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MentoringCommentUpdateCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		System.out.println(postNo);
		return "redirect:MentoringBoardPostDetailController.do?postNo="+postNo;
	}

}
