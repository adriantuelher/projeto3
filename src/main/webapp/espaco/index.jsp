<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Espaco"%>
<%@ page import="controllers.EspacoController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../checkLogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Espaços do IF</title> 
<link rel="stylesheet" href="/eventos/css/estilos.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
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


	<div class="container" style="margin-top: 25px;">
		<c:choose>
			<c:when test="${empty espacos}">
				<h2>Não há espaços cadastrados.</h2>
			</c:when>
			<c:otherwise>
				<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${espacos}" var="espaco">
                <tr>
                    <td>${espaco.id}</td>
                    <td>${espaco.nome}</td>
                    <c:if test="${logado.nivel == 1}">
                        <td>
                            <a class="btn btn-primary" href="/atividades/espacos?id=${espaco.id}&nome=${espaco.nome}&id_bloco=${espaco.id_bloco}&opcao=editar">Editar</a>
                            <form action="/atividades/espacos" method="post" onsubmit="return confirm('Você deseja excluir ${espaco.nome}?')" class="d-inline">
                                <input type="hidden" name="id" value="${espaco.id}">
                                <button class="btn btn-danger" type="submit" name="opcao" value="excluir">Excluir</button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div>
        	<a href="/atividades/espaco/new.jsp"><button type="button" class=" btn btn-outline-dark me-2">Cadastrar espaços</button></a>
        	<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
        </div>
</body>
</html>