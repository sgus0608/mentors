package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.Pagination;



public class FreeBoardFindPostListController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo=request.getParameter("pageNo");
		Pagination pagination=null;
		long totalPostCount=FreeBoardDAO.getInstance().getTotalPostCount();
		if(pageNo==null) {
			pagination=new Pagination(totalPostCount);
		} else {
			pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
		}
		request.setAttribute("list", FreeBoardDAO.getInstance().findPostList(pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "freeBoard/freeboard-list.jsp");
		return "layout.jsp";
	}

}
