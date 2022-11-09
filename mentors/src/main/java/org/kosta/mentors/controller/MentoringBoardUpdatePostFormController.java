package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		MentoringPostVO postVO = MentoringBoardDAO.getInstance().postDetailByNo(postNo);
		
		ArrayList<String> categoryList = new ArrayList<>();
		categoryList.add("프로그래밍");
		categoryList.add("알고리즘");
		categoryList.add("전공지식");
		categoryList.add("취업");
		categoryList.add("자격증");
		ArrayList<String> roleList = new ArrayList<>();
		roleList.add("멘토");
		roleList.add("멘티");
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("roleList", roleList);
		
		request.setAttribute("postVO", postVO);
		request.setAttribute("url", "mentoringBoard/mentoringboard-update-form.jsp");
		return "layout.jsp";
	}

}
