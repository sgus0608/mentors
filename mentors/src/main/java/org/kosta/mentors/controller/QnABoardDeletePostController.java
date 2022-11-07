package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnABoardDAO;

public class QnABoardDeletePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("POST") == false) {
			throw new ServletException(getClass().getName() + "는 POST 방식만 접근가능");
		}

		long postNo = Long.parseLong(request.getParameter("postNo"));
		QnABoardDAO.getInstance().deletePost(postNo);
		return "redirect:QnABoardFindPostListController.do";
	}
}
