package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.kosta.mentors.model.MemberDAO;

public class FindPwController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pw = MemberDAO.getInstance().findMemberPw(id, email);
		JSONObject json = new JSONObject();
		json.put("pw", pw);
		request.setAttribute("responsebody",json);
		return "AjaxView";
	}

}
