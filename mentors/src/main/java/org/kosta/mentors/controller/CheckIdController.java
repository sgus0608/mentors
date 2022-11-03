package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MemberDAO;

public class CheckIdController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		boolean result = MemberDAO.getInstance().checkId(id);
		String message = null;
		if(result)
			message="fail";
		else
			message="ok";
		request.setAttribute("responsebody", message);
		return "AjaxView";
	}

}
