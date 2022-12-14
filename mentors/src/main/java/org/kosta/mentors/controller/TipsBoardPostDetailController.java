package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsCommentDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TipsBoardPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		HttpSession session=request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list=(ArrayList<Long>) session.getAttribute("tipsboard");
		if(list.contains(postNo)==false) {
			TipsBoardDAO.getInstance().updateHits(postNo);;
			list.add(postNo);
		}
		
		TipsPostVO tipsPostVO=tipsBoardDAO.postDetailByNo(postNo);
		ArrayList<CommentVO> commentList=TipsCommentDAO.getInstance().findCommentList(postNo);
		
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		String id=memberVO.getId();
		boolean checkLike=TipsBoardDAO.getInstance().checkLike(postNo, id);		
		
		request.setAttribute("totalLikeCount",TipsBoardDAO.getInstance().getTotalLikeCount(postNo));
		request.setAttribute("likeFlag", checkLike);
		request.setAttribute("tipsPostVO", tipsPostVO);
		request.setAttribute("commentList", commentList);
		request.setAttribute("tipsMenuBar", true);
		request.setAttribute("url", "tipsBoard/tipsboard-post-detail.jsp");
		return "layout.jsp";
	}
}
