package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnACommentDAO;

public class QnACommentWriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		String commentContent=request.getParameter("commentContent");
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		CommentVO commentVO=new CommentVO(commentContent, postNo, memberVO);
		QnACommentDAO.getInstance().writeComment(commentVO);
		return "redirect:QnABoardPostDetailController.do?postNo="+postNo;
	}
}
