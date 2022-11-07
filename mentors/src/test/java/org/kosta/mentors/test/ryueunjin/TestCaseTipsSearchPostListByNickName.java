package org.kosta.mentors.test.ryueunjin;

import java.util.ArrayList;

import org.kosta.mentors.model.Pagination;
import org.kosta.mentors.model.TipsBoardDAO;
import org.kosta.mentors.model.TipsPostVO;

public class TestCaseTipsSearchPostListByNickName {
	public static void main(String[] args) {
		try {
			String searhText="장기하";
			long totalCountPost=TipsBoardDAO.getInstance().getTotalPostCountByNickName(searhText);
			System.out.println("총 게시물 수 :"+totalCountPost+"개");
			Pagination pagination=new Pagination(totalCountPost);
			ArrayList<TipsPostVO> list=TipsBoardDAO.getInstance().searchPostListByNickName(searhText,pagination);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
