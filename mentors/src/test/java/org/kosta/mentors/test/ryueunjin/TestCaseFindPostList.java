package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseFindPostList {
	public static void main(String[] args) {
		TipsBoardDAO tipsBoardDAO = TipsBoardDAO.getInstance();
		ArrayList<TipsPostVO> list;
		try {
			list = tipsBoardDAO.findPostList();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
