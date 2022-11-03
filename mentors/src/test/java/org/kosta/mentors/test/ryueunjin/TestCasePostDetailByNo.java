package org.kosta.mentors.test.ryueunjin;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCasePostDetailByNo {
	public static void main(String[] args) {
		try {
			long postNo=1;
			TipsPostVO tipsPostVO=TipsBoardDAO.getInstance().postDetailByNo(postNo);
			System.out.println(tipsPostVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
