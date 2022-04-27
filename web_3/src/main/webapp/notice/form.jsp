<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 권한이 있는 사용자만 이 페이지를 이용할 수 있어야한다 -->
<%
try {
	String userLevel = (String) session.getAttribute("userLevel");
	
	if(userLevel.equals("admin")) {
		// 권한이 있는 사용자(관리자)가 접근했을 때만 공지사항 쓰기 페이지가 보이도록 하고
	} else {
		// 권한이 없는 사용자(로그인을 하지 않았거나 일반사용자 로그인)가 접근했을 때는 메인 페이지가 보이도록 하기
		response.sendRedirect("/web3/main");
	}
	
} catch(NullPointerException e) {
	// 로그인을 하지않은 사용자가 접근했을 때 메인 페이지가 보이도록 함
	response.sendRedirect("/web3/main");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="/web3/css/header.css">
<link rel="stylesheet" href="/web3/css/footer.css">
<link rel="stylesheet" href="/web3/css/notice_form.css">
</head>
<body>
	<header>
		<div id="login_area">
			<form action="/web3/member/login" method="POST">
				<input type="text" name="id" placeholder="아이디">
				<input type="password" name="pw" placeholder="비밀번호">
				<input type="submit" value="로그인">
			</form>
		</div>
		<div id="join_area">
			<button type="button">회원가입</button>
		</div>
	</header>
	
	<div id="wrapper">
        <h2>공지사항</h2>

		<form action="/web3/notice" method="POST">
			<div id="title_wrapper">
				<label>제목&nbsp;&nbsp;&nbsp;&nbsp;: <input type="text" name="title"></label>
			</div>
			
			<div id="contents_wrapper">
				<label>내용<br><textarea name="contents" cols="100" rows="10"></textarea> </label>
			</div>
			
			<div>
				<label>첨부파일 : <input type="file" name="file"></label>
			</div>

	        <div id="btn_wrapper">
	            <button type="submit">공지사항 작성</button>
	        </div>
        </form>
	</div>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>
</body>
</html>