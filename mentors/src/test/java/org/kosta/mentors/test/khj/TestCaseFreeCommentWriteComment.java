package org.kosta.mentors.test.khj;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.FreeCommentDAO;
import org.kosta.mentors.model.MemberVO;

public class TestCaseFreeCommentWriteComment {
	public static void main(String[] args) {
		String commentContent="댓글 내용!!";
		long postNo =40;
		String id ="spring";
		MemberVO memberVO=new MemberVO();
		memberVO.setId(id);
		CommentVO commentVO = new CommentVO(commentContent, postNo, memberVO);
		try {
			FreeCommentDAO.getInstance().writeComment(commentVO);
			System.out.println("댓글 작성 완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
