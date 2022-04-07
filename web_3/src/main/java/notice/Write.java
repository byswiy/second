package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.NoticeInfo;

@WebServlet("/notice")
public class Write extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 작성 -> 공지사항 테이블에 공지사항 제목, 내용 등 공지사항 정보를 저장
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		// 공지사항 데이터를 공지사항 정보로 뭉쳐주고
		NoticeInfo noticeInfo = new NoticeInfo(title, contents);
		
		// 공지사항 테이블에 저장
		Database.noticeInfoTable.add(noticeInfo);
		
		// 공지사항 목록 페이지로 이동
		response.sendRedirect("/web3/notice/list.html");
	}

}
