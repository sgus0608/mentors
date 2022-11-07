package org.kosta.mentors.test.khj;

import org.kosta.mentors.model.FreeCommentDAO;

public class TestCaseDeleteComment {
	public static void main(String[] args) {
		try {
			long commentNo=2L;
			FreeCommentDAO.getInstance().deleteComment(commentNo);
			System.out.println(commentNo+"번 글 삭제완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
