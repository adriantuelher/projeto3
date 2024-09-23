<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.Equipamento"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../checkLogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipamentos do Espaço</title>
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
        <h1>Equipamentos do Espaço</h1>
        <c:choose>
            <c:when test="${empty equipamentos}">
                <h2>Não há equipamentos cadastrados para este espaço.</h2>
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
                            <c:forEach items="${equipamentos}" var="equipamento">
                                <tr>
                                    <td>${equipamento.id}</td>
                                    <td>${equipamento.nome}</td>
                                    <td>${equipamento.descricao}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
        
        <div >
				
				<a href="/atividades/equipamentos"><button  type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>

</div>
    </div>
</body>
</html>
