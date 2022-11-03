package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;
import org.kosta.mentors.model.QuizVO;

public class QuizFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("list", QuizDAO.getInstance().FindPostList());
		request.setAttribute("url","quizBoard/quiz.jsp");
		
		return "layout.jsp";
	}

}
