package org.kosta.mentors.test.khj;

import java.sql.SQLException;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class TestCasePostDetailByNo {
	public static void main(String[] args) {
		try {
			PostVO postVO=FreeBoardDAO.getInstance().postDetailByNo(1L);
			System.out.println(postVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
