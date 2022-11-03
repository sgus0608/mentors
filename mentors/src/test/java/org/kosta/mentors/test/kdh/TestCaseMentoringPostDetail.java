package org.kosta.mentors.test.kdh;

import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.kosta.mentors.model.MentoringBoardDAO;
import org.kosta.mentors.model.MentoringPostVO;

public class TestCaseMentoringPostDetail {
	public static void main(String[] args) {
		try {
			long postNo = 1;
			MentoringPostVO postVO = MentoringBoardDAO.getInstance().postDetailByNo(postNo);
			System.out.println(postVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
