package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.PostVO;

public class FreeBoardSearchPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo=request.getParameter("pageNo");
		String category =request.getParameter("category");
		String searchText=request.getParameter("searchText");
		Pagination pagination=null;
		ArrayList<PostVO> list= null;
		long totalPostCount=0;
		
		
		if(category.equalsIgnoreCase("제목")) {
			totalPostCount=FreeBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			if(pageNo==null) {	
				pagination=new Pagination(totalPostCount);
			} else {
				pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
			}
			list=FreeBoardDAO.getInstance().searchPostListByTitle(searchText, pagination);
		} else if(category.equalsIgnoreCase("내용")) {
			totalPostCount=FreeBoardDAO.getInstance().getTotalPostCountByContent(searchText);
			if(pageNo==null) {	
				pagination=new Pagination(totalPostCount);
			} else {
				pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
			}
			list=FreeBoardDAO.getInstance().searchPostListByContent(searchText, pagination);
		} else if(category.equalsIgnoreCase("작성자")) {
			totalPostCount=FreeBoardDAO.getInstance().getTotalPostCountByNickName(searchText);
			if(pageNo==null) {	
				pagination=new Pagination(totalPostCount);
			} else {
				pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
			}
			list=FreeBoardDAO.getInstance().searchPostListByNickName(searchText, pagination);
		}
			
		request.setAttribute("category", category);
		request.setAttribute("searchText", searchText);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "freeBoard/freeboard-search-list.jsp");
		return "layout.jsp";
	}

}
