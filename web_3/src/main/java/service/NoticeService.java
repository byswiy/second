package service;

import DAO.NoticeInfoDao;
import etc.Database;
import vo.NoticeInfo;

public class NoticeService {
	public boolean addNotice(NoticeInfo newNoticeInfo) {
		// 공지사항 테이블에 새로운 공지사항 저장
		NoticeInfoDao dao = new NoticeInfoDao();
		
		boolean result = dao.insertNoticeInfo(newNoticeInfo);
		
		return result;
	}
	
	public void getNotice(NoticeInfo newnoticeInfo) {
		String data = "";
		for(NoticeInfo noticeInfo : Database.noticeInfoTable) {
			data = data + "{\"title\": \"" + noticeInfo.getTitle() + "\",\"contents\":\"" + noticeInfo.getContents() + "\"},";
		}
	}
}
