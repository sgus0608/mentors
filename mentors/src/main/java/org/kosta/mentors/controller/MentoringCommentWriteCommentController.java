package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringCommentDAO;

public class MentoringCommentWriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST") == false) {
			throw new ServletException(getClass().getName()+"는 POST 방식만 접근가능");
		}
		
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		String commentContent = request.getParameter("commentContent");
		
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		CommentVO commentVO = new CommentVO(commentContent, postNo, memberVO);
		MentoringCommentDAO.getInstance().writeComment(commentVO);
		return "redirect:MentoringBoardPostDetailController.do?postNo="+postNo;
	}

}
