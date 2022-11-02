package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;



public class TipsBoardDAO {
	private static TipsBoardDAO instance=new TipsBoardDAO();
	private DataSource dataSource;
	
	private TipsBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static TipsBoardDAO getInstance() {
		return instance;
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con);
	}

	public ArrayList<TipsPostVO> findPostList() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<TipsPostVO> list=new ArrayList<>();
		MemberVO memberVO=null;
		TipsPostVO tipsPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no,title,m.nick_name,category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits ");
			sql.append("from tips_board t ");
			sql.append("inner join mentors_member m on m.id=t.id ");
			sql.append("order by post_no DESC ");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				memberVO=new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO=new TipsPostVO(rs.getLong("post_No"), rs.getString("title"), rs.getLong("hits"), rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(tipsPostVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public TipsPostVO findPostByNo(long postNo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		TipsPostVO tipsPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no,title,m.nick_name,category,content,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits, m.id ");
			sql.append("from tips_board t ");
			sql.append("inner join mentors_member m on m.id=t.id ");
			sql.append("where post_no=? ");
			sql.append("order by post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1,postNo);
			rs=pstmt.executeQuery ();
			if(rs.next()) {
				MemberVO memberVO=new MemberVO(rs.getString("id"), null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO=new TipsPostVO(postNo, rs.getString("title"), rs.getString("content"), rs.getLong("hits"), rs.getString("time_posted"), rs.getString("category"), memberVO);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return tipsPostVO;
	}


	
}
