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
		String category = request.getParameter("category");
		String searchText = null;
		Pagination pagination = null;
		ArrayList<MentoringPostVO> list = null;
		long totalPostCount = 0;
		MentoringBoardDAO dao = MentoringBoardDAO.getInstance();
		
		if(category == null) {
			totalPostCount = dao.getTotalPostCount();
			if(pageNo == null) {
				pagination = new Pagination(totalPostCount);
			} else {
				pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
			}
			list = dao.findPostList(pagination);
		} else {
			searchText = request.getParameter("searchText");
			if(category.equalsIgnoreCase("제목")) {
				totalPostCount = dao.getTotalPostCountByTitle(searchText);
				if(pageNo == null) {
					pagination = new Pagination(totalPostCount);				
				} else {
					pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
				}
				list = dao.searchPostListByTitle(searchText, pagination);
			} else if(category.equalsIgnoreCase("내용")) {
				totalPostCount = dao.getTotalPostCountByContent(searchText);
				if(pageNo == null) {
					pagination = new Pagination(totalPostCount);
				} else {
					pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
				}
				list = dao.searchPostListByContent(searchText, pagination);
			} else if(category.equalsIgnoreCase("작성자")) {
				totalPostCount = dao.getTotalPostCountByNickName(searchText);
				if(pageNo == null) {
					pagination = new Pagination(totalPostCount);
				} else {
					pagination = new Pagination(totalPostCount, Long.parseLong(pageNo));
				}
				list = dao.searchPostListByNickName(searchText, pagination);
			}
		}
		
		request.setAttribute("category", category);
		request.setAttribute("searchText", searchText);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("mentoringMenuBar", true);
		request.setAttribute("url", "mentoringBoard/mentoringboard-list.jsp");
		return "layout.jsp";
	}

}
