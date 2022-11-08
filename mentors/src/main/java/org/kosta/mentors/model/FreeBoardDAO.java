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
	public ArrayList<PostVO> findPostList(Pagination pagination) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no, title, m.nick_name,time_posted, hits ");
			sql.append("from( ");
			sql.append("select row_number() over(order by post_no desc) as rnum, ");
			sql.append("post_no, title, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted, ");
			sql.append("id , hits from free_board ");
			sql.append(") f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by f.post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				list.add(new PostVO(rs.getLong("post_no"),rs.getString("title"),rs.getLong("hits"),rs.getString("time_posted"),memberVO));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public PostVO postDetailByNo(Long postNo) throws SQLException {
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
			pstmt.setLong(1, postNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				memberVO.setId(rs.getString("id"));
				postVO=new PostVO(postNo, rs.getString("title"), rs.getString("content"), rs.getLong("hits"), rs.getString("time_posted"), memberVO);
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
	public void deletePost(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="delete from free_board where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public void updateHits(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update free_board set hits=hits+1 where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	public long getTotalPostCount() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalPostCount=0;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from free_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalPostCount=rs.getLong(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public long getTotalPostCountByTitle(String searchText) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalPostCount=0;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from free_board where title like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
				totalPostCount=rs.getLong(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public ArrayList<PostVO> searchPostListByTitle(String searchText, Pagination pagination) throws SQLException {	
		ArrayList<PostVO> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no, title, m.nick_name,time_posted, hits ");
			sql.append("from( ");
			sql.append("select row_number() over(order by post_no desc) as rnum, ");
			sql.append("post_no, title, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted, ");
			sql.append("id , hits from free_board ");
			sql.append("where title like ? ");
			sql.append(") f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by f.post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				list.add(new PostVO(rs.getLong("post_no"),rs.getString("title"),rs.getLong("hits"),rs.getString("time_posted"),memberVO));
			}
		}finally {
			closeAll(rs, pstmt, con);;
		}
		return list;
	}
	public long getTotalPostCountByContent(String searchText) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalPostCount=0;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from free_board where content like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			rs=pstmt.executeQuery();
			if(rs.next())
				totalPostCount=rs.getLong(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	public ArrayList<PostVO> searchPostListByContent(String searchText, Pagination pagination) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no, title, m.nick_name,time_posted, hits ");
			sql.append("from( ");
			sql.append("select row_number() over(order by post_no desc) as rnum, ");
			sql.append("post_no, title, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted, ");
			sql.append("id , hits from free_board ");
			sql.append("where content like ? ");
			sql.append(") f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by f.post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				list.add(new PostVO(rs.getLong("post_no"),rs.getString("title"),rs.getLong("hits"),rs.getString("time_posted"),memberVO));
			}
		}finally {
			closeAll(rs, pstmt, con);;
		}
		return list;
	}
	public long getTotalPostCountByNickName(String searchText) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalPostCount=0;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from free_board f ");
			sql.append("inner join mentors_member m on f.id=m.id ");
			sql.append("where nick_name=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, searchText);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalPostCount=rs.getLong(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	public ArrayList<PostVO> searchPostListByNickName(String searchText, Pagination pagination) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select post_no, title, nick_name, time_posted, hits ");
			sql.append("from( ");
			sql.append("select row_number() over(order by post_no desc) as rnum, ");
			sql.append("post_no, title, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted, ");
			sql.append("hits, mm.id, mm.nick_name ");
			sql.append("from free_board fb ");
			sql.append("inner join mentors_member mm on fb.id=mm.id ");
			sql.append("where mm.nick_name=? ");
			sql.append(") where rnum between ? and ? ");
			sql.append("order by post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, searchText);
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO=new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				list.add(new PostVO(rs.getLong("post_no"),rs.getString("title"),rs.getLong("hits"),rs.getString("time_posted"),memberVO));
			}
		}finally {
			closeAll(rs, pstmt, con);;
		}
		return list;
	}
	
	public void insertLike(String id, long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="insert into free_like values(?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	public void deleteLike(String id, long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="delete from free_like where post_no=? and id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.setString(2, id);	
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	public boolean checkLike(String id, long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from free_like where post_no=? and id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.setString(2, id);
			rs=pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				result=true;
			} 
		}finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}
	
	public long getTotalLikeCount(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long likeTotal=0L;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from free_like where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				likeTotal-=1;
			} else {
				likeTotal+=1;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return likeTotal;
	}
}


















