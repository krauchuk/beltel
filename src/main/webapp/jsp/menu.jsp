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
    <link href="<c:url value="/css/light/menu.css" />" rel ="stylesheet">
    <meta charset="utf-8">
</head>
<body>
<ul class="menu-ul">
    <sec:authorize access="hasRole('ADMIN')">
        <li class="menu-li"><a href="${path}/all_notices" class="menu-a">Все заявки</a></li>
        <li class="menu-li"><a href="${path}/new_notices" class="menu-a">Новые заявки</a></li>
        <li class="menu-li"><a href="${path}/edit_db" class="menu-a">Редактирование БД</a></li>
        <li class="menu-li"><a href="${path}/pdf_settings" class="menu-a">Настройки PDF</a></li>
    </sec:authorize>

    <sec:authorize access="hasRole('USER')">
        <li class="menu-li"><a href="${path}/create_notice" class="menu-a">Новая заявка</a></li>
        <li class="menu-li"><a href="${path}/user_notices" class="menu-a">Ваши заявки</a></li>
    </sec:authorize>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <c:url value="/login?logout" var="logoutUrl"/>

        <li class="menu-li-left-block">
            <div class="menu-username">${pageContext.request.userPrincipal.name}</div>
            <div class="menu-li"><a href="${path}/logout" class="menu-a">Выйти</a></div>
        </li>
    </c:if>
</ul>
</body>
</html>