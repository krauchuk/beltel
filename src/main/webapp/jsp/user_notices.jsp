<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Ваши заявки</title>
    <meta charset="utf-8">
    <link href="<c:url value="/css/dark/table.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/datatables.min.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<p style="text-align:center">Список ваших заявок</p>
<table id="user-notices" style="visibility: hidden">
    <thead>
    <tr>
        <th></th>
        <th>ID</th>
        <th>Пользователь</th>
        <th>Время подачи</th>
        <th>Время одобрения</th>
        <th>Статус</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty reportingNotice}">
        <c:forEach var="notice" items="${reportingNotice}">
            <tr>
                <td><a class="view-btn" href="notice/${notice.id}">Подробнее</a></td>
                <td>${notice.id}</td>
                <td>${notice.employers_id.fio}</td>
                <td>${notice.dateSet}</td>
                <td>${notice.dateDone}</td>
                <c:if test="${notice.status == true}">
                    <td>
                        <span class="notice-status-true">&#10004;</span>
                    </td>
                </c:if>
                <c:if test="${notice.status == false}">
                    <td>
                        <span class="notice-status-false">&#10006;</span>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<script>
    $(function () {
        $("#user-notices").dataTable({
            language: {
                "processing": "Подождите",
                "search": "Поиск:",
                "lengthMenu": "Показать _MENU_ записей",
                "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
                "infoEmpty": "Записи с 0 до 0 из 0 записей",
                "infoFiltered": "(отфильтровано из _MAX_ записей)",
                "infoPostFix": "",
                "loadingRecords": "Загрузка записей",
                "zeroRecords": "Записи отсутствуют",
                "emptyTable": "В таблице отсутствуют данные",
                "paginate": {
                    "first": "Первая",
                    "previous": "Предыдущая",
                    "next": "Следующая",
                    "last": "Последняя"
                },
                "aria": {
                    "sortAscending": ": активировать для сортировки столбца по возрастанию",
                    "sortDescending": ": активировать для сортировки столбца по убыванию"
                }
            },
            stateSave: true
        });
    })
    $(document).ready(function () {
        document.getElementById("user-notices").style.visibility = "visible";
    })
</script>
</body>
</body>
</html>