package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MentoringBoardDAO {
	private static MentoringBoardDAO instance = new MentoringBoardDAO();
	private DataSource dataSource;

	private MentoringBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static MentoringBoardDAO getInstance() {
		return instance;
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	public ArrayList<MentoringPostVO> findPostList() throws SQLException {
		ArrayList<MentoringPostVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.post_no, b.title, b.hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, b.category, b.role, m.nick_name ");
			sql.append("FROM mentoring_board b ");
			sql.append("INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("ORDER BY b.post_no DESC");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setNickName(rs.getString("nick_name"));
				MentoringPostVO postVO = new MentoringPostVO();
				postVO.setPostNo(rs.getLong("post_no"));
				postVO.setTitle(rs.getString("title"));
				postVO.setHits(rs.getLong("hits"));
				postVO.setTimePosted(rs.getString("time_posted"));
				postVO.setCategory(rs.getString("category"));
				postVO.setRole(rs.getString("role"));
				postVO.setMemberVO(memberVO);
				list.add(postVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public MentoringPostVO postDetailByNo(long postNo) throws SQLException {
		MentoringPostVO postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.post_no, b.title, b.content, b.hits, TO_CHAR(time_posted, 'YYYY.MM.DD HH24:MI:SS') as time_posted, b.category, b.role, m.id, m.nick_name ");
			sql.append("FROM mentoring_board b ");
			sql.append("INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("WHERE b.post_no=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setNickName(rs.getString("nick_name"));
				postVO = new MentoringPostVO();
				postVO.setPostNo(rs.getLong("post_no"));
				postVO.setTitle(rs.getString("title"));
				postVO.setContent(rs.getString("content"));
				postVO.setHits(rs.getLong("hits"));
				postVO.setTimePosted(rs.getString("time_posted"));
				postVO.setCategory(rs.getString("category"));
				postVO.setRole(rs.getString("role"));
				postVO.setMemberVO(memberVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return postVO;
	}

	public void writePost(MentoringPostVO postVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO mentoring_board(post_no, title, content, time_posted, category, role, id) ");
			sql.append("VALUES(mentoring_board_seq.nextval, ?, ?, sysdate, ?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getContent());
			pstmt.setString(3, postVO.getCategory());
			pstmt.setString(4, postVO.getRole());
			pstmt.setString(5, postVO.getMemberVO().getId());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void updatePost(MentoringPostVO postVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE mentoring_board SET category=?, role=?, title=?, content=? ");
			sql.append("WHERE post_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postVO.getCategory());
			pstmt.setString(2, postVO.getRole());
			pstmt.setString(3, postVO.getTitle());
			pstmt.setString(4, postVO.getContent());
			pstmt.setLong(5, postVO.getPostNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void deletePost(long postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM mentoring_board WHERE post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	
}
