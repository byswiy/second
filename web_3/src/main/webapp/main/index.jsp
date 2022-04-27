<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/URLConfig.jsp" %>

<%-- LOGOUT_URL = ${JOIN_URL } --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="/web3/css/header.css">
<link rel="stylesheet" href="/web3/css/footer.css">
<link rel="stylesheet" href="/web3/css/main_index.css">

<script src="/web3/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	<main>
		<div id="notice_title">
			<h2>공지사항</h2>
			<a href="/web3/notice/list.html"> [ 더보기 ] </a>
		</div>
		
<!-- 		<div id="notice_list">공지사항이 없습니다.</div> -->
		<div id="notice_list">
<!-- 			<div class="contents"> -->
<!-- 				<a href=""> -->
<!-- 					<span class="title">국무위원은 국정에 관하여 대통령을 보좌하며, 국무회의의 ...</span> -->
<!-- 				</a> -->
<!-- 			</div> -->
<!-- 			<div class="contents"> -->
<!-- 				<a href=""> -->
<!-- 					<span class="title">대통령후보자가 1인일 때에는 그 득표수가 선거권자 총수의 ...</span> -->
<!-- 				</a> -->
<!-- 			</div> -->
<!-- 			<div class="contents"> -->
<!-- 				<a href=""> -->
<!-- 					<span class="title">헌법재판소 재판관은 정당에 가입하거나 정치에 관여할 수 없다.</span> -->
<!-- 				</a> -->
<!-- 			</div> -->
		</div>
	</main>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>
	
	<script>
		$.ajax ({
			url:"${GET_NOTICE_LIST_URL}",
			type:"GET",
			dataType:"json",
			success: function(noticeInfo) {
				// 접근 방법 noticeList.noticeList[0]
				let noticeList = noticeInfo.noticeList;
				
				for(let i=0; i<noticeList.length; i++) {
					let notice = noticeList[i];
					
					let noticeTag = "<div class=\"contents\">" +
	                    				"<span class=\"order\">" + (i+1) + "</span>" +
	                     					"<a href=\"\">" +
	                     					   "<span class=\"title\"> " + notice["title"] + "<\span>" +
	                    					"</a>" +
	                				"</div>";
	                
	                $("#notice_list").append(noticeTag);
				}
				
			},
			error: function(response) {
				console.log("에러 발생")
				console.log(response)
			}
		})
	</script>
</body>
</html>