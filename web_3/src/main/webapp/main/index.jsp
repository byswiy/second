<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/URLConfig.jsp" %>

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
			<a href="${PAGE_NOTICE_LIST }"> [ 더보기 ] </a>
		</div>
		
		<div id="notice_list">
		</div>
	</main>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>
	
	<script>
		
		$("#login_area > form > button").on("click", function() {
			
			let $id = $("input[name=id]");
			let $pw = $("input[name=pw]");
			
			let id = $id.val();
			let pw = $pw.val();
			
			$.ajax({
				url: "${LOGIN_URL}",
				type: "POST",
				data: "id=" + id + "&pw=" + pw,
				dataType: "json",
				success: function(result) {
					
// 					if(loginUserName == "관리자") {
// 						location.href="/web3/main";
// 					} else {}
					$("#login_area").text(result.loginUserName + "님 환영합니다~!");
					
					$("#join_area > button").text("로그아웃");
					$("#join_area > button").off("click");
					$("#join_area > button").on("click", function() {
						location.href="${LOGOUT_URL}"
					});
					
					if(result.userLevel == "admin") {
						$("#join_area").prepend("<button type=\"button\" id=\"admin_notice_write\">공지사항 쓰기</button>");
						$("#admin_notice_write").on("click", function() {
							location.href = "${PAGE_NOTICE_FORM_URL}";
						});
					}
				},
				error: function() {
					
					alert("아이디 또는 비밀번호를 확인해주세요");
				}
			
			});
			
		});
		
		$.ajax ({
			url:"${SERVLET_NOTICE_LIST}",
			type:"GET",
			data: "pageNumber=1",
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