package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardWritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");
		String role = request.getParameter("role");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("mvo");
		MentoringPostVO postVO = new MentoringPostVO(title, content, category, role, memberVO);
		MentoringBoardDAO.getInstance().writePost(postVO);
		return "redirect:MentoringBoardFindPostListController.do";
	}

}