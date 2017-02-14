<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
				 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Index</title>
<meta charset="utf-8">
</head>
<body>
<jsp:include page="menu.jsp"/>
	<p>Welcome</p>
</body>
</html>