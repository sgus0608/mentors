package org.kosta.mentors.test.khj;

import org.kosta.mentors.model.FreeBoardDAO;

public class TestCaseDeletePost {
	public static void main(String[] args) {
		try {
			long no=1L;
			FreeBoardDAO.getInstance().deletePost(no);
			System.out.println(no+"번 글 삭제완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
