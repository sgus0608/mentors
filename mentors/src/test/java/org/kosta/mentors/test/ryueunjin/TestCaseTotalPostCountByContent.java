package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import org.kosta.mentors.model.TipsBoardDAO;


public class TestCaseTotalPostCountByContent {
	public static void main(String[] args) {
		String searchText="있지";
		long totalPostCount;
		try {
			totalPostCount = TipsBoardDAO.getInstance().getTotalPostCountByContent(searchText);
			System.out.println("총게시물수:"+totalPostCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
