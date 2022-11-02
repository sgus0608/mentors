package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;


public class FreeBoardDAO {
	private static FreeBoardDAO instance=new FreeBoardDAO();
	private DataSource dataSource;
	private FreeBoardDAO() {
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static FreeBoardDAO getInstance() {
		return instance;
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close(); // 컨넥션을 DBCP(DataSource)로 반납한다
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(pstmt, con); // 컨넥션을 DBCP(DataSource)로 반납한다
	}
	public ArrayList<PostVO> findPostList() throws SQLException {
		ArrayList<PostVO> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no,title,m.nick_name, ");
			sql.append("TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits ");
			sql.append("from free_board f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("order by f.post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				list.add(new PostVO(rs.getLong("post_no"),rs.getString("title"),rs.getLong("hits"),rs.getString("time_posted"),memberVO));
			}
		}finally {
			
		}
		return list;
	}
	public PostVO postDetailByNo(Long post_no) throws SQLException {
		PostVO postVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no,title,m.nick_name,m.id, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD HH24:MI:SS') as time_posted ");
			sql.append(", hits, content ");
			sql.append("from free_board f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("where f.post_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, post_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				memberVO.setId(rs.getString("id"));
				postVO=new PostVO(post_no, rs.getString("title"), rs.getString("content"), rs.getLong("hits"), rs.getString("time_posted"), memberVO);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return postVO;
	}
	public void writePost(PostVO postVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("insert into free_board(post_no, title, content, time_posted, id) ");
			sql.append("values(free_board_seq.nextval,?,?,sysdate,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setString(3, postVO.getMemberVO().getId());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void updatePost(PostVO postVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update free_board set title=?, content=? where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setLong(3, postVO.getPostNo());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void deletePost(long post_no) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="delete from free_board where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, post_no);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void countHitsPost(long post_no) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update free_board set hits=hits+1 where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, post_no);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
}


















