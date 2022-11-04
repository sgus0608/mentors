package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;
import org.kosta.mentors.model.Pagination;

public class MentoringBoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo = request.getParameter("pageNo");
		long totalPostCount = MentoringBoardDAO.getInstance().getTotalPostCount();
		Pagination pagination = null;
		if(pageNo == null) {
			pagination = new Pagination(totalPostCount);
		} else {
			pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
		}
		ArrayList<MentoringPostVO> list = MentoringBoardDAO.getInstance().findPostList(pagination);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "mentoringBoard/mentoringboard-list.jsp");
		return "layout.jsp";
	}

}
