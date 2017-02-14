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
    <link href="<c:url value="/css/dark/edit_db.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/dark/popup.css"/> " rel="stylesheet">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<p style="text-align:center">Редактирование таблиц БД</p>
<div style="background-color: #2B2B2B; padding: 3px;">
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
</div>

<p></p>

<div style="float:left;width: 100%;" id="get_table"></div>

<div class="popup" id="popup">
    <div class="popup-content" id="popup-content">
    </div>
</div>

<script>
    hidePopup();
    hideTable();
    $(document).ready(function () {
        $("#popup-content").append('<a class="close-btn" onclick="hidePopup()">&#10006;</a><div id="popup-data"></div>');
    });
    $("#select_table_name").on("change", function () {
        getCurrentData();
    });
    function getCurrentData() {
        $(document).ready(function () {
            var selectTableUrl = $("select#select_table_name").val();
            hideTable();
            $.ajax({
                type: "GET",
                url: "admin/" + selectTableUrl,
                success: function (data) {
                    $('#get_table').html(data);
                    $(document).ready(function () {
                        showTable();
                    });
                }
            });
        });
    }
    function goToLastPage() {
        $(document).ready(function () {
            tableDT.fnPageChange("last", true);
        });
    }
    function hidePopup() {
        $("#popup").hide();
    }
    function showPopup() {
        $("#popup").show();
    }
    function hideTable() {
        $("#get_table").hide();
    }
    function showTable() {
        $("#get_table").show();
    }
    function setPopupContent(dataID, dataType) {
        $.ajax({
            type: "GET",
            url: "admin/table_" + dataType + "_edit/" + dataID,
            success: function (data) {
                $("#popup-data").html(data);
                $(document).ready(function () {
                    showPopup();
                });
            }
        });
    }
</script>

</body>
</html>