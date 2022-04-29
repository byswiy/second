<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="LOGOUT_URL" value="/web3/member/controller1" />

<c:set var="LOGIN_URL" value="/web3/member/controller1" />

<c:set var="JOIN_URL" value="/web3/member/join.jsp" /> 

<%-- 서블릿을 호출하는 URL은 SERVLET으로 시작 --%>
<%-- 페이지를 이동하는 URL은 PAGE로 시작 --%>
<c:set var="SERVLET_JOIN_URL" value="/web3/member/join"/>

<c:set var="PAGE_MAIN_URL" value="/web3/main/index.jsp"/>

<c:set var="PAGE_NOTICE_FORM_URL" value="/web3/notice/form.jsp"/>

<c:set var="SERVLET_NOTICE_WRITE" value="/web3/notice/controller" />

<c:set var="SERVLET_NOTICE_LIST" value="${SERVLET_NOTICE_WRITE }" />

<c:set var="PAGE_NOTICE_LIST" value="/web3/notice/list.jsp"/>

<c:set var="SERVLET_NOTICE_AMOUNT" value="/web3/notice/controller2" />