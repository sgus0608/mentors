package org.kosta.mentors.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance= new MemberDAO();
	private DataSource dataSource;
	
	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();	
	}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException {
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); // 컨넥션을 DBCP(DataSource)로 반납한다 
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException {
		if(rs!=null)
			rs.close();
		closeAll(pstmt, con); // 컨넥션을 DBCP(DataSource)로 반납한다 
	}
	
	// 로그인 메서드
	public MemberVO login(String id, String password) throws SQLException {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT nick_name, email, address, interest, TO_CHAR(signup_date, 'YYYY-MM-DD') as signup_date, member_type ");
			sql.append("FROM mentors_member WHERE id=? and password=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO = new MemberVO(id, password, rs.getString("nick_name"), rs.getString("email"), rs.getString("address"), rs.getString("interest"), rs.getString("signup_date"), rs.getString("member_type"));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return memberVO;
	}
	
}
