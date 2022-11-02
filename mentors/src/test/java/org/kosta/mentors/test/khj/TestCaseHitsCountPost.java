package org.kosta.mentors.test.khj;

import org.kosta.mentors.model.FreeBoardDAO;

public class TestCaseHitsCountPost {
	public static void main(String[] args) {
		try {
			long post_no=2L;
			FreeBoardDAO.getInstance().countHitsPost(post_no);
			System.out.println(post_no+"번 글 조회수 증가");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
