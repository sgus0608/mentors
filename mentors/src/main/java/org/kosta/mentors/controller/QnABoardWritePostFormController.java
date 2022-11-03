package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnABoardWritePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("url", "qnaBoard/qnaboard-write-form.jsp");
		return "layout.jsp";
	}
}
