<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="models.Usuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${sessionScope.logado == null}">
        <c:redirect url="login.jsp" />
    </c:when>
    <c:otherwise>
        <%
            Usuario logado = (Usuario) session.getAttribute("logado");
        %>
    </c:otherwise>
</c:choose>
