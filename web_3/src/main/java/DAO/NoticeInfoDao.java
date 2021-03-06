package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import etc.Database;
import vo.NoticeInfo;

public class NoticeInfoDao {
	public boolean insertNoticeInfo(NoticeInfo noticeInfo) {
		Database db = new Database();

		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;

		try {
			String sql = "INSERT INTO noticeinfo(`title`, `contents`) VALUES (?, ?)";

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
	
	public List<NoticeInfo> selectNoticeInfo(int pageNumber) {
		Database db = new Database();

		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<NoticeInfo> noticeInfoList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM noticeinfo ORDER BY id DESC LIMIT ?, 5 ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,	(pageNumber - 1) * 5);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				
				NoticeInfo nthNotice = new NoticeInfo(title, contents);
				
				noticeInfoList.add(nthNotice);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeResultSet(rs);
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
		return noticeInfoList;
	}
	
	public int getAmountOfNotice() {
		Database db = new Database();
		
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int amount = 0;
		
		String sql = "SELECT COUNT(*) AS amount FROM noticeInfo";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 공지사항의 갯수의 return 되는 결과가 무조건 있기 때문에
			rs.next();
			
			amount = rs.getInt("amount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeResultSet(rs);
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
		return amount;
	}
}
