package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;

public class FreeBoardDeletePostController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		FreeBoardDAO.getInstance().deletePost(postNo);
		return "redirect:FreeBoardFindPostListController.do";
	}
}
