package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST") == false) {
			throw new ServletException(getClass().getName()+"는 POST 방식만 접근가능");
		}
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:index.jsp";
	}

}
