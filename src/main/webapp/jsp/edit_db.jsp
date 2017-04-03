<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Редактирование БД</title>
    <meta charset="utf-8">
    <link href="<c:url value="/css/light/edit_db.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/light/popup.css"/> " rel="stylesheet">
    <link href="<c:url value="/css/light/buttons.css"/> " rel="stylesheet">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<p style="text-align:center">Редактирование таблиц БД</p>
<div class="edit-db-select-line">
    Таблица:
    <select id="select_table_name">
        <option disabled selected hidden></option>
        <option value="table_post">Должности</option>
        <option value="table_zues"> ЗУЕС</option>
        <option value="table_division">Отделы</option>
        <option value="table_subresource">Подресурсы</option>
        <option value="table_regimeaccess">Режимы доступа</option>
        <option value="table_resource">Ресурсы</option>
        <option value="table_sector">Сектора</option>
        <option value="table_employer">Сотрудники</option>
    </select>
    <a class="refresh-table-btn" onclick="getCurrentData()">Обновить таблицу</a>
</div>
<p></p>
<div style="float:left;width: 100%; visibility: hidden;" id="get_table"></div>
<div class="popup" id="popup">
    <div class="popup-content" id="popup-content">
    </div>
</div>
<script>
    hidePopup();
    $(document).ready(function () {
        $("#popup-content").append('<a class="close-btn" onclick="hidePopup()">&#10006;</a><div id="popup-data"></div>');
    });
    $("#select_table_name").on("change", function () {
        getCurrentData();
    });
    function getCurrentData() {
        $(document).ready(function () {
            var selectTableUrl = $("select#select_table_name").val();
            $.ajax({
                type: "GET",
                url: "admin/" + selectTableUrl,
                success: function (data) {
                    $('#get_table').html(data);
                }
            });
        });
    }
    function goToLastPage() {
        $(document).ready(function () {
            tableDT.fnPageChange("last", true);
        });
    }
    function setPopupContent(dataID, dataType) {
        $.ajax({
            type: "GET",
            url: "admin/table_" + dataType + "_edit/" + dataID,
            success: function (data) {
                $("#popup-data").html(data);
                showPopup();
            }
        });
    }
    function hidePopup() {
        $("#popup").hide();
    }
    function showPopup() {
        $("#popup").show();
    }
</script>

</body>
</html>