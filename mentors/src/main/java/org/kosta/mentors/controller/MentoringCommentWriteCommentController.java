package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringCommentDAO;

public class MentoringCommentWriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long postNo = Long.parseLong(request.getParameter("postNo"));
		String commentContent = request.getParameter("commentContent");
		HttpSession session = request.getSession(false);
		MemberVO memberVO = (MemberVO) session.getAttribute("mvo");
		CommentVO commentVO = new CommentVO(commentContent, postNo, memberVO);
		MentoringCommentDAO.getInstance().writeComment(commentVO);
		return "redirect:MentoringBoardPostDetailController.do?postNo="+postNo;
	}

}
