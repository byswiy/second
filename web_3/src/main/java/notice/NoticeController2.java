package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NoticeInfoDao;
import service.NoticeService;

@WebServlet("/notice/controller2")
public class NoticeController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 전체 개수를 불러와 전달
		
		NoticeInfoDao dao = new NoticeInfoDao();
		
		int amount = dao.getAmountOfNotice();
		
		PrintWriter out = response.getWriter();
		
		out.print(amount);
		
		out.close();
	}

}
