package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;

public class TestCaseLike {
	public static void main(String[] args) {
		long postNo=0;
		String id="java";
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		try {
			boolean result=qnaBoardDAO.checkLike(postNo, id);
			System.out.println("result : "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
