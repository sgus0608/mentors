package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.FreeCommentDAO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.PostVO;

public class FreeBoardPostDetailController implements Controller {
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long postNo=Long.parseLong(request.getParameter("postNo"));
		
		// 조회수 증가 및 방지 기능
		HttpSession session=request.getSession(false);
		@SuppressWarnings("unchecked")
		ArrayList<Long> list=(ArrayList<Long>) session.getAttribute("freeboard");
		if(list.contains(postNo)==false) {
			FreeBoardDAO.getInstance().updateHits(postNo);
			list.add(postNo);
		}
		
		PostVO postVO=FreeBoardDAO.getInstance().postDetailByNo(postNo);
		ArrayList<CommentVO> commentList=FreeCommentDAO.getInstance().findCommentList(postNo);
		
		//사용자의 좋아요 유무 판단
		MemberVO memberVO=(MemberVO) session.getAttribute("mvo");//MemberVO 객체를 담고 있는 정보
		String id = memberVO.getId();
		boolean likeFlag =FreeBoardDAO.getInstance().checkLikeFlag(id, postNo);
		
		request.setAttribute("totalLikeCount", FreeBoardDAO.getInstance().likeTotalCount(postNo));
		request.setAttribute("likeFlag", likeFlag);
		request.setAttribute("commentList", commentList);
		request.setAttribute("postVO", postVO);
		request.setAttribute("url", "freeBoard/freeboard-post-detail.jsp");
		return "layout.jsp";
	}

}
