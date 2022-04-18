package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import etc.Database;
import vo.MemberInfo;

public class UserInfo {
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
}
