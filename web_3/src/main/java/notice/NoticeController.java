package notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.UrlConfig;
import service.NoticeService;
import vo.NoticeInfo;

@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 공지사항 작성 -> 공지사항 테이블에 공지사항 제목, 내용 등 공지사항 정보를 저장
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
				// 공지사항 추가 실패와 관련된 처리
			}
			
			
	}

}
