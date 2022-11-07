package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MemberDAO;

public class deleteMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id =request.getParameter("id");
		MemberDAO.getInstance().deleteMember(id);
		
		return "redirect:login/login.jsp";
	}

}
