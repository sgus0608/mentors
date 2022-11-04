package org.kosta.mentors.test.kdh;

import java.sql.SQLException;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class TestCaseMentoringUpdatePost {
	public static void main(String[] args) {
		try {
			long postNo = 2;
			MentoringPostVO postVO = new MentoringPostVO();
			postVO.setPostNo(postNo);
			postVO.setCategory("자바");
			postVO.setRole("멘토");
			postVO.setTitle("타이틀");
			postVO.setContent("컨텐트");
			MentoringBoardDAO.getInstance().updatePost(postVO);
			System.out.println("수정완료: "+postVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
