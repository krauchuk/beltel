<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Настройки PDF</title>
    <link href="<c:url value="/css/light/pdf_settings.css"/> " rel="stylesheet">
    <link href="<c:url value="/css/light/buttons.css"/> " rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp" />
<c:if test="${empty error}">
    <form:form action="${path}/pdf_settings" method="post" commandName="data" id="settings-form">
        <table>
            <tr>
                <td>
                    <span class="td-text-gl">Для</span>
                </td>
                <td>
                    <form:input path="branch"></form:input> <span class="td-text-gl">филиала</span> (Например: Гродненского)
                </td>
            </tr>
            <tr>
                <td>
                    <span class="td-text-gl">Директору</span>
                </td>
                <td>
                    <form:input path="director"></form:input> (Например: Матвееву В.В.)
                </td>
            </tr>
            <tr>
                <td>
                    <span class="td-text-gl">Местоположение</span>
                </td>
                <td>
                    <form:input path="city"></form:input> (Например: г. Гродно)
                </td>
            </tr>
        </table>
        <p>
            <a onmousedown="return false" onclick="postForm()" class="save-pdf-settings-btn">Сохранить</a>
        </p>
    </form:form>
</c:if>
<c:if test="${not empty error}">
    Ошибка доступа к файлу настроек.
</c:if>
<script>
    function postForm() {
        document.getElementById("settings-form").submit();
    }
</script>
</body>
</html>
