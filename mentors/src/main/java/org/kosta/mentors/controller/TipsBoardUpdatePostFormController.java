package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long  postNo=Long.parseLong(request.getParameter("postNo"));
		TipsPostVO postVO=TipsBoardDAO.getInstance().postDetailByNo(postNo);
		request.setAttribute("tipsPostVO",postVO);		
		request.setAttribute("url", "tipsBoard/tipsboard-update-form.jsp");
		return "layout.jsp";
	}

}
