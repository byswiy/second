package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.MemberInfo;
// 로그인 처리와 로그 아웃 처리를 할 서블릿
@WebServlet("/member/controller1")
public class MemberController1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그 아웃 처리
		HttpSession session = request.getSession();
		session.removeAttribute("isLogin");
		session.removeAttribute("loginUserName");
		session.removeAttribute("userLevel");
		
		response.sendRedirect("/web3/main/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 처리
		
		MemberService service = new MemberService();
		
		MemberInfo memberInfo = service.getLoginResult(request);
		
		// 로그인 성공 또는 실패
		if(memberInfo != null) {
			// 찾았으면 로그인 성공
			String loginUserName = memberInfo.getName();
			String loginUserId = memberInfo.getId();
			String userLevel;
			
			if(loginUserId.equals("admin")) {
				userLevel = "admin";
			} else {
				userLevel = "user";
			}
			
			HttpSession session = request.getSession();
			
			session.setAttribute("isLogin", true);
			session.setAttribute("userLevel", userLevel);
			session.setAttribute("loginUserName", loginUserName);
			
			response.setContentType("application/json;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String result = "{\"loginUserName\": \"" + loginUserName + "\", \"userLevel\":\"" + userLevel + "\"}";
			
			out.print(result);
			
			out.close();
			
		} else {
			response.setStatus(400);
		}
	}

}
