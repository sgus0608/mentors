package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		QnAPostVO qnaPostVO=qnaBoardDAO.postDetailByNo(postNo);
		
		ArrayList<String> categoryList=new ArrayList<>();
		categoryList.add("프로그래밍");
		categoryList.add("취업");
		categoryList.add("자격증");
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("qnaPostVO", qnaPostVO);
		request.setAttribute("qnaMenuBar", true);
		request.setAttribute("url", "qnaBoard/qnaboard-update-form.jsp");
		return "layout.jsp";
	}
}
