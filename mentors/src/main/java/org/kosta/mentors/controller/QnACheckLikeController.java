package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;

public class QnACheckLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		String id=memberVO.getId();
		
		boolean result=QnABoardDAO.getInstance().checkLike(postNo, id);
		String message=null;
		if(result) {
			QnABoardDAO.getInstance().deleteLike(id, postNo);
			message="fail";
		}else {
			QnABoardDAO.getInstance().insertLike(id, postNo);
			message="ok";
		}
		
		long countLike=QnABoardDAO.getInstance().getTotalLikeCount(postNo);
		JSONObject json=new JSONObject();
		json.put("message", message);
		json.put("countLike", countLike);
		
		request.setAttribute("responsebody", json);//AjaxViewServlet이 클라이언트에게 응답하도록 저장
		return "AjaxView";
	}
}
