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
                <span>1</span>
                <span>2</span>
                <span>3</span>
                <span>4</span>
            </div>
        </div>

        <div id="btn_wrapper">
            <button type="button">공지사항 작성</button>
        </div>
	</div>
	
	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>

	
	<script>
		// 공지사항을 목록을 불러와서 보여줄 ajax 
		$.ajax ({
			url:"${SERVLET_NOTICE_LIST}",
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