package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		ArrayList<TipsPostVO> list=tipsBoardDAO.findPostList();
		request.setAttribute("list", list);
		request.setAttribute("url", "tipsBoard/tipsboard-list.jsp");
		return "layout.jsp";
	}

}
