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
    <title>Новая заявка</title>
    <meta charset="utf-8">
    <link href="<c:url value="/css/dark/repnote.css"/> " rel="stylesheet">
    <link href="<c:url value="/css/dark/edit_db.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<table>
    <tr>
        <td>
            <span class="td-text-gl">Пользователь:</span>
            ${employer.surname}
            ${employer.name}
            ${employer.middlename}
        </td>
        <td>
            <span class="td-text-gl">email:</span>
            ${employer.email}
        </td>
    </tr>
    <tr>
        <td>
            <span class="td-text-gl">Должность:</span>
            ${employer.post_id.name}
        </td>
        <td>
            <span class="td-text-gl">Телефон:</span>
            ${employer.telJob}
        </td>
    </tr>

    <tr>
        <td>
            <span class="td-text-gl">Область:</span>
            ${employer.zues_id.region_id.name}
        </td>
        <td>
            <span class="td-text-gl">Centrix:</span>
            ${reportingNotice.employers_id.centrix}
        </td>
    </tr>
    <tr>
        <td>
            <span class="td-text-gl">Зуес:</span>
            ${employer.zues_id.name}
        <td>
    </tr>

    <tr>
        <td>
            <span class="td-text-gl">Сектор:</span>
            ${employer.sector_id.name}
        </td>
        <td>
            <span class="td-text-gl">Отдел:</span>
            ${employer.sector_id.division_id.name}
        </td>

    </tr>
    <tr>
        <td></td>
        <td>
            <span class="td-text-gl">Начальник отдела:</span>
            ${employer.sector_id.division_id.divhead_id.fio}
        </td>
    </tr>
</table>
<form:form action="${path}/user/notice_add/" method="post" modelAttribute="data" id="notice-form">
    <p>
        <a onmousedown="return false" onclick="addResourceField()" style="cursor: pointer;">+ Добавить еще ресурс</a>
        <span onmousedown="return false" id="delete-field"></span>
    </p>
    <table style="width: auto;" id="input-fields">
        <tr id="tabletr">
            <td>1. Подресурс:</td>
            <td>
                <select name="repnotes[0].subResource_id.id">
                    <option disabled selected hidden></option>
                    <c:forEach var="subresource" items="${subresource}">
                        <option value="${subresource.id}">${subresource.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>Режим доступа:</td>
            <td>
                <select name="repnotes[0].regimeAccess_id.id">
                    <option disabled selected hidden></option>
                    <c:forEach var="regimeaccess" items="${regimeaccess}">
                        <option value="${regimeaccess.id}">${regimeaccess.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <p>
        <a onmousedown="return false" onclick="sendRepnote()" style="cursor: pointer;">Отправить заявку</a>
    </p>
    <div style="color: #e85b47; text-align:center;" id="post-error"></div>
</form:form>
<script>
    var maxFields = 10;
    var currentCount = 1;
    $("#post-error").hide();
    $("#delete-field").hide();
    $(document).ready(function () {
        $("#delete-field").append('<a class="delete-btn" onclick="deleteResourceField()" style="cursor: pointer;">Убрать</a>');
    });
    function sendRepnote() {
        var isNullable = false;
        $('select').each(function () {
            if ($(this).val() == null) {
                isNullable = true;
            }
        });
        if(!isNullable){
            document.getElementById("notice-form").submit();
        }else{
            $("#post-error").text("Заполните все поля");
            $("#post-error").show().delay(3000).hide(0);
        }
    }
    function deleteResourceField() {
        if (currentCount > 2) {
            var delVal = currentCount - 1;
            var getTrId = "#table-tr" + delVal;
            $(getTrId).remove();
            currentCount--;
        } else if (currentCount == 2) {
            var delVal = currentCount - 1;
            var getTrId = "#table-tr" + delVal;
            $(getTrId).remove();
            currentCount--;
            $("#delete-field").hide();
        }
    }
    function addResourceField() {
        if (currentCount < maxFields) {
            var subNum = currentCount + 1;
            $("#input-fields").append(
                    '<tr id="table-tr' + currentCount + '"><td>' + subNum + '. Подресурс:</td><td>' +
                    '<select name="repnotes[' + currentCount + '].subResource_id.id">' +
                    '<option disabled selected hidden></option>' +
                    '<c:forEach var="subresource" items="${subresource}">' +
                    '<option value="${subresource.id}">${subresource.name}</option></c:forEach>' +
                    '</select></td><td>Режим доступа:</td><td>' +
                    '<select name="repnotes[' + currentCount + '].regimeAccess_id.id"><option disabled selected hidden></option>' +
                    '<c:forEach var="regimeaccess" items="${regimeaccess}">' +
                    '<option value="${regimeaccess.id}">${regimeaccess.name}</option>' +
                    '</c:forEach></select></td></tr>');
            currentCount++;
            $("#delete-field").show();
        }
    }
</script>
</body>
</html>