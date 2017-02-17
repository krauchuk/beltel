<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
    <jsp:include page="menu.jsp"/>
    <div style="color: #e85b47; text-align:center;" id="post-error">
        Ошибка
        Недостаточно прав для просмотра данной страницы
    </div>
</body>
</html>
