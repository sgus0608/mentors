package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberDAO;
import org.kosta.mentors.model.MemberVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new ServletException(getClass().getName()+"는 POST 방식만 접근가능");
		}
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		MemberVO mvo=MemberDAO.getInstance().login(id, password);
		String viewPath=null;
		if(mvo==null) {
			viewPath="redirect:login/login-fail.jsp";
		}else {
			viewPath="redirect:index.jsp";
			HttpSession session=request.getSession();
			session.setAttribute("mvo", mvo);
			session.setAttribute("mentoringboard", new ArrayList<Long>());
			session.setAttribute("freeboard", new ArrayList<Long>());
			session.setAttribute("qnaboard", new ArrayList<Long>());
			session.setAttribute("tipsboard", new ArrayList<Long>());
		}
		return viewPath;
	}

}
