package etc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import vo.MemberInfo;
import vo.NoticeInfo;

// 서블릿이 만들어지면서 객체가 만들어지지만 라이프사이클 사용해서 접근하기
public class startupProcessor extends HttpServlet{

	// 이 서블릿을 직접 등록하고 
	// 등록할 때 load-on-startup 태그를 사용해서
	// 서버가 시작되면서 서블릿이 만들어지도록하고
	
	// 서블릿이 만들어질 때 우리에게 필요한 계정을 생성해서
	// DB에 저장되도록하세요
	

	@Override
	public void init() throws ServletException {
		System.out.println("<< startupProcessor 동작 >>");
		
		MemberInfo memberInfo = new MemberInfo("id1", "pw1", "name1"); 
		MemberInfo adminInfo = new MemberInfo("admin", "admin123", "관리자");
		
		Database.memberInfoTable.add(memberInfo);
		Database.memberInfoTable.add(adminInfo);
		
		
		NoticeInfo noticeInfo = new NoticeInfo("제목1", "내용1");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목2", "내용2");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목3", "내용3");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목4", "내용4");
		Database.noticeInfoTable.add(noticeInfo);
		
	}
	
	
	
	
	
}
