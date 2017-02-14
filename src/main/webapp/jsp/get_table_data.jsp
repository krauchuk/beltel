<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/datatables.min.js"/>"></script>
    <link href="<c:url value="/css/dark/table.css" />" rel="stylesheet">
</head>
<body>
<div>
    <a style="padding: 3px; cursor: pointer;" onclick="setPopupContent('0', '${dataType}')">+ Добавить запись</a>
    <p></p>
</div>
<table id="data_table">
    <thead>
    <tr>
        <th>ID</th>
        <c:choose>
            <c:when test="${dataType == 'division'
                or dataType == 'sector'}">
                <th>Название</th>
                <th>Сокращенно</th>
                <c:choose>
                    <c:when test="${dataType == 'division'}">
                        <th>Начальник</th>
                    </c:when>
                    <c:when test="${dataType == 'sector'}">
                        <th>Отдел</th>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${dataType == 'employer'}">
                <th>ФИО</th>
                <th>Должность</th>
                <th>Отдел</th>
            </c:when>
            <c:when test="${dataType == 'post'
            or dataType == 'regimeaccess'
            or dataType == 'resource'
            or dataType == 'zues'
            or dataType == 'subresource'}">
                <th>Название</th>
                <c:choose>
                    <c:when test="${dataType == 'subresource'}">
                        <th>Ресурс</th>
                    </c:when>
                    <c:when test="${dataType == 'zues'}">
                        <th>Область</th>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="data" items="${data}">
        <tr>
            <td>${data.id}</td>
            <c:choose>
                <c:when test="${dataType == 'division'
                or dataType == 'sector'}">
                    <td>${data.name}</td>
                    <td>${data.shortName}</td>
                    <c:choose>
                        <c:when test="${dataType == 'division'}">
                            <td>${data.divhead_id.fio}</td>
                        </c:when>
                        <c:when test="${dataType == 'sector'}">
                            <td>${data.division_id.name}</td>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:when test="${dataType == 'employer'}">
                    <td>${data.fio}</td>
                    <td>${data.post_id.name}</td>
                    <td>${data.sector_id.division_id.shortName}</td>
                </c:when>
                <c:when test="${dataType == 'post'
                or dataType == 'regimeaccess'
                or dataType == 'resource'
                or dataType == 'zues'
                or dataType == 'subresource'}">
                    <td>${data.name}</td>
                    <c:choose>
                        <c:when test="${dataType == 'subresource'}">
                            <td>${data.resource_id.name}</td>
                        </c:when>
                        <c:when test="${dataType == 'zues'}">
                            <td>${data.region_id.name}</td>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    Ошибка. Данный dataType отсутствует.
                </c:otherwise>
            </c:choose>
            <td>
                <a class="view-btn" onclick="setPopupContent('${data.id}', '${dataType}')" style="cursor: pointer;">Редактировать</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    var tableDT;
    $(function () {
        tableDT = $("#data_table").dataTable({
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
    });
</script>
</body>
</html>
