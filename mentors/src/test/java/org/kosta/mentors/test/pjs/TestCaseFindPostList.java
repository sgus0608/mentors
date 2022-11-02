package org.kosta.mentors.test.pjs;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseFindPostList {
	public static void main(String[] args) {
		QnABoardDAO qnaBoardDAO = QnABoardDAO.getInstance();
		ArrayList<QnAPostVO> list;
		try {
			list = qnaBoardDAO.findPostList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
