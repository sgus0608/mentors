package org.kosta.mentors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QuizDAO;

public class QuizLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long quizNo = Long.parseLong(request.getParameter("quizNo"));
		System.out.println(quizNo);
		//request.setAttribute("count", count);
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String id = mvo.getId();
		int result = QuizDAO.getInstance().checkLike(quizNo, id);
		
		JSONObject json = new JSONObject();
		
		String message = null;
		if(result==0) {
			QuizDAO.getInstance().quizLikeInsert(quizNo, id);
			message = "ok";
		}else {
			QuizDAO.getInstance().quizLikeDelete(id);
			message="fail";
		}
		long count = QuizDAO.getInstance().quizLikeCount(quizNo);
		json.put("count", count);
		json.put("message", message);
		request.setAttribute("responsebody",json);
		return "AjaxView";
	}

}
