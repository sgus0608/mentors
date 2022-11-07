package org.kosta.mentors.test.khj;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeCommentDAO;

public class TestCaseUpdateComment {

	public static void main(String[] args) {
		CommentVO commentVO=new CommentVO();
		commentVO.setCommentContent("월요일은 즐거운 코딩!!");
		commentVO.setCommentNo(4L);
		try {
			FreeCommentDAO.getInstance().updateComment(commentVO);
			System.out.println("수정완");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
