package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.TipsCommentDAO;

public class TipsCommentUpdateCommentController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false)
			throw new ServletException("TipsCommentUpdatePostController는 POST METHOD 방식만 가능");
		long postNo=Long.parseLong(request.getParameter("postNo"));
		long commentNo=Long.parseLong(request.getParameter("commentNo"));
		String content=request.getParameter("commentContent");
		CommentVO commentVO=new CommentVO();
		commentVO.setCommentNo(commentNo);

		TipsCommentDAO.getInstance().updateComment(commentVO);
		return "redirect:.do";
	}

}
