<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../checkLogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eventos</title>
<link rel="stylesheet" href="/atividades/css/estilos.css">
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
	<div class="container">
		<h1>Controle de Eventos</h1>
		<c:choose>
			<c:when test="${empty eventos}">
				<h2>Não há eventos cadastrados.</h2>
			</c:when>
			<c:otherwise>
				<div class="table-responsive">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th scope="col" class="d-none d-md-table-cell">ID</th>
                <th scope="col">Nome</th>
                <th scope="col" class="d-none d-md-table-cell">Tipo</th>
                <th scope="col" class="d-none d-md-table-cell">Data</th>
                <th scope="col" class="d-none d-md-table-cell">Hora início</th>
                <th scope="col" class="d-none d-md-table-cell">Hora término</th>
                <th scope="col" class="d-none d-md-table-cell">Ocorrido</th>
                <th scope="col">Local</th>
                <th scope="col">Organizador</th>
                <th scope="col" class="d-none d-md-table-cell">Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${eventos}" var="evento">
                <tr>
                    <td class="d-none d-md-table-cell">${evento.id}</td>
                    <td>${evento.nome}</td>
                    <td class="d-none d-md-table-cell">${evento.tipo}</td>
<td class="d-none d-md-table-cell">
    <a href="/atividades/eventos?opcao=filtrarPorData&data=${evento.data_evento}">${evento.data_evento}</a>
</td>                    <td class="d-none d-md-table-cell">${evento.hora_inicio}</td>
                    <td class="d-none d-md-table-cell">${evento.hora_fim}</td>
                    <td class="d-none d-md-table-cell">${evento.realizado ? 'Sim' : 'Não'}</td>
                    <td>
                        <c:forEach items="${espacos}" var="espaco">
                            <c:if test="${espaco.id == evento.id_espaco}"><a href="/atividades/eventos?opcao=filtrarEspacos&espacoId=${espaco.id}">
                                ${espaco.nome}</a>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${usuarios}" var="usuario">
                        <c:if test="${usuario.id == evento.id_usuario}"><a href="/atividades/eventos?opcao=filtrarUsers&usuarioId=${usuario.id}">
                                ${usuario.nome}</a>
                            </c:if>
                        </c:forEach>
                    </td>
                    <c:if test="${evento.id_usuario == logado.id || logado.nivel == 1}">
                        <td>
                            <a class="btn btn-primary"
                                href="/atividades/eventos?id=${evento.id}&nome=${evento.nome}&data_evento=${evento.data_evento}&hora_inicio=${evento.hora_inicio}&hora_fim=${evento.hora_fim}&realizado=${evento.realizado}&id_espaco=${evento.id_espaco}&id_usuario=${evento.id_usuario}&opcao=editar">Editar</a>
                            <form action="/atividades/eventos" method="post"
                                onsubmit="return confirm('Você deseja excluir ${evento.nome}?')"
                                class="d-inline">
                                <input type="hidden" name="id" value="${evento.id}">
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
		<div>
        	<a href="/atividades/eventos?opcao=cadastrar"><button type="button" class=" btn btn-outline-dark me-2">Cadastrar evento</button></a>
        	<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
        	<p><a href="/atividades/eventos" class="btn btn-secondary">Todos os eventos</a></p>
        </div>
	</div>
</body>
</html>
