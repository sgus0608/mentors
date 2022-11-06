package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeCommentDAO;
import org.kosta.mentors.model.MemberVO;

public class FreeCommentWriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String commentContent=request.getParameter("commentContent");
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		
		CommentVO commentVO=new CommentVO(commentContent, postNo, memberVO);
		FreeCommentDAO.getInstance().writeComment(commentVO);
		return "redirect:FreeBoardPostDetailController.do?postNo="+postNo;
	}

}
