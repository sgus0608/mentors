package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo=request.getParameter("pageNo");
		long totalPostCount=TipsBoardDAO.getInstance().getTotalPostCount();
		Pagination pagination=null;
		if(pageNo==null) {
			pagination=new Pagination(totalPostCount);
		}else {
			pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
		}
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		ArrayList<TipsPostVO> list=tipsBoardDAO.findPostList(pagination);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "tipsBoard/tipsboard-list.jsp");
		return "layout.jsp";
	}

}
