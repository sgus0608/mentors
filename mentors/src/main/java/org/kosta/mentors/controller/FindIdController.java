package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.kosta.mentors.model.MemberDAO;

public class FindIdController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String id = MemberDAO.getInstance().findMemberId(email);
		JSONObject json = new JSONObject();
		json.put("id", id);
		request.setAttribute("responsebody",json);
		return "AjaxView";
	}

}
