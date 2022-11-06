package org.kosta.mentors.test.pjs;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseQnASearchPostList {
	public static void main(String[] args) {
		try {
			String searchText="질문";
			long totalPostCount=QnABoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			System.out.println(totalPostCount);
			Pagination pagination=new Pagination(totalPostCount);
			ArrayList<QnAPostVO> list=QnABoardDAO.getInstance().searchPostListByTitle(searchText,pagination);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
