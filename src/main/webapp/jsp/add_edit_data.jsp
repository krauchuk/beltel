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
</head>
<body>
<form:form commandName="data" id="spring-form">
    <table>
        <c:if test="${not empty data.name}">
            <tr>
                <td><form:label path="id">id</form:label></td>
                <td>
                    <form:input path="id" readonly="true" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <c:choose>
            <c:when test="${dataType == 'division'
                or dataType == 'sector'}">
                <tr>
                    <td>
                        <form:label path="name">Название</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="100" path="name" /></td>
                </tr>
                <tr>
                    <td><form:label path="shortName">Сокращенно</form:label></td>
                    <td><form:input maxlength="10" path="shortName"/></td>
                </tr>
                <c:choose>
                    <c:when test="${dataType == 'division'}">
                        <tr>
                            <td><form:label path="divhead_id.id">Начальник отдела</form:label></td>
                            <td>
                                <form:select path="divhead_id.id">
                                    <form:option value="0">---</form:option>
                                    <c:forEach var="divhead" items="${divhead}">
                                        <form:option value="${divhead.id}">${divhead.fio}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${dataType == 'sector'}">
                        <tr>
                            <td>
                                <form:label path="division_id.id">Отдел</form:label>
                                <span class="required-fild">*</span>
                            </td>
                            <td>
                                <form:select path="division_id.id">
                                    <option disabled selected hidden></option>
                                    <c:forEach var="division" items="${division}">
                                        <form:option value="${division.id}">${division.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:when>
            <c:when test="${dataType == 'employer'}">
                <tr>
                    <td>
                        <form:label path="centrix">Centrix</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="4" path="centrix"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="fio">Фио</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input path="fio"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="surname">Фамилия</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="55" path="surname"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="name">Имя</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="30" path="name"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="middlename">Отчество</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="40" path="middlename"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="telJob">Телефон</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="15" path="telJob"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">email</form:label></td>
                    <td><form:input maxlength="50" path="email"/></td>
                </tr>
                <tr>
                    <td>
                        <form:label path="post_id.id">Должность</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td>
                        <form:select path="post_id.id">
                            <option disabled selected hidden></option>
                            <c:forEach var="post" items="${post}">
                                <form:option value="${post.id}">${post.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="zues_id.id">ЗУЕС</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td>
                        <form:select path="zues_id.id">
                            <option disabled selected hidden></option>
                            <c:forEach var="zues" items="${zues}">
                                <form:option value="${zues.id}">${zues.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="sector_id.id">Сектор</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td>
                        <form:select path="sector_id.id">
                            <<option disabled selected hidden></option>
                            <c:forEach var="sector" items="${sector}">
                                <form:option value="${sector.id}">${sector.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="other">Заметка о пользователе</form:label></td>
                    <td><form:textarea path="other"/></td>
                </tr>
            </c:when>
            <c:when test="${dataType == 'post'
                or dataType == 'regimeaccess'
                or dataType == 'resource'
                or dataType == 'zues'
                or dataType == 'subresource'}">
                <tr>
                    <td>
                        <form:label path="name">Название</form:label>
                        <span class="required-fild">*</span>
                    </td>
                    <td><form:input maxlength="100" path="name"/></td>
                </tr>
                <c:choose>
                    <c:when test="${dataType == 'subresource'}">
                        <tr>
                            <td>
                                <form:label path="resource_id.id">Ресурс</form:label>
                                <span class="required-fild">*</span>
                            </td>
                            <td>
                                <form:select path="resource_id.id">
                                    <option disabled selected hidden></option>
                                    <c:forEach var="resource" items="${resource}">
                                        <form:option value="${resource.id}">${resource.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${dataType == 'zues'}">
                        <tr>
                            <td>
                                <form:label path="region_id">Область</form:label>
                                <span class="required-fild">*</span>
                            </td>
                            <td>
                                <form:select path="region_id.id">
                                    <option disabled selected hidden></option>
                                    <c:forEach var="region" items="${region}">
                                        <form:option value="${region.id}">${region.name}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:when>
            <c:otherwise>
                Ошибка. Данный dataType отсутствует.
            </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="2" class="required-fild">* - поля обязатены к заполнению</td>
        </tr>
        <tr>
            <td style="text-align: center" colspan="2">
                <c:if test="${not empty data.name}">
                    <a onclick="postData('${dataType}', 'edit')" style="cursor: pointer;">Сохранить</a>
                </c:if>
                <c:if test="${empty data.name}">
                    <a onclick="postData('${dataType}', 'add')" style="cursor: pointer;">Добавить</a>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<p>
<div style="color: #92C157; text-align:center;" id="post-result"></div>
<div style="color: #e85b47; text-align:center;" id="post-error"></div>
</p>
<script>
    $("#post-result").hide();
    $("#post-error").hide();
    function postData(dataType, method) {
            $.ajax({
                type: "POST",
                data: $("#spring-form").serialize(),
                url: "admin/table_" + dataType + "_add/",
                error: function () {
                    $("#post-error").text("Ошибка.Повторите попытку");
                    $("#post-error").show().delay(3000).hide(0);
                },
                success: function () {
                    if(method == 'add'){
                        $("#post-result").text("Запись добавлена");
                        $("#spring-form")[0].reset();
                        getCurrentData();
                        goToLastPage();
                    }
                    else{
                        $("#post-result").text("Запись сохранена");
                        getCurrentData();
                    }
                    $("#post-error").hide();
                    $("#post-result").show(0).delay(3000).hide(0);
                }
            });
    }
</script>
</body>
</html>
