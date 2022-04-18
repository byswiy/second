package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.MemberInfo;

@WebServlet("/member/join")
public class Join extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		
		// 클라이언트가 전달한 파라미터에서 회원 정보를 꺼냄
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		// 파라미터 검증
		// 1. 아이디 / 비밀번호 / 이름을 입력하지 않았을 때
		// 2. 아이디 / 비밀번호 / 이름의 길이 제한
		// 3. 아이디 / 비밀번호 / 이름에 사용할 수 없는 문자 확인
		//						   꼭 필요한 문자 확인
		
		// 회원 정보를 생성
		MemberInfo memberList =  new MemberInfo(id, pw, name);
		
		// 아이디 중복 여부 확인
		
		
		// 회원 정보를 Table에 저장
		Database db = new Database();
		// 1. 커넥션 연결
		Connection conn = db.getConnection();
		// 2. stmt 생성
		try {
			Statement stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Database.memberInfoTable.add(memberList);
		
		// 회원 가입 성공 페이지로 이동
		response.sendRedirect("/web3/member/joinSuccess.html");
	}

}
