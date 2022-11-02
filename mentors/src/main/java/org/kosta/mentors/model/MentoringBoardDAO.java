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
	
}
