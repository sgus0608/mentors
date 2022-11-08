package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class QuizDAO {
	private static QuizDAO instance = new QuizDAO();
	private DataSource dataSource;
	private QuizDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static QuizDAO getInstance() {
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
	public ArrayList<QuizVO> FindPostList(Pagination pagination) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<QuizVO> list = new ArrayList<>();
		QuizVO vo = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select rnum, quiz_no, quiz_content,question1,question2, ");
			sql.append("question3, question4, answer, category from ");
			sql.append("(select row_number() over(order by quiz_no asc) ");
			sql.append("as rnum, quiz_no, quiz_content, question1, question2,question3, ");
			sql.append("question4, answer, category from Quiz_BOARD) ");
			sql.append("where rnum between ? and ? order by quiz_no asc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, pagination.getStartRowNumber());
			pstmt.setLong(2, pagination.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new QuizVO(rs.getLong("quiz_no"),rs.getString("quiz_content"), rs.getString("question1"), rs.getString("question2"), rs.getString("question3"), rs.getString("question4"), rs.getString("answer"), rs.getString("category"));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public String checkResult(long postNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = null;
		try {
			con = dataSource.getConnection();
			String sql = "select answer from quiz_board where quiz_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("answer");
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return result;
	}
	public long getTotalPostCount() throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long totalPostCount =0;
		Connection con  = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from quiz_board";
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				totalPostCount = rs.getLong(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return totalPostCount;
	
	}
	public void writePost(QuizVO quizVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "insert into QUIZ_BOARD values(quiz_board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, quizVO.getContent());
			pstmt.setString(2, quizVO.getQuestion1());
			pstmt.setString(3, quizVO.getQuestion2());
			pstmt.setString(4, quizVO.getQuestion3());
			pstmt.setString(5, quizVO.getQuestion4());
			pstmt.setString(6, quizVO.getAnswer());
			pstmt.setString(7, quizVO.getCategory());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	
	public QuizVO postDetailByNo(long postNo) throws SQLException {
		QuizVO quizVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select quiz_content,question1,question2,question3,question4,answer,category ");
			sql.append("from quiz_board where quiz_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setLong(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				quizVO = new QuizVO();
				quizVO.setNo(postNo);
				quizVO.setCategory(rs.getString("category"));
				quizVO.setAnswer(rs.getString("answer"));
				quizVO.setContent(rs.getString("quiz_content"));
				quizVO.setQuestion1(rs.getString("question1"));
				quizVO.setQuestion2(rs.getString("question2"));
				quizVO.setQuestion3(rs.getString("question3"));
				quizVO.setQuestion4(rs.getString("question4"));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return quizVO;
	}
	public void updatePost(QuizVO quizVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE quiz_board SET quiz_content=?, question1=?, question2=?, question3=?, question4=?, ");
			sql.append("answer=?, category=? WHERE quiz_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, quizVO.getContent());
			pstmt.setString(2, quizVO.getQuestion1());
			pstmt.setString(3, quizVO.getQuestion2());
			pstmt.setString(4, quizVO.getQuestion3());
			pstmt.setString(5, quizVO.getQuestion4());
			pstmt.setString(6, quizVO.getAnswer());
			pstmt.setString(7, quizVO.getCategory());
			pstmt.setLong(8, quizVO.getNo());
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
			String sql = "DELETE FROM quiz_board WHERE quiz_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, postNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public long quizLikeCount(long quizNo) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		long count = 0;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from  quiz_likeBoard where quiz_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, quizNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getLong(1);
			}
			
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	
}
