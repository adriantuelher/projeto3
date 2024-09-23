<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Vegetal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../checkLogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Controle de Vegetais</title>
<link rel="stylesheet" href="/atividades/css/estilos.css">
    <link rel="stylesheet" href="/eventos/css/estilos.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</head>
<body>
    <script>
    <c:if test="${not empty sessionScope.message}">
        alert("${sessionScope.message}");
        <c:remove var="status" scope="session"/>
        <c:remove var="message" scope="session"/>
    </c:if>
    </script>
    <%@ include file="../navbar.jsp" %>
    <div class="container">
<c:if test="${not empty vegetais}">
    <c:forEach items="${espacos}" var="espaco">
        <c:if test="${espaco.id == vegetal.id_espaco}">
            <h1>Vegetais em: ${espaco.nome}</h1>
        </c:if>
    </c:forEach>
</c:if>
        <c:choose>
            <c:when test="${empty vegetais}">
                <h2>Não há vegetais cadastrados.</h2>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Descrição</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${vegetais}" var="vegetal">
                                <tr>
                                    <td>${vegetal.id}</td>
                                    <td>${vegetal.nome}</td>
                                    <td>${vegetal.descricao}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
        <div >
				
				<a href="/atividades/vegetais"><button  type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>

</div>
    </div>
</body>
</html>
