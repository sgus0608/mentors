package org.kosta.mentors.test.pjs;

import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseQnASearchPostListByContent {
	public static void main(String[] args) {
		try {
			String searchText="나이";
			long totalCountPost=QnABoardDAO.getInstance().getTotalPostCountByContent(searchText);
			System.out.println("총게시물수: "+totalCountPost+"개"); // 총게시물 수를 확인하고 Pagination 의 매개변수로 사용 
			Pagination pagination=new Pagination(totalCountPost); // Pagination 객체 생성
			ArrayList<QnAPostVO> list=QnABoardDAO.getInstance().searchPostListByContent(searchText,pagination);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
