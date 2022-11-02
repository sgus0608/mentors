package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

// 상세 게시글 보기 테스트
public class TestCasePostDetailByNo {
	public static void main(String[] args) {
		long postNo=1;
		QnABoardDAO qnaBoardDAO=QnABoardDAO.getInstance();
		QnAPostVO qnaPostVO;
		try {
			qnaPostVO = qnaBoardDAO.postDetailByNo(postNo);
			System.out.println(qnaPostVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
