package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardFindPostListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<MentoringPostVO> list = MentoringBoardDAO.getInstance().findPostList();
		request.setAttribute("list", list);
		request.setAttribute("url", "mentoringBoard/mentoringboard-list.jsp");
		return "layout.jsp";
	}

}
