package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		TipsPostVO tipsPostVO=tipsBoardDAO.postDetailByNo(postNo);
		request.setAttribute("tipsPostVO", tipsPostVO);
		request.setAttribute("url", "tipsBoard/tipsboard-post-detail.jsp");
		return "layout.jsp";
	}
}
