package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;

public class FreeBoardDeletePostController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("POST") == false) 
			throw new ServletException(getClass().getName()+"는 POST METHOD 방식만 가능");
		long postNo=Long.parseLong(request.getParameter("postNo"));
		FreeBoardDAO.getInstance().deletePost(postNo);
		return "redirect:FreeBoardFindPostListController.do";
	}
}
