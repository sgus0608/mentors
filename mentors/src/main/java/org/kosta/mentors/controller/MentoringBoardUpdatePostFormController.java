package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		MentoringPostVO postVO = MentoringBoardDAO.getInstance().postDetailByNo(postNo);
		request.setAttribute("postVO", postVO);
		request.setAttribute("url", "mentoringBoard/mentoringboard-update-form.jsp");
		return "layout.jsp";
	}

}
