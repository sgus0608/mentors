package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;
import org.kosta.mentors.model.QuizVO;

public class QuizUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		QuizVO quizVO = QuizDAO.getInstance().postDetailByNo(postNo);
		request.setAttribute("quizVO", quizVO);
		request.setAttribute("quizMenuBar", true);
		request.setAttribute("url", "quizBoard/quiz-update-form.jsp");
		return "layout.jsp";
	}

}
