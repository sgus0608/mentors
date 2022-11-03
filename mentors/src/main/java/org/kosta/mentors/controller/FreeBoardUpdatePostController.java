package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class FreeBoardUpdatePostController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("POST") == false) 
			throw new ServletException("FreeBoardUpdatePostController는 POST METHOD 방식만 가능");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		PostVO postVO=new PostVO();
		postVO.setTitle(title);
		postVO.setContent(content);
		postVO.setPostNo(postNo);
		FreeBoardDAO.getInstance().updatePost(postVO);
		return "redirect:FreeBoardFindPostListController.do";
	}
}
