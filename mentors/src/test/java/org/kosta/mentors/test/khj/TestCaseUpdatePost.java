package org.kosta.mentors.test.khj;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class TestCaseUpdatePost {
	public static void main(String[] args) {
		PostVO postVO=new PostVO();
		postVO.setPostNo(1L);
		postVO.setTitle("제목바꾸기");
		postVO.setContent("내용바꾸기");
		try {
			FreeBoardDAO.getInstance().updatePost(postVO);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
