package org.kosta.mentors.test.ryueunjin;

import org.kosta.mentors.model.TipsCommentDAO;

public class TestCaseTipsCommentDeleteByCommentNo {
	public static void main(String[] args) {
		try {
			long commentNo=1L;
			TipsCommentDAO.getInstance().deleteComment(commentNo);
			System.out.println(commentNo+"번 댓글 삭제완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
