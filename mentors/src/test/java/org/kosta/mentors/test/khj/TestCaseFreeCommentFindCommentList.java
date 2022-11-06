package org.kosta.mentors.test.khj;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeCommentDAO;

public class TestCaseFreeCommentFindCommentList {
	public static void main(String[] args) {
		long postNo =40;
		ArrayList<CommentVO> commentList;
		try {
			commentList = FreeCommentDAO.getInstance().findCommentList(postNo);
			for(int i=0; i<commentList.size(); i++) {
				System.out.println(commentList.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
