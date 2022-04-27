<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/URLConfig.jsp" %>
<!-- 권한이 있는 사용자만 이 페이지를 이용할 수 있어야한다 -->
<c:set var="userLevel" value="${sessionScope['userLevel'] }" />

<c:if test="${userLevel ne 'admin' }">
	<c:redirect url="/main"></c:redirect>
</c:if>

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
	<%@ include file="../includes/header.jsp" %>
	
	<div id="wrapper">
        <h2>공지사항</h2>

		<form action="${SERVLET_NOTICE_WRITE }" method="POST">
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