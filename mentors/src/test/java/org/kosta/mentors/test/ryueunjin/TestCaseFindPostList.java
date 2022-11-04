package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseFindPostList {
	public static void main(String[] args) {
		TipsBoardDAO tipsBoardDAO = TipsBoardDAO.getInstance();
		ArrayList<TipsPostVO> list;
		try {
			Pagination pagination=new Pagination(10);
			list = tipsBoardDAO.findPostList(pagination);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
