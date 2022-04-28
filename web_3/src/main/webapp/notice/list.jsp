<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../includes/URLConfig.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet Project</title>
<link rel="stylesheet" href="/web3/css/header.css">
<link rel="stylesheet" href="/web3/css/footer.css">
<link rel="stylesheet" href="/web3/css/notice_list.css">
<script src="/web3/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<%@ include file="../includes/header.jsp" %>
	
	<div id="wrapper">
        <h2>공지사항</h2>

        <div id="notice_wrapper">
            <div id="title_info_wrapper">
                <span class="order">번호</span>
                <span class="title">제목</span>
            </div>
            <div id="list">
<!--                 <div class="contents"> -->
<!--                     <span class="order">3</span> -->
<!--                     <a href=""> -->
<!--                         <span class="title">국무위원은 국정에 관하여 대통령을 보좌하며, 국무회의의 ...</span> -->
<!--                     </a> -->
<!--                 </div> -->
<!--                 <div class="contents"> -->
<!--                     <span class="order">2</span> -->
<!--                     <a href=""> -->
<!--                         <span class="title">대통령후보자가 1인일 때에는 그 득표수가 선거권자 총수의 ...</span> -->
<!--                     </a> -->
<!--                 </div> -->
<!--                 <div class="contents"> -->
<!--                     <span class="order">1</span> -->
<!--                     <a href=""> -->
<!--                         <span class="title">헌법재판소 재판관은 정당에 가입하거나 정치에 관여할 수 없다.</span> -->
<!--                     </a> -->
<!--                 </div> -->
            </div>
            <div class="pagination">
            </div>
        </div>
	</div>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>

	
	<script>
		// 공지사항 목록의 페이지네이션을 구성할 ajax
		$.ajax ({
			// 공지사항의 개수를 전달할 서블릿
			url:"${SERVLET_NOTICE_AMOUNT}",
			type:"get",
			success: function(amount) {
				// 전제 공지 사항의 개수를 받아와서 
				// 전체 개수를 사용해서 적절히 페이지네이션을 출력하도록 한다
				let roofCount = amount / 5;
				for(let i=1; i<=roofCount; i++) {
					// 페이지네이션 출력
					$(".pagination").addpend("<span>" + i + "</span>");
				}
				console.log(amount);
			},
			error: function() {
				
			}
		})
	
		// 공지사항을 목록을 불러와서 보여줄 ajax 
		$.ajax ({
			url:"${SERVLET_NOTICE_LIST}",
			type:"GET",
			data: "pageNumber=1",
			dataType:"json",
			success: function(noticeInfo) {
				// 접근 방법 noticeList.noticeList[0]
				let noticeList = noticeInfo.noticeList;
				console.log(noticeList);
				for(let i=0 i<noticeList.length; i++) {
					let notice = noticeList[i];
					
					let noticeTag = "<div class=\"contents\">" +
	                    				"<span class=\"order\">" + (noticeList.length - i)  + "</span>" +
	                     					"<a href=\"\">" +
	                     					   "<span class=\"title\"> " + notice["title"] + "</span>" +
	                    					"</a>" +
	                				"</div>";
	                
	                $("#list").append(noticeTag);
	               
				}
			},
			error: function(response) {
				console.log("에러 발생")
				console.log(response)
			}
		})
		
		// 공지사항 작성 버튼에서 사용할 ajax
	</script>
	
</body>
</html>