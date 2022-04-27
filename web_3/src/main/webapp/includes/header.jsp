<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			
			<script>
			$("#login_area > form > button").on("click", function() {
				
				let $id = $("input[name=id]");
				let $pw = $("input[name=pw]");
				
				let id = $id.val();
				let pw = $pw.val();
				
				$.ajax({
					url: "${LOGIN_URL }",
					type: "POST",
					data: "id=" + id + "&pw=" + pw,
					dataType: "json",
					success: function(result) {
						// 로그인 성공
						// 로그인 한 사용자의 이름 출력
//	 					alert("로그인 성공! 사용자의 이름을 출력할 차례");
//	 					if(loginUserName == "관리자") {
//	 						location.href="/web3/main";
//	 					} else {
//	 						
//	 					}
						$("#login_area").text(result.loginUserName + "님 환영합니다~!");
						
						$("#join_area > button").text("로그아웃");
							// #join_area > button 이 2개의 click 이벤트를 실행하고 있기 때문에
							// 위에서 join.html로 이동하는 click 이벤트를 삭제시켜주는 이벤트 처리를 해준다
						$("#join_area > button").off("click");
						$("#join_area > button").on("click", function() {
							location.href="${LOGOUT_URL}"
						});
						
						if(result.userLevel == "admin") {
							$("#join_area").prepend("<button type=\"button \" id=\"admin_notice_write\">공지사항 쓰기</button>")
							$("#admin_notice_write").on("click", function() {
								location.href = "${PAGE_NOTICE_FORM_URL}"
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
				
//	 			return false;
			});
			</script>
		</c:if>
	</div>
	
<%-- 	<c:set var="buttonText" value="회원가입"/> --%>
<%-- 	<c:if test="${sessionScope.isLogin eq true }"> --%>
<%-- 		<c:set var="buttonText" value="로그아웃"/> --%>
<%-- 	</c:if> --%>
	
	<div id="join_area">	
		<c:if test="${sessionScope.isLogin eq true }">
			<c:if test="${sessionScope.userLevel eq 'admin' }">
				<button type="button" id="admin_notice_write">공지사항 쓰기</button>
				
				<script>
					$("#admin_notice_write").on("click", function() {
						location.href = "${PAGE_NOTICE_FORM_URL}"
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
	</div>
	</header>