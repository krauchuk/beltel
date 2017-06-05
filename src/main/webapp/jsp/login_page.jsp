<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
    <link href="<c:url value="/css/light/login_page.css" />" rel="stylesheet">
    <title>Вход в систему</title>
<body>
<div class="vertical-center">
    <div class="center-text">
        <h1>РУП "Белтелеком"</h1>
    </div>
    <form action="<%=request.getContextPath()%>/login" method="POST" class="container">
        <input type="text" name="username" placeholder="Введите логин" autofocus/>
        <input type="password" name="password" placeholder="Введите пароль"/>
        <c:if test="${param.error != null}">
            <div class="error-text">Неправильный логин или пароль</div>
        </c:if>
        <input type="submit" value="Войти"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<script>
    <%--if document ready--%>
    $(function(){
        if (window.location.href.toLowerCase().indexOf("loaded") < 0) {
            location.reload();
        }
    });
</script>
</body>
</html>