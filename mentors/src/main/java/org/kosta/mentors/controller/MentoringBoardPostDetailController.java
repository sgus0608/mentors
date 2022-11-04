package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list = (ArrayList<Long>) session.getAttribute("mentoringboard");
		if(list.contains(postNo) == false) {
			MentoringBoardDAO.getInstance().updateHits(postNo);
			list.add(postNo);
		}
		
		MentoringPostVO postVO = MentoringBoardDAO.getInstance().postDetailByNo(postNo);
		request.setAttribute("postVO", postVO);
		request.setAttribute("url", "mentoringBoard/mentoringboard-post-detail.jsp");
		return "layout.jsp";
	}

}
