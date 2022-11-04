package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;

public class QuizCheckResultController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answer = request.getParameter("answer");
		long postNo = Long.parseLong(request.getParameter("postNo"));
		String result = QuizDAO.getInstance().checkResult(postNo);
		String message=null;
		if(answer.trim().equals(result.trim())) {
			message="ok";
		}else {
			message="fail";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
	}

}
