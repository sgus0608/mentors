package org.kosta.mentors.test.khj;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.PostVO;

public class TestCaseFreeBoardSearchPostList {
	public static void main(String[] args) {
		try {
			String searchText = "갑니다";
			long totalPostCount = FreeBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			System.out.println(totalPostCount);
			Pagination pagination=new Pagination(totalPostCount);
			ArrayList<PostVO> list = FreeBoardDAO.getInstance().searchPostListByTitle(searchText, pagination);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
