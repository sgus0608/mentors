package org.kosta.mentors.test.khj;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.PostVO;

public class TestCaseFreeBoardSearchPostNickNameList {
	public static void main(String[] args) {
		try {
			String searchText = "아이유";
			long totalPostCount = FreeBoardDAO.getInstance().getTotalPostCountByNickName(searchText);
			System.out.println(totalPostCount);
			Pagination pagination=new Pagination(totalPostCount);
			ArrayList<PostVO> list = FreeBoardDAO.getInstance().searchPostListByNickName(searchText, pagination);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
