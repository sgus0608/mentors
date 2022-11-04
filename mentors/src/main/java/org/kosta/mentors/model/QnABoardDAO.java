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
	public ArrayList<QnAPostVO> findPostList(Pagination pagination) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<QnAPostVO> list=new ArrayList<>();
		MemberVO memberVO=null;
		QnAPostVO qnaPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT rnum,post_no,title,category,time_posted,hits,m.nick_name ");
			sql.append("FROM ( ");
			sql.append("SELECT ROW_NUMBER() OVER(ORDER BY post_no DESC) AS rnum,post_no,title, ");
			sql.append("category,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,id ");
			sql.append("FROM qna_board) q ");
			sql.append("INNER JOIN mentors_member m ON q.id=m.id ");
			sql.append("WHERE rnum BETWEEN ? AND ? ");
			sql.append("ORDER BY post_no DESC");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
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
	public QnAPostVO postDetailByNo(long postNo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO memberVO=null;
		QnAPostVO qnaPostVO=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT post_no,title,m.nick_name,category,content,to_char(time_posted,'YYYY.MM.DD') as time_posted,hits,m.id ");
			sql.append("FROM qna_board q ");
			sql.append("INNER JOIN mentors_member m ON m.id=q.id ");
			sql.append("WHERE post_no=? ");
			sql.append("ORDER BY post_no DESC ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setLong(1, postNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO=new MemberVO(rs.getString("id"), null, rs.getString("nick_name"), null, null, null, null, null);
				qnaPostVO=new QnAPostVO(postNo, rs.getString("title"), rs.getString("content"), rs.getLong("hits"), rs.getString("time_posted"), rs.getString("category"), memberVO);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return qnaPostVO;
	}
	public void writePost(QnAPostVO qnaPostVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("insert into qna_board(post_no,title,content,time_posted,category,id) ");
			sql.append("values (qna_board_seq.nextval,?,?,sysdate,?,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, qnaPostVO.getTitle());
			pstmt.setString(2, qnaPostVO.getContent());
			pstmt.setString(3, qnaPostVO.getCategory());
			pstmt.setString(4, qnaPostVO.getMemberVO().getId());
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
			String sql="DELETE from qna_board where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public void updatePost(QnAPostVO qnaPostVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="update qna_board set category=?,title=?,content=? where post_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qnaPostVO.getCategory());
			pstmt.setString(2, qnaPostVO.getTitle());
			pstmt.setString(3, qnaPostVO.getContent());
			pstmt.setLong(4, qnaPostVO.getPostNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public long getTotalPostCount() throws SQLException {
		long totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="SELECT COUNT(*) FROM qna_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCount=rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}
}



















