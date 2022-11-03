package org.kosta.mentors.test.kdh;

import java.sql.SQLException;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class TestCaseMentoringWritePost {

	public static void main(String[] args) {
		try {
			String category = "알고리즘";
			String role = "멘토";
			String title = "알고리즘 공부하실 멘티분들 모셔요";
			String content = "백준 사이트를 이용해 알고리즘 알려드리려고 합니다";
			MemberVO memberVO = new MemberVO("java", null, null, null, null, null, null, null);
			MentoringPostVO postVO = new MentoringPostVO(title, content, category, role, memberVO);
			MentoringBoardDAO.getInstance().writePost(postVO);
			System.out.println("글 등록완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
