package org.kosta.mentors.test.kdh;

import java.util.ArrayList;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class TestCaseMentoringFindPostList {
	public static void main(String[] args) {
		try {
		ArrayList<MentoringPostVO> list = MentoringBoardDAO.getInstance().findPostList();
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
