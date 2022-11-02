package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class QnABoardDAO { // Singleton Design Pattern : 자원을 효율적으로 사용하기위해 서버상에서 한번만 생성해서 공유한다 
	private static QnABoardDAO instance=new QnABoardDAO();
	private DataSource dataSource;
	private QnABoardDAO() {
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static QnABoardDAO getInstance() {
		return instance;
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}
	public ArrayList<QnAPostVO> findPostList() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<QnAPostVO> list=new ArrayList<>();
		MemberVO memberVO=null;
		QnAPostVO qnaPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT post_no,title,m.nick_name,category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits ");
			sql.append("FROM qna_board q ");
			sql.append("INNER JOIN mentors_member m ON m.id=q.id ");
			sql.append("ORDER BY post_no DESC ");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				memberVO=new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				qnaPostVO=new QnAPostVO(rs.getLong("post_no"), rs.getString("title"), rs.getLong("hits"), rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(qnaPostVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}


















