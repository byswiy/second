package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserInfoDao;
import vo.MemberInfo;

public class MemberService {
	
	public boolean getLoginResult(MemberInfo memberInfo) {
		
		
		UserInfoDao userInfo = new UserInfoDao();
	
		memberInfo = userInfo.selectUserInfo(memberInfo);
	
		boolean success = memberInfo.getName() == null ? false : true;
		
		return success;
	}
	
}
