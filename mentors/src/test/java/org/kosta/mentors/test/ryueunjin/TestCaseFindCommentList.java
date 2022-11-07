package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.TipsCommentDAO;

public class TestCaseFindCommentList {
	public static void main(String[] args) {
		try {
			long postNo = 26;
			ArrayList<CommentVO> commentList;
			commentList = TipsCommentDAO.getInstance().findCommentList(postNo);
			for (int i = 0; i < commentList.size(); i++) {
				System.out.println(commentList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
