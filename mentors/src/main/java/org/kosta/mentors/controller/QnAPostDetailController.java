package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnAPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		QnAPostVO qnaPostVO=qnaBoardDAO.postDetailByNo(postNo);
		request.setAttribute("qnaPostVO", qnaPostVO);
		request.setAttribute("url", "qnaBoard/qnaboard-post-detail.jsp");
		return "layout.jsp";
	}
}
