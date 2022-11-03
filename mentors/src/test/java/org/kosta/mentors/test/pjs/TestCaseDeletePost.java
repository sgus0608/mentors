package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseDeletePost {
	public static void main(String[] args) {
		long no=7;
		try {
			QnABoardDAO.getInstance().deletePost(no);
			System.out.println(no+"번 글이 삭제되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
