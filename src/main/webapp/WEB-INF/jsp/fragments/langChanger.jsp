<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:choose>
    <c:when test="${pageContext.response.locale eq 'ru'}">
        <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en" class="navbar-brand">
    </c:when>
    <c:when test="${pageContext.response.locale eq 'en'}">
        <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ru" class="navbar-brand">
    </c:when>
</c:choose>
<fmt:message key="common.language"/></a>