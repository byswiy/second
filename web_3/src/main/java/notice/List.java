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

@WebServlet("/notice/list")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항의 목록을 불러와 저장하는 서블릿
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter output = response.getWriter();
		
		output.print("{\"noticeList\":[");
		
		String data = "";
		for(NoticeInfo noticeInfo : Database.noticeInfoTable) {
			data = data + "{\"title\": \"" + noticeInfo.getTitle() + "\",\"contents\":\"" + noticeInfo.getContents() + "\"},";
		}
		
		data = data.substring(0, data.length()-1);
		output.print(data);
		output.print("]}");
			
		output.close();
	}


}
