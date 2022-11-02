package org.kosta.mentors.test.khj;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.MemberVO;
import org.kosta.mentors.model.PostVO;

public class TestCaseWritePost {
	public static void main(String[] args) {
		try {
			String title="멘토스 테스트";
			String content="즐거운 코딩";
			String id="java";
			MemberVO memberVO=new MemberVO();
			memberVO.setId(id);
			PostVO postVO=new PostVO();
			postVO.setTitle(title);
			postVO.setContent(content);
			postVO.setMemberVO(memberVO);
			FreeBoardDAO.getInstance().writePost(postVO);
			System.out.println("자유게시판 글쓰기 완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
