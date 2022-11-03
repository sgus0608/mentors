package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class FreeBoardUpdatePostFormController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		PostVO postVO = FreeBoardDAO.getInstance().postDetailByNo(postNo);
		request.setAttribute("url", "freeBoard/freeboard-update-form.jsp");
		request.setAttribute("postVO", postVO);
		return "layout.jsp";
	}

}
