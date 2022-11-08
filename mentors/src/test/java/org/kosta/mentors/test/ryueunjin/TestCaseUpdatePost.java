package org.kosta.mentors.test.ryueunjin;

import java.sql.SQLException;

import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseUpdatePost {
	public static void main(String[] args) {
		long no=3;
	      String category="수정카테고리";
	      String title="수정타이틀";
	      String content="수정내용";
	      TipsPostVO tipsPostVO=new TipsPostVO();
	      tipsPostVO.setPostNo(no);
	      tipsPostVO.setCategory(category);
	      tipsPostVO.setTitle(title);
	      tipsPostVO.setContent(content);	      
	      try {
	         TipsBoardDAO.getInstance().updatePost(tipsPostVO);
	         System.out.println(no+"번 글 수정결과: "+tipsPostVO);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
}
