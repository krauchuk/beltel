<%@page import="by.grsu.controller.AppController" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <link href="<c:url value="/css/dark/menu.css" />" rel ="stylesheet">
    <meta charset="utf-8">
</head>
<body>
<ul class="menu-ul">
    <sec:authorize access="hasRole('ADMIN')">
        <li class="menu-li"><a href="${path}/all_notices">Все заявки</a></li>
        <li class="menu-li"><a href="${path}/new_notices">Новые заявки</a></li>
        <li class="menu-li"><a href="${path}/edit_db">Редактирование БД</a></li>
        <li class="menu-li"><a href="${path}/pdf_settings">Настройки PDF</a></li>
    </sec:authorize>

    <sec:authorize access="hasRole('USER')">
        <li class="menu-li"><a href="${path}/create_notice">Новая заявка</a></li>
        <li class="menu-li"><a href="${path}/user_notices">Ваши заявки</a></li>
    </sec:authorize>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <c:url value="/login?logout" var="logoutUrl"/>

        <li class="li-left">
            <div class="username">${pageContext.request.userPrincipal.name}</div>
            <a href="${path}/logout">Выйти</a>
        </li>
    </c:if>
</ul>
</body>
</html>