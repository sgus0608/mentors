package org.kosta.mentors.test.pjs;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseFindPostList {
	public static void main(String[] args) {
		QnABoardDAO qnaBoardDAO = QnABoardDAO.getInstance();
		ArrayList<QnAPostVO> list;
		try {
			Pagination pagination=new Pagination(10);
			list = qnaBoardDAO.findPostList(pagination);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
