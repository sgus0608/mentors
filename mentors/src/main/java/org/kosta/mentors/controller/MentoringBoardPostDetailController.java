package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringCommentDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class MentoringBoardPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo = Long.parseLong(request.getParameter("postNo"));
		
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list = (ArrayList<Long>) session.getAttribute("mentoringboard");
		if(list.contains(postNo) == false) {
			MentoringBoardDAO.getInstance().updateHits(postNo);
			list.add(postNo);
		}
		
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		boolean checkLike = MentoringBoardDAO.getInstance().checkLike(mvo.getId(), postNo);
		
		MentoringPostVO postVO = MentoringBoardDAO.getInstance().postDetailByNo(postNo);
		ArrayList<CommentVO> commentList = MentoringCommentDAO.getInstance().findCommentList(postNo);
		request.setAttribute("totalLikeCount", MentoringBoardDAO.getInstance().getTotalLikeCount(postNo));
		request.setAttribute("likeFlag", checkLike);
		request.setAttribute("postVO", postVO);
		request.setAttribute("commentList", commentList);
		request.setAttribute("url", "mentoringBoard/mentoringboard-post-detail.jsp");
		return "layout.jsp";
	}

}
