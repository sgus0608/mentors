package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;

public class QuizLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long quizNo = Long.parseLong(request.getParameter("quiz_no"));
		long count = QuizDAO.getInstance().quizLikeCount(quizNo);
		
		return null;
	}

}
