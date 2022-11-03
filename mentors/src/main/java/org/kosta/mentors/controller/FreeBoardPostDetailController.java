package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class FreeBoardPostDetailController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PostVO postVO=FreeBoardDAO.getInstance().postDetailByNo(Long.parseLong(request.getParameter("postNo")));
		request.setAttribute("postVO", postVO);
		request.setAttribute("url", "freeBoard/freeboard-post-detail.jsp");
		return "layout.jsp";
	}

}
