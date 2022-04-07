package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/notice/header")
public class Header extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			 boolean isLogin = (boolean) session.getAttribute("isLogin");
			 String loginUserName = (String) session.getAttribute("loginUserName");
			 String userLevel = (String) session.getAttribute("userLevel");
			 
			 response.setContentType("application/json;charset=UTF-8");
			 PrintWriter output = response.getWriter();
			 
			 // 로그인 했다는 것을 알려줌
			 // Json으로 묶어서 필요없음
//			 output.print("isLogin=true");
			 
			// Json으로 묶어서 필요없음
//			 output.print("loginUserName= " + loginUserName);
			 
			 output.print("{\"isLogin\":true, \"loginUserName\":\"" + loginUserName + "\",\"userLevel\":\"" + userLevel + "\"}");
			 
			 output.close();
			 
		} catch(NullPointerException e) {
			// 로그인 하지 않은 사용자의 경우 예외 발생
			PrintWriter output = response.getWriter();
			output.print("{\"islogin\": false}");
			output.close();
		}
		
		
		// 로그인 여부
		// 로그인 했다면 -> 로그인한 사용자 이름 노출
		//				-> 로그인한 사용자가 관리자인지 일반 사용자인지 확인
	}


}
