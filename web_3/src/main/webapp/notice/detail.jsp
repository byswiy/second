<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="/project2/css/header.css">
<link rel="stylesheet" href="/project2/css/footer.css">
<link rel="stylesheet" href="/project2/css/notice_detail.css">
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
    <div id="wrapper">
        <h2>공지사항</h2>

		<div id="title_wrapper">
			<span>제목</span>
		</div>
		
		<div id="contents_wrapper">
			<p>내용</p>
		</div>
		
		<div id="file_wrapper">
			<img src="/project2/images/img.png" alt=""><a href="#">첨부파일</a>
		</div>

        <div id="btn_wrapper">
            <button type="button">목록으로</button>
        </div>
	</div>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>
</body>
</html>