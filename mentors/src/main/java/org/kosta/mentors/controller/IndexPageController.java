package org.kosta.mentors.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.PostVO;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class IndexPageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long totalPostCountOfMentoring = MentoringBoardDAO.getInstance().getTotalPostCount();
		long totalPostCountOfFree = FreeBoardDAO.getInstance().getTotalPostCount();
		long totalPostCountOfQnA = QnABoardDAO.getInstance().getTotalPostCount();
		long totalPostCountOfTips = TipsBoardDAO.getInstance().getTotalPostCount();
		
		Pagination paginationOfMentoring = new Pagination(totalPostCountOfMentoring);
		ArrayList<MentoringPostVO> mentoringList = MentoringBoardDAO.getInstance().findPostList(paginationOfMentoring);
		
		Pagination paginationOfFree = new Pagination(totalPostCountOfFree);
		ArrayList<PostVO> freeList = FreeBoardDAO.getInstance().findPostList(paginationOfFree);
		
		Pagination paginationOfQnA = new Pagination(totalPostCountOfQnA);
		ArrayList<QnAPostVO> qnaList = QnABoardDAO.getInstance().findPostList(paginationOfQnA);
		
		Pagination paginationOfTips = new Pagination(totalPostCountOfTips);
		ArrayList<TipsPostVO> tipsList = TipsBoardDAO.getInstance().findPostList(paginationOfTips);
		
		request.setAttribute("mentoringList", mentoringList);
		request.setAttribute("freeList", freeList);
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("tipsList", tipsList);
		
		return "indexRecent.jsp";
	}

}
