package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NoticeInfoDao;
import etc.Database;
import etc.UrlConfig;
import service.NoticeService;
import vo.NoticeInfo;

@WebServlet("/notice/controller")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 공지사항 목록 전달
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항의 목록을 불러와 저장
		
		// 지금 우리 컨트롤러 구조는
		// 1. 공지사항의 목록을 불러온다
		// 2. 불러온 목록을 JSON으로 구성해서 전달한다
		
		// 컨트롤러의 구조를 다르게 생각해서 
		// 1. 공지사항의 목록을 불러와 JSON으로 구성한다
		// 2. JSON을 전달한다
		
		// 공지사항의 목록을 불러온다
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		NoticeService service = new NoticeService();
		String data = service.loadNoticeInfoListToJson(pageNumber);
		
		// JSON을 전달한다
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter output = response.getWriter();
		output.print(data);
		output.close();
		// 불러온 목록을 JSON으로 구성해서 전달한다
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		// 클라이언트가 보낸 파라미터 값 검증
		
		// 공지사항 데이터를 공지사항 정보로 뭉쳐주고
		NoticeInfo noticeInfo = new NoticeInfo(title, contents);
		
		// 공지사항 테이블에 저장
		NoticeService service = new NoticeService();
		
		boolean result = service.addNotice(noticeInfo);
		if(result) {
			// 공지사항 목록 페이지로 이동
			response.sendRedirect(UrlConfig.PAGE_NOTICE_LIST_URL);
		} else {
			// 공지사항 추가 실패와 관련된 처리를 함
		}
		
		
	}

}
