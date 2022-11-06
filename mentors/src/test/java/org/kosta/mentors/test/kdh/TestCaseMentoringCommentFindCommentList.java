package org.kosta.mentors.test.kdh;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MentoringCommentDAO;

public class TestCaseMentoringCommentFindCommentList {
	public static void main(String[] args) {
		try {
			long postNo = 35;
			ArrayList<CommentVO> commentList = MentoringCommentDAO.getInstance().findCommentList(postNo);
			for(int i=0; i<commentList.size(); i++) {
				System.out.println(commentList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
