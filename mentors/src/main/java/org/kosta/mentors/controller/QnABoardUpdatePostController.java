package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardUpdatePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new ServletException(getClass().getName()+" POST 방식만 접근가능");
		}
		
		long postNo=Long.parseLong(request.getParameter("postNo"));
		String category=request.getParameter("category");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		QnAPostVO qnaPostVO=new QnAPostVO();
		qnaPostVO.setPostNo(postNo);
		qnaPostVO.setCategory(category);
		qnaPostVO.setTitle(title);
		qnaPostVO.setContent(content);
		QnABoardDAO.getInstance().updatePost(qnaPostVO);
		return "redirect:QnABoardFindPostListController.do";
	}
}
