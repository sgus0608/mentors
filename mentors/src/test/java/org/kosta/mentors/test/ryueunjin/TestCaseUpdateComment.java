package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.TipsCommentDAO;

public class TestCaseUpdateComment {
	public static void main(String[] args) {
		long commentNo=4;
		String commentContent="내가 진짜 진짜로 짱이야";
		CommentVO commentVO=new CommentVO();
		commentVO.setCommentNo(commentNo);
		commentVO.setCommentContent(commentContent);		
		try {
			TipsCommentDAO.getInstance().updateComment(commentVO);
			System.out.println(commentNo+"번 댓글 수정결과:"+commentContent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
