package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseUpdatePost {
	public static void main(String[] args) {
		long no=1;
		String category="수정카테고리";
		String title="수정타이틀";
		String content="수정내용";
		QnAPostVO qnaPostVO=new QnAPostVO();
		qnaPostVO.setPostNo(no);
		qnaPostVO.setCategory(category);
		qnaPostVO.setTitle(title);
		qnaPostVO.setContent(content);
		System.out.println(no+"번 글 수정결과: "+qnaPostVO);
		try {
			QnABoardDAO.getInstance().updatePost(qnaPostVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
