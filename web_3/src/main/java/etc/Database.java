package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberInfo;
import vo.NoticeInfo;

public class Database {
	public static List<MemberInfo> memberInfoTable = new ArrayList<>();
	
	public static List<NoticeInfo> noticeInfoTable = new ArrayList<>();
	
	// DB 커넥션을 생성해서 반환하는 메서드
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://192.168.10.4:3306/shopdb/user=root&password=1234");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}


