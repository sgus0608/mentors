package org.kosta.mentors.test.kdh;

import java.sql.SQLException;

import org.kosta.mentors.model.MentoringBoardDAO;

public class TestCassMentoringDeletePost {

	public static void main(String[] args) {
		try {
			long postNo = 4;
			MentoringBoardDAO.getInstance().deletePost(postNo);
			System.out.println("삭제완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
