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

	public ArrayList<MentoringPostVO> findPostList(Pagination pagination) throws SQLException {
		ArrayList<MentoringPostVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.rnum, b.post_no, b.title, b.hits, b.time_posted, b.category, b.role, m.nick_name ");
			sql.append("FROM( ");
			sql.append("	SELECT row_number() over(ORDER BY post_no DESC) as rnum, ");
			sql.append("	post_no,title, hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, ");
			sql.append("	category, role, id ");
			sql.append("	FROM mentoring_board ");
			sql.append(") b INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY b.post_no DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
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

	public long getTotalPostCount() throws SQLException {
		long totalPostCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM mentoring_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public long getTotalPostCountByTitle(String searchText) throws SQLException {
		long totalPostCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM mentoring_board WHERE title LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public long getTotalPostCountByContent(String searchText) throws SQLException {
		long totalPostCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM mentoring_board WHERE content LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public long getTotalPostCountByNickName(String searchText) throws SQLException {
		long totalPostCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT count(*) FROM mentoring_board b ");
			sql.append("INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("WHERE m.nick_name=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}

	public void updateHits(long postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE mentoring_board SET hits=hits+1 WHERE post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public ArrayList<MentoringPostVO> searchPostListByTitle(String searchText, Pagination pagination) throws SQLException {
		ArrayList<MentoringPostVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.rnum, b.post_no, b.title, b.hits, b.time_posted, b.category, b.role, m.nick_name ");
			sql.append("FROM( ");
			sql.append("	SELECT row_number() over(ORDER BY post_no DESC) as rnum, ");
			sql.append("	post_no,title, hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, ");
			sql.append("	category, role, id ");
			sql.append("	FROM mentoring_board ");
			sql.append("	WHERE title LIKE ? ");
			sql.append(") b INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY b.post_no DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
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

	public ArrayList<MentoringPostVO> searchPostListByContent(String searchText, Pagination pagination) throws SQLException {
		ArrayList<MentoringPostVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.rnum, b.post_no, b.title, b.hits, b.time_posted, b.category, b.role, m.nick_name ");
			sql.append("FROM( ");
			sql.append("	SELECT row_number() over(ORDER BY post_no DESC) as rnum, ");
			sql.append("	post_no,title, hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, ");
			sql.append("	category, role, id ");
			sql.append("	FROM mentoring_board ");
			sql.append("	WHERE content LIKE ? ");
			sql.append(") b INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY b.post_no DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
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
	
	public ArrayList<MentoringPostVO> searchPostListByNickName(String searchText, Pagination pagination) throws SQLException {
		ArrayList<MentoringPostVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT rnum, post_no, title, hits, time_posted, category, role, nick_name ");
			sql.append("FROM( ");
			sql.append("	SELECT row_number() over(ORDER BY post_no DESC) as rnum, ");
			sql.append("	b.post_no, b.title, b.hits, TO_CHAR(time_posted, 'YYYY.MM.DD') as time_posted, ");
			sql.append("	b.category, b.role, m.nick_name ");
			sql.append("	FROM mentoring_board b ");
			sql.append("	INNER JOIN mentors_member m ON b.id=m.id ");
			sql.append("	WHERE m.nick_name=?" );
			sql.append(")WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY post_no DESC");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, searchText);
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
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

	public boolean checkLike(String id, long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from mentoring_like where post_no=? and id=?";
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
	
	public void insertLike(String id, long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="insert into mentoring_like values(?,?)";
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
			String sql="delete from mentoring_like where post_no=? and id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.setString(2, id);	
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	public long getTotalLikeCount(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalLikeCount=0L;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from mentoring_like where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				totalLikeCount-=1;
			} else {
				totalLikeCount+=1;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalLikeCount;
	}
	
}





