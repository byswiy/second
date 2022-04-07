package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/notice/form")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 용도 : 공지사항 쓰기 페이지로 들어갈 때 접근할 서블릿
	//       권한이 있는 사용자(관리자)가 접근했을 때만 공지사항 쓰기 페이지가 보이도록 하고
	//       권한이 없는 사용자(로그인을 하지 않았거나 일반사용자 로그인)가 접근했을 때는 메인 페이지가 보이도록 하기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 더 간결한 코드
		try {
			HttpSession session = request.getSession();
			
			String userLevel = (String) session.getAttribute("userLevel");
			
			if(userLevel.equals("admin")) {
				// 권한이 있는 사용자(관리자)가 접근했을 때만 공지사항 쓰기 페이지가 보이도록 하고
				RequestDispatcher rd = request.getRequestDispatcher("/notice/form.html");
				rd.forward(request, response);
				
			} else {
				// 권한이 없는 사용자(로그인을 하지 않았거나 일반사용자 로그인)가 접근했을 때는 메인 페이지가 보이도록 하기
				response.sendRedirect("/web3/main");
			}
			
		} catch(NullPointerException e) {
			// 로그인을 하지않은 사용자가 접근했을 때 메인 페이지가 보이도록 함
			response.sendRedirect("/web3/main");
		}
		
		
		
		
//		try {
//			HttpSession session = request.getSession();
//			
//			String userLevel = (String) session.getAttribute("userLevel");
//			
//			if(userLevel.equals("admin")) {
//				// 권한이 있는 사용자(관리자)가 접근했을 때만 공지사항 쓰기 페이지가 보이도록 하고
//				RequestDispatcher rd = request.getRequestDispatcher("notice/form.html");
//				rd.forward(request, response);
//			} else {
//				// 권한이 없는 사용자(로그인을 하지 않았거나 일반사용자 로그인)가 접근했을 때는 메인 페이지가 보이도록 하기
//				RequestDispatcher rd = request.getRequestDispatcher("main/index.html");
//				rd.forward(request, response);
//			}
//			
//		} catch(NullPointerException e) {
//			// 로그인을 하지않은 사용자가 접근했을 때 메인 페이지가 보이도록 함
//			RequestDispatcher rd = request.getRequestDispatcher("main/index.html");
//			rd.forward(request, response);
//		}
		
		
		
		
		
	}

}
