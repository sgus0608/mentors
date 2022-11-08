package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.json.JSONObject;
import org.kosta.mentors.model.MemberVO;

public class MentoringCheckLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String id = mvo.getId();
		
		boolean result=MentoringBoardDAO.getInstance().checkLike(postNo, id);
		String message=null;
		
		if(result) {
			MentoringBoardDAO.getInstance().deleteLike(postNo, id);
			message="fail";
		}else {
			MentoringBoardDAO.getInstance().insertLike(postNo, id);
			message="ok";
		}
		
		long countLike = MentoringBoardDAO.getInstance().getTotalLikeCount(postNo);
		
		JSONObject json = new JSONObject();
		json.put("message", message);
		json.put("countLike", countLike);
		
		request.setAttribute("responsebody", json);//AjaxViewServlet이 클라이언트에게 응답하도록 저장 
		return "AjaxView";
	}

}
