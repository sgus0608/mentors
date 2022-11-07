package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseTipsSearchPostListByContent {
	public static void main(String[] args) {
		try {
			String searchText="있지";
			long totalCountPost=TipsBoardDAO.getInstance().getTotalPostCountByContent(searchText);
			System.out.println("총 게시물수:"+totalCountPost+"개");
			Pagination pagination=new Pagination(totalCountPost);
			ArrayList<TipsPostVO> list=TipsBoardDAO.getInstance().searchPostListByContent(searchText,pagination);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
