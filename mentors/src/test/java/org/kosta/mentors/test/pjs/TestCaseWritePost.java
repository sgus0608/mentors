package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseWritePost {
	public static void main(String[] args) {
		try {
			String id="java";
			String category="프로그래밍";
			String title="멋진 개발자가 꿈입니다";
			String content="이곳이 그유명한 멘토스 사이트인가요?";
			MemberVO memberVO=new MemberVO();
			memberVO.setId(id);
			QnAPostVO qnaPostVO=new QnAPostVO();
			qnaPostVO.setCategory(category);
			qnaPostVO.setTitle(title);
			qnaPostVO.setContent(content);
			qnaPostVO.setMemberVO(memberVO);
			QnABoardDAO.getInstance().writePost(qnaPostVO);
			System.out.println(qnaPostVO+"글쓰기완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
