<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Edit meal</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}">index</a>&nbsp;|&nbsp;<a href="users">userList</a>&nbsp;|&nbsp;<a href="meals">mealList</a>
        <h2>Edit meal</h2>
        <form method="POST" action="${pageContext.request.contextPath}/meals?id=${userMeal.id}&operation=update">
            <table>
                <tbody>
                <tr>
                    <td>date:<input type="datetime-local" name="date" value="${userMeal.dateTime}"/></td>
                    <td>desc:<input type="text" name="description" value="${userMeal.description}"/></td>
                    <td>calories:<input type="number" name="calories" value="${userMeal.calories}"/></td>
                    <td>id:<input type="number" name="calories" value="${userMeal.id}"/></td>
                    <td><input type="text" name="id" value="${userMeal.id}" hidden/><input type="submit" value="update" /></td>
                </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
