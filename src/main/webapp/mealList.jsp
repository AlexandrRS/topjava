<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {color: green;}
        .exceeded {color: red;}
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>User select section</h3>
    <form action="meals?action=setUserId" method="post">
        <p><select size="1" name="userId">
            <option disabled>Выберите пользователя</option>
            <option value="1">Alex</option>
            <option value="2">Kat</option>
        </select></p>
        <p><input type="submit" value="Отправить"></p>
    </form>
    <h3>Filter section</h3>
    <form method="post" action="${pageContext.request.contextPath}/meals?action=doFilter">
        <table>
            <tr>
                <td>Дата от: <input type="date" value="${filter.dateFrom}" name="dateFrom"></td><td>до: <input type="date" value="${filter.dateTo}" name="dateTo"></td>
            </tr>
            <tr>
                <td>Время от: <input type="time" value="${filter.timeFrom}" name="timeFrom"></td><td>до: <input type="time" value="${filter.timeTo}" name="timeTo"></td>
            </tr>
        </table>
        <button type="submit">apply</button>
        <button type="reset">reset</button>
    </form>
    <h3>Meal list</h3>
    <a href="meals?action=create">Add Meal</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${mealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                   <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                   <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
                    <%=TimeUtil.toString(meal.getDateTime())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>