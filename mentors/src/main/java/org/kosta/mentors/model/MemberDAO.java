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
	public boolean checkId(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from mentors_member where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()&&rs.getInt(1)>0) {
				result = true;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}
	public void registerMember(MemberVO vo) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con =null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into mentors_member(id,password,nick_name,email," );
			sql.append("address,interest,signup_date) values(?,?,?,?,?,?,sydate)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getInterest());
			pstmt.executeQuery();
		} finally {
			closeAll(pstmt, con);
		}
	}
}
