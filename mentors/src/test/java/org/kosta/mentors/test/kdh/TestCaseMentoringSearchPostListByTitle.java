package org.kosta.mentors.test.kdh;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;
import org.kosta.mentors.model.Pagination;

public class TestCaseMentoringSearchPostListByTitle {
	public static void main(String[] args) {
		try {
			String searchText = "사자";
			long totalPostCount = MentoringBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			System.out.println(totalPostCount);
			Pagination pagination = new Pagination(totalPostCount);
			ArrayList<MentoringPostVO> list = MentoringBoardDAO.getInstance().searchPostListByTitle(searchText, pagination);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
