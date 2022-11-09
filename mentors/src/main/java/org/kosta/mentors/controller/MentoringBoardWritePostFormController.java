package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MentoringBoardWritePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("mentoringMenuBar", true);
		request.setAttribute("url", "mentoringBoard/mentoringboard-write-form.jsp");
		return "layout.jsp";
	}

}
