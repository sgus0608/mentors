package org.kosta.mentors.test.pjs;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.QnACommentDAO;

public class TestCaseQnACommentFindCommentList {
	public static void main(String[] args) {
		long postNo=63;
		ArrayList<CommentVO> commentList;
		try {
			commentList = QnACommentDAO.getInstance().findCommentList(postNo);
			for(int i=0;i<commentList.size();i++) {
				System.out.println(commentList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
