package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;

public class QnACheckLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		String id=memberVO.getId();
		long postNo=Long.parseLong(request.getParameter("postNo"));
		boolean result=QnABoardDAO.getInstance().checkLike(postNo, id);
		String message=null;
		if(result) {
			QnABoardDAO.getInstance().deleteLike(id, postNo);
			message="fail";
		}else {
			QnABoardDAO.getInstance().insertLike(id, postNo);
			message="ok";
		}
		request.setAttribute("responsebody", message);
		return "AjaxView";
	}
}
