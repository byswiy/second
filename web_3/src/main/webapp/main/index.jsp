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
	<header>
		<div id="login_area">
			<!-- 로그인을 한 상태라면 (loginUserName)님 환영합니다! 를 출력 -->
			<c:if test="${sessionScope.isLogin eq true }">
				${sessionScope.loginUserName }님 환영합니다
			</c:if>
			
			<!-- 로그인을 하지 않은 상태라면 로그인 form을 보여준다 -->
			<c:if test="${sessionScope.isLogin ne true }">
				<form action="${LOGIN_URL }" method="POST">
					<input type="text" name="id" placeholder="아이디">
					<input type="password" name="pw" placeholder="비밀번호">
					<button type="button">로그인</button>
				</form>
			</c:if>
		</div>
		
		<c:set var="buttonText" value="회원가입"/>
		<c:if test="${sessionScope.isLogin eq true }">
			<c:set var="buttonText" value="로그아웃"/>
		</c:if>
		
		<div id="join_area">	
			<c:if test="${sessionScope.isLogin eq true }">
				<c:if test="${sessionScope.userLevel eq 'admin' }">
					<button type="button" id="admin_notice_write">공지사항 쓰기</button>
					<script type="text/javascript">
						$("#admin_notice_write").on("click", function() {
							location.href = "web3/notice/form"
						})
					</script>
				</c:if>
				<button type="button">로그아웃</button>
				
				<script>
					$("#join_area > button").on("click", function() {
						location.href = "${LOGOUT_URL}"
					});
				</script>
			</c:if>
			
			<c:if test="${sessionScope.isLogin ne true }">
				<button type="button">회원가입</button>
				
				<script>
					$("#join_area > button").on("click", function() {
						location.href="${JOIN_URL}"
					});
				</script>
			</c:if>
		
			<%-- <button type="button">${buttonText }</button> --%>
		</div>
	</header>
	
	<main>
			<div id="notice_title">
			<h2>공지사항</h2>
			<a href="/project2/notice/list.html"> [ 더보기 ] </a>
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
	
	<script type="text/javascript">
// 		$("#join_area > button").on("click", function() {
// 			location.href="/web3/member/join.html"
// 		});
		
// 		$("#login_area > input[type=submit]").on("click", function(e) {
// 			e.preventDefault();
		$("#login_area > form > button").on("click", function() {
			
			let $id = $("input[name=id]");
			let $pw = $("input[name=pw]");
			
			let id = $id.val();
			let pw = $pw.val();
			
			$.ajax({
				url: "${LOGIN_URL }",
				type: "POST",
				data: "id=" + id + "&pw=" + pw,
				dataType: "text",
				success: function(loginUserName) {
					// 로그인 성공
					// 로그인 한 사용자의 이름 출력
// 					alert("로그인 성공! 사용자의 이름을 출력할 차례");
					if(loginUserName == "관리자") {
						location.href="/web3/main";
					} else {
						$("#login_area").text(loginUserName + "님 환영합니다~!");
					$("#join_area > button").text("로그아웃");
					// #join_area > button 이 2개의 click 이벤트를 실행하고 있기 때문에
					// 위에서 join.html로 이동하는 click 이벤트를 삭제시켜주는 이벤트 처리를 해준다
					$("#join_area > button").off("click");
					$("#join_area > button").on("click", function() {
						location.href="${LOGOUT_URL}"
					});
					}
					
					
				},
				error: function() {
					// 로그인 실패
					// 서버에 문제가 생겼을 때
					
					alert("아이디 또는 비밀번호를 확인해주세요");
					
					// 아무것도 하지 않음
				}
			
			});
			
// 			return false;
		});
		
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