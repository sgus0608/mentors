package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardWritePostController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("POST")==false) {
			throw new Exception(getClass().getName()+" Post 방식만 가능합니다");
		}
		
		String category=request.getParameter("category");
		String title=request.getParameter("title");
		String content=request.getParameter("content"); // 
		
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		QnAPostVO qnaPostVO=new QnAPostVO(title, content, category, memberVO);
		QnABoardDAO.getInstance().writePost(qnaPostVO);
		return "redirect:QnABoardFindPostListController.do";
	}
}
