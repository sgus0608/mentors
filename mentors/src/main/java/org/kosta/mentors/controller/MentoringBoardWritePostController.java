package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardWritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST") == false) {
			throw new ServletException(getClass().getName()+"는 POST 방식만 접근가능");
		}
		
		String category = request.getParameter("category");
		String role = request.getParameter("role");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		MentoringPostVO postVO = new MentoringPostVO(title, content, category, role, memberVO);
		MentoringBoardDAO.getInstance().writePost(postVO);
		return "redirect:MentoringBoardFindPostListController.do";
	}

}
