package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import etc.Database;
import vo.MemberInfo;

public class UserInfo {
	public boolean insertUserInfo(MemberInfo newMemberInfo) {
		Database db = new Database();
		
		Connection conn = db.getConnection();
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO userinfo(id. pw, name) VALUES ('" + newMemberInfo.getId() + "', '" + newMemberInfo.getPw() + "', '" + newMemberInfo.getName() + "')";
		
			int count = stmt.executeUpdate(sql);	
			
			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;	
				
		
	}
}
