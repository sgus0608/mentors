package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;

public class TipsPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("TipsPostVO", TipsBoardDAO.getInstance().findPostByNo(Long.parseLong(request.getParameter("postNo"))));
		return "tipsBoard/post-detail.jsp";
	}

}
