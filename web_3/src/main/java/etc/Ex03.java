package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex03 {

	public static void main(String[] args) {
		// JDBC Driver를 불러오는 코드
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 접속하기 위한 정보들을 입력
			// < 접속 정보 구성 요소 >
			// 프로토콜 : jdbc:mariadb://
			// DBMS 서버 도메인 또는 주소 : localhost, 127.0.0.1, 서버의 도메인 또는 IP 주소
			// 포트번호 : mysql이나 mariadb는 일반적으로 3306번 포트를 사용
			// 접속할 DB명
			// 접속할 사용자 계정과 비밀번호 : GET Parameter 형식으로 접속 정보의 마지막에 ?가 붙고 name=value로 넣어준다
			
			// ex. http://localhost:80/web_3
			//     jdbc:mairadb://localhost:3306/employees?user=root&password=1234
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=1234");
			
			// 우리의 쿼리를 실행시켜주고 쿼리의 실행 결과를 가져오는 객체 
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM employees LIMIT 5";
			
			// stmt를 통해서 쿼리 실행 및 결과 받아오기
			// 1. SELECT 쿼리를 실행 -> executeQuery 메서드 호룿
			// 2. INSERT, UPDATE, DELETE 쿼리 실행 -> executeUpdate 메서드 호출
			ResultSet rs = stmt.executeQuery(sql);
			
			// 위 ResultSet 안에는 SELECT의 결과들이 들어있는 것 / 결과들은 꼭 2개 이상이 아닐 수도 있다
			
			// count를 사용해서 안에 데이터가 몇개가 들었는지 확인할 수 있었음
			int count = 1;
			
			while(rs.next()) {
				System.out.println("<< " + count + " 번째 데이터 출력 >>" );
				
				int empNo = rs.getInt("emp_no");
				String birthDate = rs.getString("birth_date");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				char gender = rs.getString("gender").charAt(0);
				String hireDate = rs.getString("hire_date");
				
				System.out.println("emp_no => " + empNo);
				System.out.println("birthDate => " + birthDate);
				System.out.println("firstName => " + firstName);
				System.out.println("lastName => " + lastName);
				System.out.println("gender => " + gender);
				System.out.println("hireDate => " + hireDate);
				
				count++;
			}
			
			System.out.println("count -> " + count);
			
			// DB와 관련된 자원을 해제할 때는 반드시 finally안에서 해제 시켜줘야한다
//			stmt.close();
//			conn.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// if문으로 null이 아닌 상태를 만들어 준 이유
				// 초기화 상태를 만들기 위해 null 상태를 저장해줬는데 null 상태가 catch 될 경우
				// 다시 NullPointException이 발생할 수 있기 때문에 null 상태가 아니라면 close를 사용하라는 뜻으로 사용
				if(stmt != null) {
					stmt.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
