package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardUpdatePostFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long  postNo=Long.parseLong(request.getParameter("postNo"));
		TipsPostVO postVO=TipsBoardDAO.getInstance().postDetailByNo(postNo);
		
		ArrayList<String> categoryList=new ArrayList<>();
		categoryList.add("뉴스");
		categoryList.add("후기");
		categoryList.add("추천");
		categoryList.add("꿀팁");
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("tipsPostVO",postVO);
		request.setAttribute("tipsMenuBar", true);
		request.setAttribute("url", "tipsBoard/tipsboard-update-form.jsp");
		return "layout.jsp";
	}

}
