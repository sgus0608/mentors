package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardSearchPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo=request.getParameter("pageNo");
		String category=request.getParameter("category");
		String searchText=request.getParameter("searchText");
		Pagination pagination=null;
		ArrayList<TipsPostVO> list=null;
		long totalPostCount=0;
		if(category.equalsIgnoreCase("제목")) {
			totalPostCount=TipsBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			if(pageNo==null) {
				pagination=new Pagination(totalPostCount);
			}else {
				pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
			}
			list=TipsBoardDAO.getInstance().searchPostListByTitle(searchText, pagination);
		}
		request.setAttribute("category", category);		
		request.setAttribute("searchText", searchText);	
		
		request.setAttribute("list", list);		
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "tipsBoard/tipsboard-search-list.jsp");
		return "layout.jsp";
	}
}
