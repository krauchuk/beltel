<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <c:if test="${not empty reportingNotice}">
        <title>Заявка #${reportingNotice.id}</title>
    </c:if>
    <meta charset="utf-8">
    <link href="<c:url value="/css/light/repnote.css"/> " rel="stylesheet">
    <link href="<c:url value="/css/light/buttons.css"/> " rel="stylesheet">
    <link href="<c:url value="/css/light/select2.css"/> " rel="stylesheet">
    <script type="text/javascript" src="<c:url value ="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/select2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value ="/js/noty.packaged.min.js"/>"></script>
</head>
<body>
<jsp:include page="menu.jsp"/>
<c:if test="${not empty reportingNotice}">
    <div class="notice-table-center">
        <p style="text-align:center">Информация по заявке #${reportingNotice.id}</p>
        <table>
            <tr>
                <td>
                    <span class="td-text-gl">Пользователь:</span>
                        ${reportingNotice.employers_id.surname}
                        ${reportingNotice.employers_id.name}
                        ${reportingNotice.employers_id.middlename}
                </td>
                <td>
                    <span class="td-text-gl">email:</span>
                        ${reportingNotice.employers_id.email}
                </td>
            </tr>
            <tr>
                <td>
                    <span class="td-text-gl">Должность:</span>
                        ${reportingNotice.employers_id.post_id.name}
                </td>
                <td>
                    <span class="td-text-gl">Телефон:</span>
                        ${reportingNotice.employers_id.telJob}
                </td>
            </tr>

            <tr>
                <td>
                    <span class="td-text-gl">Область:</span>
                        ${reportingNotice.employers_id.zues_id.region_id.name}
                </td>
                <td>
                    <span class="td-text-gl">Centrix:</span>
                        ${reportingNotice.employers_id.centrix}
                </td>
            </tr>
            <tr>
                <td>
                    <span class="td-text-gl">Зуес:</span>
                        ${reportingNotice.employers_id.zues_id.name}
                <td>
            </tr>

            <tr>
                <td>
                    <span class="td-text-gl">Сектор:</span>
                        ${reportingNotice.employers_id.sector_id.name}
                </td>
                <td>
                    <span class="td-text-gl">Отдел:</span>
                        ${reportingNotice.employers_id.division_id.name}
                </td>

            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="td-text-gl">Начальник отдела:</span>
                        ${reportingNotice.employers_id.division_id.divheadName}
                </td>
            </tr>
            <sec:authorize access="hasRole('ADMIN')">
                <c:if test="${not empty reportingNotice.employers_id.other}">
                    <tr>
                        <td colspan="2">
                            <span class="td-text-gl">Заметка о пользователе:</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                                ${reportingNotice.employers_id.other}
                        </td>
                    </tr>
                </c:if>
            </sec:authorize>
        </table>
        <table class="repnote-res-table">
            <c:if test="${not empty subres}">
                <th>Подресурс</th>
                <th>Ресурс</th>
                <th>Режим доступа</th>
                <c:if test="${reportingNotice.status == false}">
                    <th></th>
                </c:if>
                <c:forEach var="subres" items="${subres}">
                    <tr>
                        <td>${subres.subResource_id.name}</td>
                        <td>${subres.subResource_id.resource_id.name}</td>
                        <td>${subres.regimeAccess_id.name}</td>
                        <c:if test="${reportingNotice.status == false}">
                            <td><a class="delete-btn"
                                   href="${path}/detele_${reportingNotice.id}_subres/${subres.id}">Удалить</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <c:if test="${reportingNotice.status == false}">
            <table>
                <th style="text-align: center" colspan="5">Добавить ресурс</th>
                <tr>
                    <form:form action="${path}/notice_add_subres/${reportingNotice.id}" method="post"
                               modelAttribute="newSubres" id="newSubres-form">
                        <td>Подресурс:</td>
                        <td>
                            <select class="select2" name="subResource_id.id">
                                <option disabled selected hidden></option>
                                <c:forEach var="resource" items="${resource}">
                                    <optgroup label="${resource.name}">
                                        <c:forEach var="subresource" items="${subresource}">
                                            <c:if test="${resource.name == subresource.resource_id.name}">
                                                <option value="${subresource.id}">${subresource.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </optgroup>
                                </c:forEach>
                            </select>
                        </td>
                        <td>Режим доступа:</td>
                        <td>
                            <select class="select2" name="regimeAccess_id.id">
                                <option disabled selected hidden></option>
                                <c:forEach var="regimeaccess" items="${regimeaccess}">
                                    <option value="${regimeaccess.id}">${regimeaccess.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <a onmousedown="return false" onclick="sendRepnote()" class="add-data-btn">Добавить
                                ресурс</a>
                        </td>
                    </form:form>
                </tr>
            </table>
        </c:if>
        <table>
            <tr>
                <td>
                    <span class="td-text-gl">ip:</span>
                        ${reportingNotice.ipAddr}
                </td>
                <td>
                    <span class="td-text-gl">Имя компьютера:</span>
                        ${reportingNotice.userPcName}
                </td>
            </tr>

            <tr>
                <td>
                    <span class="td-text-gl">Время отправки:</span>
                        ${reportingNotice.dateSet}
                </td>
                <td>
                    <span class="td-text-gl">Время подтверждения:</span>
                        ${reportingNotice.dateDone}
                </td>
            </tr>
            <tr>
                <td>
                    <span class="td-text-gl">Статус:</span>
                    <c:if test="${reportingNotice.status == true}">
                        <span class="notice-status-true">&#10004;</span>
                    </c:if>
                    <c:if test="${reportingNotice.status == false}">
                        <span class="notice-status-false">&#10006;</span>
                    </c:if>
                </td>
                <td></td>
            </tr>
        </table>
    </div>
    <sec:authorize access="hasRole('ADMIN')">
        <ul class="menu-bottom-ul">
            <c:if test="${reportingNotice.status == false}">
                <li class="menu-li"><a class="accept-btn"
                                       href="${path}/change_status/${reportingNotice.id}">Одобрить</a></li>
            </c:if>
            <c:if test="${reportingNotice.status == true}">
                <li class="menu-li"><a class="unaccept-btn"
                                       href="${path}/change_status/${reportingNotice.id}">Отклонить</a></li>
                <li class="menu-li"><a class="unaccept-btn" href="${path}/get_pdf/${reportingNotice.id}"
                                       download="report_${reportingNotice.id}.pdf">Сохранить отчет</a></li>
            </c:if>

            <li class="menu-li"><a class="delete-btn" href="${path}/delete/${reportingNotice.id}">Удалить</a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('USER')">
        <c:if test="${reportingNotice.status == true}">
            <ul class="menu-bottom-ul">
                <li class="menu-li"><a class="unaccept-btn" href="${path}/get_pdf/${reportingNotice.id}"
                                       download="report_${reportingNotice.id}.pdf">Сохранить отчет</a></li>
            </ul>
        </c:if>
    </sec:authorize>
</c:if>
<script>
    select2Init();
    function select2Init() {
        $('.select2').select2({
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
    function sendRepnote() {
        var isNullable = false;
        $('select').each(function () {
            if ($(this).val() == null) {
                isNullable = true;
            }
        });
        if (!isNullable) {
            document.getElementById("newSubres-form").submit();
        } else {
            var n = noty({
                text: 'Заполните все поля',
                layout: 'bottomRight',
                theme: 'relax',
                type: 'error',
                timeout: 3000
            });
        }
    }
</script>
</body>
</html>