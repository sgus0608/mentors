package org.kosta.mentors.test.ryueunjin;

import org.kosta.mentors.model.TipsBoardDAO;

public class TestCaseTotalPostCountByNickName {
	public static void main(String[] args) {
		String searchText="장기하";
		long totalPostCount;
		try {
			totalPostCount=TipsBoardDAO.getInstance().getTotalPostCountByNickName(searchText);
			System.out.println("총게시물수:"+totalPostCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
