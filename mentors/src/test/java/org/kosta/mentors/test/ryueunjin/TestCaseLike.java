package org.kosta.mentors.test.ryueunjin;

import org.kosta.mentors.model.TipsBoardDAO;

public class TestCaseLike {
	public static void main(String[] args) {
		long postNo=0;
		String id="java";
		TipsBoardDAO tipsBoardDAO=TipsBoardDAO.getInstance();
		try {
			boolean result=tipsBoardDAO.checkLike(postNo,id);
			System.out.println("result "+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
