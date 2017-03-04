<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/select2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/noty.packaged.min.js"/>"></script>
    <link href="<c:url value="/css/dark/select2.css"/> " rel="stylesheet">
</head>
<body>
<div id="popup_page_content" style="visibility: hidden">
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
                        <td><form:input id="sector_name" maxlength="100" path="name"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="shortName">Сокращенно</form:label></td>
                        <td><form:input maxlength="10" path="shortName"/></td>
                    </tr>
                    <c:choose>
                        <c:when test="${dataType == 'division'}">
                            <tr>
                                <td><form:label path="divheadName">Начальник отдела</form:label></td>
                                <td>
                                    <form:select class="select2" path="divheadName">
                                        <form:option value="нет">---</form:option>
                                        <c:forEach var="divhead" items="${divhead}">
                                            <form:option value="${divhead.fio}">${divhead.fio}</form:option>
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
                                    <form:select id="sector_div_id" class="select2" path="division_id.id">
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
                            <form:select class="select2" path="post_id.id">
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
                            <form:select class="select2" path="zues_id.id">
                                <option disabled selected hidden></option>
                                <c:forEach var="zues" items="${zues}">
                                    <form:option value="${zues.id}">${zues.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="division_id.id">Отдел</form:label>
                            <span class="required-fild">*</span>
                        </td>
                        <td>
                            <form:select id="select-division-employer" class="select2" path="division_id.id">
                                <option disabled selected hidden></option>
                                <c:forEach var="division" items="${division}">
                                    <form:option value="${division.id}">${division.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="sector_id.id">Сектор</form:label>
                        </td>
                        <td>
                            <form:select id="select-sector-employer" class="select2" path="sector_id.id">
                                <form:option value="0">---</form:option>
                                <c:forEach var="sector" items="${sector}">
                                    <form:option value="${sector.id}">${sector.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="other">Заметка о пользователе</form:label></td>
                        <td><form:textarea cols="25" rows="3" maxlength="70" path="other"/></td>
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
                                    <form:select class="select2" path="resource_id.id">
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
                                    <form:select class="select2" path="region_id.id">
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
            <c:if test="${isused == false}">
                <tr>
                    <td style="text-align: center" colspan="2">
                        <a class="delete-btn" onclick="deleteData('${dataType}', '${data.id}')" style="cursor: pointer;">Удалить</a>
                    </td>
                </tr>
            </c:if>
        </table>
    </form:form>
</div>
<script>
    initSelect2();
    function deleteData(dataType, id) {
        $.ajax({
            type: "GET",
            url: "admin/delete_" + dataType + "/" + id + "/",
            success: function () {
                createNoty('Запись удалена', 'information');
                hidePopup();
                getCurrentData();
            },
            error: function () {
                createNoty('Произошла ошибка при удалении', 'error');
            }
        });
    }
    function postData(dataType, method) {
        if (dataType == 'sector' && $("#sector_name").val() == $("#sector_div_id option:selected").text()) {
            createNoty('Название не может совпадать с названием отдела', 'error');
        } else {
            $.ajax({
                type: "POST",
                data: $("#spring-form").serialize(),
                url: "admin/table_" + dataType + "_add/",
                error: function () {
                    createNoty('Ошибка.Повторите попытку', 'error');
                },
                success: function () {
                    if (method == 'add') {
                        createNoty('Запись добавлена', 'success');
                        getCurrentData();
                        goToLastPage();
                        setPopupContent('0', dataType);
                    }
                    else {
                        createNoty('Запись сохранена', 'success');
                        getCurrentData();
                    }
                }
            });
        }
    }
    function initSelect2() {
        $(".select2").select2({
            dropdownAutoWidth: true,
            width: "100%",
            sorter: function (data) {
                return data.sort(function (a, b) {
                    return a.text < b.text ? -1 : a.text > b.text ? 1 : 0;
                });
            }
        }).on("select2:select", function (e) {
            $('.select2-selection__rendered li.select2-selection__choice').sort(function (a, b) {
                return $(a).text() < $(b).text() ? -1 : $(a).text() > $(b).text() ? 1 : 0;
            }).prependTo('.select2-selection__rendered');
        });
    }
    function createNoty(text, type) {
        var n = noty({
            text: text,
            layout: 'bottomRight',
            theme: 'relax',
            type: type,
            timeout: 3000
        });
    }
    function setOption(name, value) {
        $("#select-sector-employer").append(new Option(name, value));
    }
    $(document).ready(function () {
        $('textarea[maxlength]').keyup(function () {
            var limit = parseInt($(this).attr('maxlength'));
            var text = $(this).val();
            var chars = text.length;
            if (chars > limit) {
                var new_text = text.substr(0, limit);
                $(this).val(new_text);
            }
        });
        document.getElementById("popup_page_content").style.visibility = "visible";
    })
</script>
</body>
</html>
