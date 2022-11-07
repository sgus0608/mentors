package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnACommentDAO;

public class TestCaseQnACommentDeletByCommentNo {
	public static void main(String[] args) {
		long commetNo=5;
		try {
			QnACommentDAO.getInstance().deleteComment(commetNo);
			System.out.println("댓글이 삭제되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
