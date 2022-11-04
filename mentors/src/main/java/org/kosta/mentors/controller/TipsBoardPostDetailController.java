package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		HttpSession session=request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list=(ArrayList<Long>) session.getAttribute("tipsboard");
		if(list.contains(postNo)==false) {
			TipsBoardDAO.getInstance().updateHits(postNo);;
			list.add(postNo);
		}
		TipsPostVO tipsPostVO=tipsBoardDAO.postDetailByNo(postNo);
		request.setAttribute("tipsPostVO", tipsPostVO);
		request.setAttribute("url", "tipsBoard/tipsboard-post-detail.jsp");
		return "layout.jsp";
	}
}
