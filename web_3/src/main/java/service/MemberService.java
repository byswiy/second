package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserInfoDao;
import vo.MemberInfo;

public class MemberService {
	
	public boolean getLoginResult(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberInfo memberInfo = new MemberInfo(id, pw);
		
		UserInfoDao userInfo = new UserInfoDao();
	
		memberInfo = userInfo.selectUserInfo(memberInfo);
	
		boolean success = memberInfo.getName() == null ? false : true;
		
		return success;
	}
	
}
