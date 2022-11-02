package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;



public class FreeBoardFindPostListController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("list", FreeBoardDAO.getInstance().findPostList());
		request.setAttribute("url", "board/freeBoardList.jsp");
		return "layout.jsp";
	}

}
