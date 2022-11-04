package org.kosta.mentors.test.kdh;

import java.util.ArrayList;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;
import org.kosta.mentors.model.Pagination;

public class TestCaseMentoringFindPostList {
	public static void main(String[] args) {
		try {
			long totalPostCount = MentoringBoardDAO.getInstance().getTotalPostCount();
			System.out.println(totalPostCount);
			Pagination pagination = new Pagination(10,2);
			ArrayList<MentoringPostVO> list = MentoringBoardDAO.getInstance().findPostList(pagination);
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
