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

@WebServlet("/member/controller1")
public class MemberController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// logout 처리
		HttpSession session = request.getSession();
		
		session.removeAttribute("isLogin");
		session.removeAttribute("loginUserName");
		session.removeAttribute("userLevel");
		
		// 모든 세션정보가 사라진다
//		session.invalidate();
		
		response.sendRedirect("/web3/main/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// login 처리
		MemberService service = new MemberService();
		
		// 로그인 성공 또는 실패 결과 
		MemberInfo memberInfo = service.getLoginResult(request);
	
		// DB에서 아이디아 비밀번호를 사용해서 일치하는 사용자를 찾는다
//		for(MemberInfo nthMemberInfo : Database.memberInfoTable) {
//			String nthid = nthMemberInfo.getId();
//			String nthpw = nthMemberInfo.getPw();
//			
//			success = nthid.equals(id) && nthpw.equals(pw);
//			
//			if(success) {
//				loginUserName = nthMemberInfo.getName();
//				break;
//			}
//		}
		if(memberInfo != null) {
			// 찾았으면 로그인 성공
			String loginUserName = memberInfo.getName();
			String loginUserId = memberInfo.getId();
			String userLevel;
			
			// 관리자가 로그인했다면 userLevel=admin
			// 관리자가 아닌 사용자가 로그인했다면 userLevel=user
			// 으로 상태정보를 기록해보세요
			if(loginUserId.equals("admin")) {
				userLevel = "admin";
			} else {
				userLevel = "user";
			}
			
			HttpSession session = request.getSession();
			
			session.setAttribute("isLogin", true);
			session.setAttribute("userLevel", userLevel);
			session.setAttribute("loginUserName", loginUserName);
			// loginUserName 변수를 지우고 자리에 memberInfo.getName()를 넣어도 된다
			
			response.setContentType("application/json;charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String result = "(\"loginUserName\": \"" + loginUserName + "\", \"userLevel\": \"" + userLevel + "\"}";
			
			out.print(result);
			
			out.close();
			
		} else {
			response.setStatus(400);
		}
	}
}


