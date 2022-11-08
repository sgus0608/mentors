package org.kosta.mentors.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsBoardDAO;

public class TipsCheckLikeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");//MemberVO 객체를 담고 있는 정보
		String id = memberVO.getId();
		long postNo=Long.parseLong(request.getParameter("postNo"));
		boolean result=TipsBoardDAO.getInstance().checkLike(postNo,id);
		String message=null;
		if(result==true) {
			message="fail";
			TipsBoardDAO.getInstance().deleteLike(id, postNo);
		}else {
			message="ok";
			TipsBoardDAO.getInstance().insertLike(id, postNo);
		}
		request.setAttribute("responsebody", message);//AjaxViewServlet이 클라이언트에게 응답하도록 저장 
		return "AjaxView";
	}

}
