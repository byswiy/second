package service;

import java.util.List;

import DAO.NoticeInfoDao;
import vo.NoticeInfo;

public class NoticeService {
	public boolean addNotice(NoticeInfo newNoticeInfo) {
		// 공지사항 테이블에 새로운 공지사항을 저장한다.
		NoticeInfoDao dao = new NoticeInfoDao();
		
		boolean result = dao.insertNoticeInfo(newNoticeInfo);
		
		return result;
	}
	
	public String loadNoticeInfoListToJson() {
		NoticeInfoDao dao = new NoticeInfoDao();
		List<NoticeInfo> noticeInfoList = dao.selectNoticeInfo();
		
		// 불러온 공지사항 목록을 JSON으로 구성한다.
		String data = "{\"noticeList\":[";
		for (NoticeInfo noticeInfo : noticeInfoList) {
			data = data + "{\"title\": \"" + noticeInfo.getTitle() + "\",\"contents\":\"" + noticeInfo.getContents() + "\"},";
		}
		data = data.substring(0, data.length() - 1);
		data = data + "]}";
		
		return data;
	}
}
