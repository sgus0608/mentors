package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class TipsBoardDAO {
	private static TipsBoardDAO instance = new TipsBoardDAO();
	private DataSource dataSource;

	private TipsBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static TipsBoardDAO getInstance() {
		return instance;
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	public ArrayList<TipsPostVO> findPostList(Pagination pagination) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TipsPostVO> list = new ArrayList<>();
		MemberVO memberVO = null;
		TipsPostVO tipsPostVO = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select rnum , post_no, title, category, time_posted, hits, m.nick_name ");
			sql.append("from( ");
			sql.append("SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum, post_no, title, category, ");
			sql.append("TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id ");
			sql.append("FROM tips_board ");
			sql.append(") t ");
			sql.append("inner join mentors_member m on t.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by post_no desc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO = new TipsPostVO(rs.getLong("post_No"), rs.getString("title"), rs.getLong("hits"),
						rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(tipsPostVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public TipsPostVO postDetailByNo(long postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TipsPostVO tipsPostVO = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select post_no,title,m.nick_name,category,content,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits, m.id ");
			sql.append("from tips_board t ");
			sql.append("inner join mentors_member m on m.id=t.id ");
			sql.append("where post_no=? ");
			sql.append("order by post_no desc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, postNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberVO memberVO = new MemberVO(rs.getString("id"), null, rs.getString("nick_name"), null, null, null,
						null, null);
				tipsPostVO = new TipsPostVO(postNo, rs.getString("title"), rs.getString("content"), rs.getLong("hits"),
						rs.getString("time_posted"), rs.getString("category"), memberVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return tipsPostVO;
	}

	public void writePost(TipsPostVO tipsPostVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tips_board(post_no,title,content,time_posted,category,id)");
			sql.append("values(tips_board_seq.nextval,?,?,sysdate,?,?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, tipsPostVO.getTitle());
			pstmt.setString(2, tipsPostVO.getContent());
			pstmt.setString(3, tipsPostVO.getCategory());
			pstmt.setString(4, tipsPostVO.getMemberVO().getId());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public void deletePost(long no) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="delete from tips_board where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
		
	}

	public void updatePost(TipsPostVO tipsPostVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update tips_board set category=?, title=?, content=? where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tipsPostVO.getCategory());
			pstmt.setString(2, tipsPostVO.getTitle());
			pstmt.setString(3, tipsPostVO.getContent());
			pstmt.setLong(4, tipsPostVO.getPostNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}		
	}

	public long getTotalPostCount() throws SQLException {
		long totalPostCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from tips_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount=rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}
	
	public long getTotalPostCountByTitle(String searchText) throws SQLException {
		long totalPostCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from tips_board where title like ? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchText+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount=rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalPostCount;
	}

	public void updateHits(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update tips_board set hits=hits+1 where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public ArrayList<TipsPostVO> searchPostListByTitle(String searchText, Pagination pagination) throws SQLException {
		ArrayList<TipsPostVO> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		TipsPostVO tipsPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select rnum , post_no, title, category, time_posted, hits, m.nick_name ");
			sql.append("from( ");
			sql.append("SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, post_no, title, category, ");
			sql.append("TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id ");
			sql.append("FROM tips_board ");
			sql.append("where title like ? ");
			sql.append(") t ");
			sql.append("inner join mentors_member m on t.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3,pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				memberVO = new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO = new TipsPostVO(rs.getLong("post_No"), rs.getString("title"), rs.getLong("hits"),
						rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(tipsPostVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		
		
		return list;
	}

	public long getTotalPostCountByContent(String searchText) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalCountPost=0;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from tips_board where content like ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCountPost=rs.getLong(1);
			}			
		} finally {
			closeAll(rs, pstmt, con);
		}		
		return totalCountPost;
	}		

	public ArrayList<TipsPostVO> searchPostListByContent(String searchText, Pagination pagination) throws SQLException {
		ArrayList<TipsPostVO> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		TipsPostVO tipsPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select rnum , post_no, title, category, time_posted, hits, m.nick_name ");
			sql.append("from( ");
			sql.append("SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, post_no, title, category, ");
			sql.append("TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits, id ");
			sql.append("FROM tips_board ");
			sql.append("where content like ? ");
			sql.append(") t ");
			sql.append("inner join mentors_member m on t.id=m.id ");
			sql.append("where rnum between ? and ? ");
			sql.append("order by post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+searchText+"%");
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3,pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				memberVO = new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO = new TipsPostVO(rs.getLong("post_No"), rs.getString("title"), rs.getLong("hits"),
						rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(tipsPostVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}				
		return list;
	}

	public long getTotalPostCountByNickName(String searchText) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		long totalCountPost=0;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from tips_board t ");
			sql.append("inner join mentors_member m on t.id=m.id ");
			sql.append("where m.nick_name =? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, searchText);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCountPost=rs.getLong(1);
			}			
		} finally {
			closeAll(rs, pstmt, con);
		}		
		return totalCountPost;
	}

	public ArrayList<TipsPostVO> searchPostListByNickName(String searhText, Pagination pagination) throws SQLException {
		ArrayList<TipsPostVO> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		TipsPostVO tipsPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select rnum , post_no, title, category, time_posted, hits, nick_name ");
			sql.append("from( ");
			sql.append("SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC)AS rnum, ");
			sql.append("post_no, title, category, m.nick_name, TO_CHAR(time_posted,'YYYY.MM.DD') as time_posted,hits ");
			sql.append("FROM tips_board t ");
			sql.append("inner join mentors_member m on m.id=t.id ");
			sql.append("where m.nick_name =? ");
			sql.append(") where rnum between ? and ? ");
			sql.append("order by post_no desc");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, searhText);
			pstmt.setLong(2, pagination.getStartRowNumber());
			pstmt.setLong(3, pagination.getEndRowNumber());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				memberVO = new MemberVO(null, null, rs.getString("nick_name"), null, null, null, null, null);
				tipsPostVO = new TipsPostVO(rs.getLong("post_No"), rs.getString("title"), rs.getLong("hits"),
						rs.getString("time_posted"), rs.getString("category"), memberVO);
				list.add(tipsPostVO);
			}			
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}

	public boolean checkLike(long postNo, String id) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result=false;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from tips_like where post_no=? and id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.setString(2, id);
			rs=pstmt.executeQuery();
			if(rs.next()&&rs.getLong(1)>0) {
				result=true;
			}
		} finally {
			closeAll(rs, pstmt, con);
		}		
		return result;
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
