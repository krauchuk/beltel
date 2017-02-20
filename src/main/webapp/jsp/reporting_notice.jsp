<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <c:if test="${not empty reportingNotice}">
        <title>Заявка #${reportingNotice.id}</title>
    </c:if>
    <meta charset="utf-8">
    <link href="<c:url value="/css/dark/repnote.css"/> " rel="stylesheet">
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
                        ${reportingNotice.employers_id.sector_id.division_id.name}
                </td>

            </tr>
            <tr>
                <td></td>
                <td>
                    <span class="td-text-gl">Начальник отдела:</span>
                        ${reportingNotice.employers_id.sector_id.division_id.divhead_id.fio}
                </td>
            </tr>
            <sec:authorize access="hasRole('ADMIN')">
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
            </sec:authorize>
        </table>
        <table>
            <c:if test="${not empty subres}">
                <th>Подресурс</th>
                <th>Ресурс</th>
                <th>Режим доступа</th>
                <c:forEach var="subres" items="${subres}">
                    <tr>
                        <td>${subres.subResource_id.name}</td>
                        <td>${subres.subResource_id.resource_id.name}</td>
                        <td>${subres.regimeAccess_id.name}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

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
        <ul>
            <c:if test="${reportingNotice.status == false}">
                <li><a class="accept-btn" href="${path}/change_status/${reportingNotice.id}">Одобрить</a></li>
            </c:if>
            <c:if test="${reportingNotice.status == true}">
                <li><a class="unaccept-btn" href="${path}/change_status/${reportingNotice.id}">Отклонить</a></li>
                <li><a class="unaccept-btn" href="${path}/admin/get_pdf/${reportingNotice.id}" download="report_${reportingNotice.id}.pdf">Сохранить отчет</a></li>
            </c:if>

            <li><a class="delete-btn" href="${path}/delete/${reportingNotice.id}">Удалить</a></li>
        </ul>
    </sec:authorize>
</c:if>
</body>
</html>