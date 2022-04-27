package service;

import java.util.List;

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
	
	public String loadNoticeInfoListToJson() {
		// 공지사항의 목록을 불러온다
		NoticeInfoDao dao = new NoticeInfoDao();
		List<NoticeInfo> noticeInfoList = dao.selectNoticeInfo();
		
		String data = "{\"noticeList\":[";
		for(NoticeInfo noticeInfo : noticeInfoList) {
			data = data + "{\"title\": \"" + noticeInfo.getTitle() + "\",\"contents\":\"" + noticeInfo.getContents() + "\"},";
		}
		data = data.substring(0, data.length()-1);
		data = data + "]}";
		
		return data;
	}
}
