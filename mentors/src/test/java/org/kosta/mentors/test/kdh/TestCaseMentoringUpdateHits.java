package org.kosta.mentors.test.kdh;

import java.sql.SQLException;

import org.kosta.mentors.model.MentoringBoardDAO;

public class TestCaseMentoringUpdateHits {

	public static void main(String[] args) {
		try {
			long postNo = 35;
			MentoringBoardDAO.getInstance().updateHits(postNo);
			System.out.println("조회수 증가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
