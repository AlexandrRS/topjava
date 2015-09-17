<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Meal list</title>
    </head>
    <body>
        <a href="/topjava">index</a>&nbsp;|&nbsp;<a href="users">userList</a>&nbsp;|&nbsp;<a href="meals">mealList</a>
        <h2>Meal list</h2>
        <form method="POST" commandName="addMeal" action="${pageContext.request.contextPath}/meals">
            <table>
                <tbody>
                <tr>
                    <td>date:<input type="datetime-local" name="date"></td>
                    <td>desc:<input type="text" name="description"/></td>
                    <td>calories:<input type="number" name="calories"/></td>
                    <td><input type="submit" value="add" /></td>
                </tr>
                </tbody>
            </table>
        </form>
        <table>
            <thead align="left">
                <tr>
                    <th width="10%">дата</th>
                    <th width="15%">время</th>
                    <th width="10%">описание</th>
                    <th width="10%">калории</th>
                    <th width="10%">перебор?</th>
                    <th width="10%">операции</th>
                </tr>
            </thead>
                <tbody>
                    <c:forEach items="${mealList}" var="meal">
                        <c:choose>
                            <c:when test="${meal.exceed eq 'true'}">
                                <tr style="color: red">
                            </c:when>
                            <c:otherwise>
                                <tr style="color: green">
                            </c:otherwise>
                        </c:choose>
                            <td>${meal.localDate}</td>
                            <td>${meal.localTime}</td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td>${meal.exceed}</td>
                            <td><a href="${pageContext.request.contextPath}/${meal.id}/edit.html">edit</a>&nbsp;|
                                <a href="${pageContext.request.contextPath}/${meal.id}/delete.html">delete</a><br/></td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
    </body>
</html>
