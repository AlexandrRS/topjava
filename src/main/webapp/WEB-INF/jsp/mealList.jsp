<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h2><a href="/topjava">Home</a></h2>
    <h3><fmt:message key="app.mealList"/></h3>
    <form method="post" action="meals?action=filter">
        <dl>
            <dt><fmt:message key="app.filterFromDate"/>:</dt>
            <dd><input type="date" name="startDate"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="app.filterToDate"/>:</dt>
            <dd><input type="date" name="endDate"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="app.filterFromTime"/>:</dt>
            <dd><input type="time" name="startTime"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="app.filterToTime"/>:</dt>
            <dd><input type="time" name="endTime"></dd>
        </dl>
        <button type="submit"><fmt:message key="app.filter"/></button>
    </form>
    <hr>
    <a href="meals/create"><fmt:message key="app.addmeal"/></a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="app.date"/></th>
            <th><fmt:message key="app.description"/></th>
            <th><fmt:message key="app.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                   <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                   <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
                    <%=TimeUtil.toString(meal.getDateTime())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals/update?id=${meal.id}"><fmt:message key="app.update"/></a></td>
                <td><a href="meals/delete?id=${meal.id}"><fmt:message key="app.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>