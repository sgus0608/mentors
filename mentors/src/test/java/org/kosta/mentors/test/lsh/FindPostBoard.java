package org.kosta.mentors.test.lsh;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.QuizDAO;
import org.kosta.mentors.model.QuizVO;

public class FindPostBoard {
	public static void main(String[] args) {
	try {
		ArrayList<QuizVO> list = QuizDAO.getInstance().FindPostList();
		for(int i=0; i<list.size();i++)
		System.out.println(list.get(i));
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
