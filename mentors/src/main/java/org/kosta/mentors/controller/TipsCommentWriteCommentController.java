package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsCommentDAO;

public class TipsCommentWriteCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new Exception(getClass().getName()+"POST 방식방 가능합니다");
		}				
		
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		String commentContent=request.getParameter("commentContent");
		
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		CommentVO commentVO=new CommentVO(commentContent, postNo, memberVO);
		TipsCommentDAO.getInstance().writeComment(commentVO);		
		return "redirect:TipsBoardPostDetailController.do?postNo="+postNo;
	}
}
