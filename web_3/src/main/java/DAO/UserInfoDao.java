package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import etc.Database;
import vo.MemberInfo;

public class UserInfoDao {
	
	public boolean insertUserInfo(MemberInfo newMemberInfo) {
		Database db = new Database();
		
		Connection conn = db.getConnection();
//		Statement stmt = null;
		
		PreparedStatement pstmt = null;
		
		
		try {
//			stmt = conn.createStatement();
			
//			String sql = "INSERT INTO userinfo(id. pw, name) VALUES ('" + newMemberInfo.getId() + "', '" + newMemberInfo.getPw() + "', '" + newMemberInfo.getName() + "')";
			String sql = "INSERT INTO userinfo(`id`, `pw`, `name`) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMemberInfo.getId());
			pstmt.setString(2, newMemberInfo.getPw());
			pstmt.setString(3, newMemberInfo.getName());
			
			System.out.println(pstmt);
		
			int count = pstmt.executeUpdate();	
			
			return count == 1;
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
			return false;
		} finally {
//			db.closeStmt(pstmt);
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
	}
	
	public static MemberInfo selectUserInfo(MemberInfo memberInfo) {
		Database db = new Database();
		
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM userinfo WHERE id= ? AND pw = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getId());
			pstmt.setString(2, memberInfo.getPw()); 
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				// 조회한 회원 정보를 매개변수로 받은 객체에 담는다
				String name = rs.getString("name");
				memberInfo.setName(name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResultSet(rs);
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
		return memberInfo;
	}
}
