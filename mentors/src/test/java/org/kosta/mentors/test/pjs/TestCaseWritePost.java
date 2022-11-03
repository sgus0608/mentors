package org.kosta.mentors.test.pjs;

import java.sql.SQLException;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.QnABoardDAO;
import org.kosta.mentors.model.QnAPostVO;

public class TestCaseWritePost {
	public static void main(String[] args) {
		try {
			String id="java";
			String category="취업";
			String title="질문 좀 할게요~";
			String content="멘토분 구하고있는데 어디서 구하나요?";
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
