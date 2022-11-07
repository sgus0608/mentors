package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QuizDAO;

public class QuizFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String quizNo = request.getParameter("quiz_no");
		Pagination pagination = null;
		long totalPostCount = QuizDAO.getInstance().getTotalPostCount();
		if(quizNo==null) {
			pagination = new Pagination(totalPostCount);
		}else {
			pagination=new Pagination(totalPostCount,Long.parseLong(quizNo));
		}
		
		request.setAttribute("list", QuizDAO.getInstance().FindPostList(pagination));
		request.setAttribute("pagination", pagination);
		request.setAttribute("url","quizBoard/quiz-list.jsp");
		
		return "layout.jsp";
	}

}
