package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QuizDAO;

public class CheckResultController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String answer = request.getParameter("answer");
		System.out.println(answer);
		long postNo = Long.parseLong(request.getParameter("postNo"));
		System.out.println(postNo);
		String result = QuizDAO.getInstance().checkResult(postNo);
		String message=null;
		if(answer == result) {
			message="ok";
		}else {
			message="fail";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
	}

}
