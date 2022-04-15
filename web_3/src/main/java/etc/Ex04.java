package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 프로그램 종료");
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
			
			// QLIntegrityConstraintViolationException 가 SQLException의 한 종류
			// 부모 예외가 위에 있고 자식 예외가 밑에 있을 경우 부모 예외가 처리를 할 텐데 자식 예외가 필요없다는 컴파일 오류가 뜬다
			// 그렇기 때문에 자식 예외를 부모 예외 보다 위에 둠으로써 예외를 발생시킬 수 있다
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
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
	
	public static MemberInfo loginInput() {
		Scanner scanf = new Scanner(System.in);
		System.out.print("아이디 -> ");
		String id = scanf.next();
		
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
		
		MemberInfo memberInfo = new MemberInfo(id, pw, null);
		
		return memberInfo;
	}
	
	public static boolean executeLoginQuery(MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;
		
		boolean isLogin = false;
		try {
			Class.forName("org.mariadb.jdbc.Drivetr");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM userinfo WHERE id ='" + memberInfo.getId() +"' AND pw = '" + memberInfo.getPw() + "'";
					
			ResultSet rs = stmt.executeQuery(sql);
		
			isLogin = rs.next();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return isLogin;
	}
	
	public static void login() {
		
		// 1. 아이디, 비밀번호를 입력 받는 부분
		MemberInfo memberInfo = loginInput();
		// 2. 로그인 쿼리를 실행하고 결과를 받앙오는 부분
		boolean isLogin = executeLoginQuery(memberInfo);
		// 3. 결과를 출력하는 부분
		if(isLogin) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
	}
	
	public static void updateInput() {
		MemberInfo memberInfo = loginInput();
	}
	
	public static MemberInfo update() {
		// 1. 회원 정보 수정을 하기 위한 아이디, 비밀번호 입력 받기
		Scanner scanf = new Scanner(System.in);
		System.out.print("아이디 -> ");
		String id = scanf.next();
		
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
		
		MemberInfo memberInfo = new MemberInfo(id, pw, null);
		
		return memberInfo;
		
		// 2. 사용자가 입력한 아이디, 비밀번호를 사용해서 수정할 회원의 정보 찾기
		
		// 3. 찾은 회원의 정보 출력
		
		// 4. 수정할 이름 입력 받기
		
		// 5. 사용자가 입력한 이름으로 회원 정보 수정
		
		// 6. 수정 결과 출력
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
					
					login();
					break;
				case MenuNumber.UPDATE:
					System.out.println("<< 회원 정보 수정 >>");
					update();
					break;
				case MenuNumber.DELETE:
					System.out.println("<< 회원 탈퇴 >>");
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
