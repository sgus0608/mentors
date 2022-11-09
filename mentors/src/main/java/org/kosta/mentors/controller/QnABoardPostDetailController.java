package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnACommentDAO;
import org.kosta.mentors.model.QnAPostVO;

public class QnABoardPostDetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		HttpSession session=request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list=(ArrayList<Long>) session.getAttribute("qnaboard");
		if(list.contains(postNo)==false) {
			QnABoardDAO.getInstance().updateHits(postNo);
			list.add(postNo);
		}
		QnAPostVO qnaPostVO=qnaBoardDAO.postDetailByNo(postNo);
		ArrayList<CommentVO> commentList=QnACommentDAO.getInstance().findCommentList(postNo);
		
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");
		String id=memberVO.getId();
		boolean checkLike=QnABoardDAO.getInstance().checkLike(postNo, id);
		
		request.setAttribute("totalLikeCount", QnABoardDAO.getInstance().getTotalLikeCount(postNo));
		request.setAttribute("likeFlag", checkLike);
		request.setAttribute("qnaPostVO", qnaPostVO);
		request.setAttribute("commentList", commentList);
		request.setAttribute("qnaMenuBar", true);
		request.setAttribute("url", "qnaBoard/qnaboard-post-detail.jsp");
		return "layout.jsp";
	}
}
