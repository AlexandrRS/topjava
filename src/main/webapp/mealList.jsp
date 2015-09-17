<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Meal list</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}">index</a>&nbsp;|&nbsp;<a href="users">userList</a>&nbsp;|&nbsp;<a href="meals">mealList</a>
        <h2>Meal list</h2>
        <form method="POST" action="${pageContext.request.contextPath}/meals?operation=add">
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
                    <th width="10%">описание</th>
                    <th width="5%">калории</th>
                    <th width="5%">перебор?</th>
                    <th width="5%"></th>
                    <th width="5%"></th>
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
                            <td>${meal.dateTime}</td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td>${meal.exceed}</td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/meals?id=${meal.id}&operation=edit">
                                    <input type="text" name="id" value="${meal.id}" hidden/>
                                    <input type="submit" value="edit" />
                                </form>
                            </td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/meals?id=${meal.id}&operation=del">
                                    <input type="text" name="id" value="${meal.id}" hidden/>
                                    <input type="submit" value="delete" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
    </body>
</html>
