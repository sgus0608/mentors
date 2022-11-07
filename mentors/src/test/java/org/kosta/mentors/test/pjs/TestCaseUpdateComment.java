package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.QnACommentDAO;

public class TestCaseUpdateComment {
	public static void main(String[] args) {
		long commentNo=22;
		String commentContent="침공 어찌 목만오셨소..";
		CommentVO commentVO=new CommentVO();
		commentVO.setCommentNo(commentNo);
		commentVO.setCommentContent(commentContent);
		try {
			QnACommentDAO.getInstance().updateComment(commentVO);
			System.out.println(commentNo+"글 수정결과 :"+commentContent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
