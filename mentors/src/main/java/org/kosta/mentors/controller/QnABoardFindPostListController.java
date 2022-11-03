package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		ArrayList<QnAPostVO> list=qnaBoardDAO.findPostList();
		request.setAttribute("list", list);
		request.setAttribute("url", "qnaBoard/qnaboard-list.jsp");
		return "layout.jsp";
	}
}
