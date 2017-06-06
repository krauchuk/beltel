<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>История операций</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/datatables.min.js"/>"></script>
    <link href="<c:url value="/css/light/table.css" />" rel="stylesheet">
    <link href="<c:url value="/css/light/buttons.css" />" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>
<p style="text-align:center">Список операций</p>
<table id="history_table" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>ID</th>
        <th>Время</th>
        <th>Пользователь</th>
        <th>Операция</th>
    </tr>
    </thead>
</table>
<script>
    $(function () {
        $("#history_table").dataTable({
            "ajax": {
                "url": "admin/get_operations_history",
                "dataSrc": ""
            },
            columns: [
                {data: "id"},
                {
                    data: "date",
                    "render": function (data) {
                        var date = new Date(data);
                        month = date.getMonth();
                        month = month + 1;
                        if (month < 10) month = "0" + month;
                        year = date.getFullYear();
                        day = date.getDate();
                        if (day < 10) day = "0" + day;
                        hour = date.getHours();
                        if (hour < 10) hour = "0" + hour;
                        minutes = date.getMinutes();
                        if (minutes < 10) minutes = "0" + minutes;
                        seconds = date.getSeconds();
                        if (seconds < 10) seconds = "0" + seconds;
                        return day + "/" + month + "/" + year + " " + hour + ":" + minutes + ":" + seconds;
                    }
                },
                {data: "userName"},
                {data: "operation"}
            ],
            "order": [[0, "desc"]],
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
            stateSave: true,
        });
    })
</script>
</body>
</html>