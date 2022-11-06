package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnACommentDAO;

public class TestCaseQnACommentWriteComment {
	public static void main(String[] args) {
		String commentContent="댓글내용~";
		long postNo=63;
		String id="spring";
		MemberVO memberVO=new MemberVO();
		memberVO.setId(id);
		CommentVO commentVO=new CommentVO(commentContent, postNo, memberVO);
		try {
			QnACommentDAO.getInstance().writeComment(commentVO);
			System.out.println("댓글 작성 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
