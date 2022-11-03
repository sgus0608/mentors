package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardUpdatePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		String category = request.getParameter("category");
		String role = request.getParameter("role");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MentoringPostVO postVO = new MentoringPostVO();
		postVO.setPostNo(postNo);
		postVO.setCategory(category);
		postVO.setRole(role);
		postVO.setTitle(title);
		postVO.setContent(content);
		MentoringBoardDAO.getInstance().updatePost(postVO);
		return "redirect:MentoringBoardFindPostListController.do";
	}

}
