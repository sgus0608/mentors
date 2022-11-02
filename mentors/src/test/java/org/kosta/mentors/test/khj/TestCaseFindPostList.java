package org.kosta.mentors.test.khj;

import java.sql.SQLException;
import java.util.ArrayList;

import org.kosta.mentors.model.FreeBoardDAO;
import org.kosta.mentors.model.PostVO;

public class TestCaseFindPostList {
	public static void main(String[] args) {
		FreeBoardDAO freeBoard=FreeBoardDAO.getInstance();
		ArrayList<PostVO> list;
		try {
			list = freeBoard.findPostList();
			for(PostVO post:list) {
				System.out.println(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
