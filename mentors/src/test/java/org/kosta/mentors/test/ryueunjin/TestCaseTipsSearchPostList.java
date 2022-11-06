package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.startup.PasswdUserDatabase;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseTipsSearchPostList {
	public static void main(String[] args) {
		try {
			String searchText="앙녕";
			long totalPostCount = TipsBoardDAO.getInstance().getTotalPostCountByTitle(searchText);
			System.out.println(totalPostCount);
			Pagination pagination=new Pagination(totalPostCount);
			ArrayList<TipsPostVO> list=TipsBoardDAO.getInstance().searchPostListByTitle(searchText,pagination);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
