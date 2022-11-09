package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TipsBoardWritePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tipsMenuBar", true);
		request.setAttribute("url", "tipsBoard/tipsboard-write-form.jsp");
		return "layout.jsp";
	}

}
