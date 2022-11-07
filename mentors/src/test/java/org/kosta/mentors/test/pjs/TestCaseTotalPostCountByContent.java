package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;

public class TestCaseTotalPostCountByContent {
	public static void main(String[] args) {
		String searchText="나이";
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		long totalCountPost;
		try {
			totalCountPost = qnaBoardDAO.getTotalPostCountByContent(searchText);
			System.out.println("총게시물수 :"+totalCountPost+"개") ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}