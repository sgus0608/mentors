package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardWritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new Exception(getClass().getName()+"POST 방식방 가능합니다");
		}		
		String category=request.getParameter("category");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		TipsPostVO	tipsPostVO=new TipsPostVO(title, content, category, memberVO);
		TipsBoardDAO.getInstance().writePost(tipsPostVO);		
		return "redirect:TipsBoardFindPostListController.do";
	}

}
