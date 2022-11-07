package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;
import org.kosta.mentors.model.QuizVO;

public class QuizUpdatePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long quizNo = Long.parseLong(request.getParameter("quizNo"));
		String content = request.getParameter("content");
		String question1 = request.getParameter("question1");
		String question2 = request.getParameter("question2");
		String question3 = request.getParameter("question3");
		String question4 = request.getParameter("question4");
		String answer = request.getParameter("answer");
		String category = request.getParameter("category");
		
		QuizVO quizVO = new QuizVO(quizNo, content, question1, question2, question3, question4, answer, category);
		QuizDAO.getInstance().updatePost(quizVO);
		
		return "redirect:QuizFindPostListController.do";
	}

}
