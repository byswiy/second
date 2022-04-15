package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import vo.MemberInfo;

public class Ex04 {
	public static int inputMenuNumber() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 뫄뫄뫄 프로그램 >>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 프로그램 종료");
		System.out.print("메뉴 선택 -> ");
		
		int menu = scanf.nextInt();
		
		return menu;
	}
	
	// 회원 가입 시 회원 정보를 입력 받는 메서드
	public static MemberInfo joinInput() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 회원가입 >>");
		
		System.out.print("아이디 -> ");
		String id = scanf.next();
		
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
		
		System.out.print("이름 -> ");
		String name = scanf.next();
		
		// MemberInfo 클래스를 사용해서 회원 정보를 받아옴
		MemberInfo memberInfo = new MemberInfo(id, pw, name);
		
		return memberInfo;
	}	
	
	public static boolean executeJoinQuery(MemberInfo memberInfo) throws SQLIntegrityConstraintViolationException{
		// DB를 사용할 때 제일 중요한 점 : 우리가 원하는 동작을 다 했다면 반드시 close()로 DB와 프로그램의 연결을 끊어줘야한다
		Connection conn = null;
		Statement stmt = null;
		
		// 회원 가입의 성공 여부
		boolean isJoin = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO userinfo(id, pw, name) VALUES('" + memberInfo.getId() + "', '" + memberInfo.getPw() + "', '" + memberInfo.getName() + "')";
			
			int count = stmt.executeUpdate(sql);
			
			// 회원 가입 성공 시 isJoin을 true
			if(count == 1) {
				isJoin = true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} // end try
			} // end if
		} // end try
		return isJoin;
	} // end method
	
	// 메서드는 한 번에 하나의 동작(역할)만 하는게 제일 좋음
	public static void join() {
		// 1. 회원 정보 입력 받는 부분
		MemberInfo memberInfo = joinInput();
		
		try {
			// 2. 회원 가입 쿼리 실행 부분
			boolean isJoin = executeJoinQuery(memberInfo);
				
			// 3. 회원 가입 결과를 출력하는 부분
			if(isJoin) {
				System.out.println("회원 가입 완료");
			} else {
				System.out.println("회원 가입 실패");
			}
		} catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 사용중인 아이디 입니다.");
		}
	}

	public static void main(String[] args) {
		
		
		boolean isRunning = true;
		
		while(isRunning) {
			
			int menu = inputMenuNumber();
			
			switch(menu) {
				case MenuNumber.JOIN:
					join();
					break;
				case MenuNumber.LOGIN:
					System.out.println("<< 로그인 >>");
					break;
				case MenuNumber.EXIT:
					System.out.println("프로그램을 종료합니다");
					isRunning = false;
					break;
				default :
					System.out.println("번호를 잘못 입력하셨습니다");
			} // end switch
			
		} // end while
		
		
	}

}
