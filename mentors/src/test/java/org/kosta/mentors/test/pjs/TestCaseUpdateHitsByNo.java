package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;

public class TestCaseUpdateHitsByNo {
	public static void main(String[] args) {
		long postNo=63;
		try {
			QnABoardDAO.getInstance().updateHits(postNo);
			System.out.println("조회수 증가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
