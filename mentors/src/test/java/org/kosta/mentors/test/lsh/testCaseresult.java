package org.kosta.mentors.test.lsh;

import java.sql.SQLException;

import org.kosta.mentors.model.QuizDAO;

public class testCaseresult {
	public static void main(String[] args) {
		long postNo=1;
		try {
			String answer= QuizDAO.getInstance().checkResult(postNo);
			System.out.println(answer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
