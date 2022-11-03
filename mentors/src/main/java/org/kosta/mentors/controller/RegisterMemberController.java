package org.kosta.mentors.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MemberDAO;
import org.kosta.mentors.model.MemberVO;

public class RegisterMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String interest = request.getParameter("interest");
		MemberVO vo = new MemberVO(id,password,nickName,email,address,interest,null,null);
		MemberDAO.getInstance().registerMember(vo);
		return "/member-register-ok.jsp";
	}

}
