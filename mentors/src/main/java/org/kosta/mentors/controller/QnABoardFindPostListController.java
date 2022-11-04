package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNo=request.getParameter("pageNo");
		long totalPostCount=QnABoardDAO.getInstance().getTotalPostCount();
		Pagination pagination=null;
		if(pageNo==null) {
			pagination=new Pagination(totalPostCount);
		}else {
			pagination=new Pagination(totalPostCount, Long.parseLong(pageNo));
		}
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		ArrayList<QnAPostVO> list=qnaBoardDAO.findPostList(pagination);
		request.setAttribute("list", list);
		request.setAttribute("pagination", pagination);
		request.setAttribute("url", "qnaBoard/qnaboard-list.jsp");
		return "layout.jsp";
	}
}
