package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;
import org.kosta.mentors.model.Pagination;

public class MentoringBoardSearchPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo =request.getParameter("pageNo");
		String category = request.getParameter("category");
		String searchText = request.getParameter("searchText");
		Pagination pagination = null;
		ArrayList<MentoringPostVO> list = null;
		long totalPostCount = 0;
		
		if(category.equalsIgnoreCase("제목")) {
			totalPostCount = MentoringBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			if(pageNo == null) {
				pagination = new Pagination(totalPostCount);				
			} else {
				pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
			}
			list = MentoringBoardDAO.getInstance().searchPostListByTitle(searchText, pagination);
		}
		request.setAttribute("category", category);
		request.setAttribute("searchText", searchText);
		
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "mentoringBoard/mentoringboard-search-list.jsp");
		return "layout.jsp";
	}

}
