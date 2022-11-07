package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MentoringCommentDAO {
	private static MentoringCommentDAO instance = new MentoringCommentDAO();
	private DataSource dataSource;
	
	private MentoringCommentDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static MentoringCommentDAO getInstance() {
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

	public ArrayList<CommentVO> findCommentList(long postNo) throws SQLException {
		ArrayList<CommentVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT c.comment_no, c.comment_content, TO_CHAR(comment_time_posted, 'YYYY.MM.DD HH24:MI:SS') as comment_time_posted, m.id, m.nick_name ");
			sql.append("FROM mentoring_comment c ");
			sql.append("INNER JOIN mentors_member m ON c.id=m.id ");
			sql.append("WHERE c.post_no=? ");
			sql.append("ORDER BY c.comment_no ASC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, postNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setNickName(rs.getString("nick_name"));
				CommentVO commentVO = new CommentVO();
				commentVO.setCommentNo(rs.getLong("comment_no"));
				commentVO.setCommentContent(rs.getString("comment_content"));
				commentVO.setCommentTimePosted(rs.getString("comment_time_posted"));
				commentVO.setMemberVO(memberVO);
				list.add(commentVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public void writeComment(CommentVO commentVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO mentoring_comment VALUES(mentoring_comment_seq.nextval, ?, sysdate, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commentVO.getCommentContent());
			pstmt.setLong(2, commentVO.getPostNo());
			pstmt.setString(3, commentVO.getMemberVO().getId());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void deleteComment(long commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM mentoring_comment WHERE comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, commentNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void updateComment(CommentVO commentVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE mentoring_comment SET comment_content=? WHERE comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commentVO.getCommentContent());
			pstmt.setLong(2, commentVO.getCommentNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
}
