package org.kosta.mentors.test.ryueunjin;

import org.kosta.mentors.model.TipsBoardDAO;

public class TestCaseDeletePost {
	public static void main(String[] args) {
		try {
			long no=1L;
			TipsBoardDAO.getInstance().deletePost(no);
			System.out.println(no+"번 글 삭제 완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
