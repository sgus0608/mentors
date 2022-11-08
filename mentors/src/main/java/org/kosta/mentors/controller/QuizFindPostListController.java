package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QuizDAO;

public class QuizFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNo = request.getParameter("quiz_no");
		Pagination pagination = null;
		long totalPostCount = QuizDAO.getInstance().getTotalPostCount();
		if(pageNo==null) {
			pagination = new Pagination(totalPostCount);
		}else {
			pagination=new Pagination(totalPostCount,Long.parseLong(pageNo));
		}
		
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String id = mvo.getId();
		
		request.setAttribute("list", QuizDAO.getInstance().FindPostList(pagination,id));
		
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("quizMenuBar", true);
		request.setAttribute("url","quizBoard/quiz-list.jsp");
		
		return "layout.jsp";
	}

}
