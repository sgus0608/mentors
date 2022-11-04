package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;

import org.kosta.mentors.model.TipsBoardDAO;

public class TestCaseUpdateHits {
	public static void main(String[] args) {
		try {
			long postNo = 35;
			TipsBoardDAO.getInstance().updateHits(postNo);
			System.out.println("조회수 증가");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}