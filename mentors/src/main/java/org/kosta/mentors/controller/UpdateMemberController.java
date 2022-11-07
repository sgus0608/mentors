package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberDAO;
import org.kosta.mentors.model.MemberVO;

public class UpdateMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String address= request.getParameter("address");
		String interest = request.getParameter("interest");
		
		MemberVO mvo = new MemberVO(id,password,nickName,email,address,interest);
		MemberDAO.getInstance().UpdateMember(mvo);
		HttpSession session = request.getSession(false);
		session.setAttribute("mvo", mvo);
		
		return "member/memberUpdate-ok.jsp";
	}

}
