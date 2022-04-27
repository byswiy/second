package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import etc.Database;
import vo.NoticeInfo;

public class NoticeInfoDao {
	public boolean insertNoticeInfo(NoticeInfo noticeInfo) {
		Database db = new Database();
		
		Connection conn = db.getConnection();
		
		PreparedStatement pstmt = null;
		
		
		try {
			String sql = "INSERT INTO noticeinfo(`title`, `content`) VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, noticeInfo.getTitle());
			pstmt.setString(2, noticeInfo.getContents());
			
		
			int count = pstmt.executeUpdate();	
			
			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
	}
}
