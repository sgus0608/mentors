package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeCommentDAO;

public class FreeCommentUpdateCommentController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long commentNo = Long.parseLong(request.getParameter("commentNo"));
		long postNo = Long.parseLong(request.getParameter("postNo"));
		String commentContent = request.getParameter("updateCommentContent_"+commentNo);
		CommentVO commentVO = new CommentVO();
		commentVO.setCommentNo(commentNo);
		commentVO.setCommentContent(commentContent);
		FreeCommentDAO.getInstance().updateComment(commentVO);
		return "redirect:FreeBoardPostDetailController.do?postNo="+postNo;
	}

}
