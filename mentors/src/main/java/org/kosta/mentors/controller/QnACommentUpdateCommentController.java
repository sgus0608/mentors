package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.QnACommentDAO;

public class QnACommentUpdateCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new ServletException(getClass().getName()+" POST방식으로만 접근가능");
		}
		long commentNo=Long.parseLong(request.getParameter("commentNo"));
		long postNo=Long.parseLong(request.getParameter("postNo"));
		String commentContent=request.getParameter("updateCommentContent_"+commentNo);
		CommentVO commentVO=new CommentVO();
		commentVO.setCommentNo(commentNo);
		commentVO.setCommentContent(commentContent);
		QnACommentDAO.getInstance().updateComment(commentVO);
		return "redirect:QnABoardPostDetailController.do?postNo="+postNo;
	}
}
