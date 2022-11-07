package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;

import org.kosta.mentors.model.CommentVO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsCommentDAO;

public class TestCaseTipsCommentWriteComment {
	public static void main(String[] args) {
		
		try {
			String commentContent="댓글 내용~";
			long postNo=26;
			String id="spring";
			MemberVO memberVO=new MemberVO();
			memberVO.setId(id);
			CommentVO commentVO=new CommentVO(commentContent, postNo, memberVO);
			TipsCommentDAO.getInstance().writeComment(commentVO);
			System.out.println("댓글 작성 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
