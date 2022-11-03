package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.PostVO;

public class FreeBoardWritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) 
			throw new ServletException("FreeBoardWritePostController는 POST METHOD 방식만 가능");
		HttpSession session=request.getSession();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		MemberVO id=(MemberVO) session.getAttribute("mvo");
		PostVO postVO=new PostVO();
		postVO.setTitle(title);
		postVO.setContent(content);
		postVO.setMemberVO(id);
		FreeBoardDAO.getInstance().writePost(postVO);
		return "redirect:FreeBoardFindPostListController.do";
	}
}
