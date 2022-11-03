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
	public ArrayList<QuizVO> FindPostList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<QuizVO> list = new ArrayList<>();
		QuizVO vo = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select quiz_no, quiz_content,question1,question2,question3,question4,answer,category ");
			sql.append("from quiz_board");
			pstmt = con.prepareStatement(sql.toString());
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
	
}
