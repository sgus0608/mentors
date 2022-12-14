package org.kosta.mentors.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardUpdatePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false)
			throw new ServletException("TipsBoardUpdatePostController는 Post METHOD 방식만 가능");
		
		Long postNo=Long.parseLong(request.getParameter("postNo"));
		String category=request.getParameter("category");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		TipsPostVO tipsPostVO=new TipsPostVO();
		tipsPostVO.setTitle(title);
		tipsPostVO.setCategory(category);
		tipsPostVO.setContent(content);
		tipsPostVO.setPostNo(postNo);
		TipsBoardDAO.getInstance().updatePost(tipsPostVO);		
		return "redirect:TipsBoardFindPostListController.do"; 
	}

}
