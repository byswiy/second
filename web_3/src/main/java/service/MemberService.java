package service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import DAO.UserInfoDao;
import vo.MemberInfo;

public class MemberService {
	public MemberInfo getLoginResult(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberInfo memberInfo = new MemberInfo(id, pw);
		
		UserInfoDao userInfo = new UserInfoDao();
		
		memberInfo = userInfo.selectUserInfo(memberInfo);
		
		memberInfo = memberInfo.getName() == null ? null : memberInfo;
		
		return memberInfo;
	}
}
