package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;

import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseWritePost {
	public static void main(String[] args) {
		try {
			String id="java";
			String category="채용";
			String title="취업 시켜주세요";
			String content="열심히 할게요";
			MemberVO memberVO=new MemberVO();
			memberVO.setId(id);
			TipsPostVO tipsPostVO=new TipsPostVO();
			tipsPostVO.setCategory(category);
			tipsPostVO.setContent(content);
			tipsPostVO.setTitle(title);
			tipsPostVO.setMemberVO(memberVO);
			
			TipsBoardDAO.getInstance().writePost(tipsPostVO);
			System.out.println(tipsPostVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
