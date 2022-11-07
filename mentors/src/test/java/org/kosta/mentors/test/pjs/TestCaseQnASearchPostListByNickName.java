package org.kosta.mentors.test.pjs;

import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseQnASearchPostListByNickName {
	public static void main(String[] args) {
		try {
			String searchText="아이유";
			long totalCountPost=QnABoardDAO.getInstance().getTotalPostCountByNickName(searchText);
			System.out.println("총게시물수: "+totalCountPost+"개");
			Pagination pagination=new Pagination(totalCountPost);
			ArrayList<QnAPostVO> list=QnABoardDAO.getInstance().searchPostListByNickName(searchText,pagination);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
