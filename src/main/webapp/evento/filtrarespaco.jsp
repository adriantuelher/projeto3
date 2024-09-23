<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eventos por Espaço</title>
    <link rel="stylesheet" href="/eventos/css/estilos.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h1>Eventos do Espaço</h1>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th class="d-none d-md-table-cell">Tipo</th>
                        <th>Data</th>
                        <th class="d-none d-md-table-cell">Hora Início</th>
                        <th class="d-none d-md-table-cell">Hora Fim</th>
                        <th class="d-none d-md-table-cell">Realizado</th>
                        <th class="d-none d-md-table-cell">Usuário</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${eventos}" var="evento">
                        <tr>
                            <td>${evento.id}</td>
                            <td>${evento.nome}</td>
                            <td class="d-none d-md-table-cell">${evento.tipo}</td>
                            <td>${evento.data_evento}</td>
                            <td class="d-none d-md-table-cell">${evento.hora_inicio}</td>
                            <td class="d-none d-md-table-cell">${evento.hora_fim}</td>
                            <td>${evento.realizado ? 'Sim' : 'Não'}</td>
                            <td class="d-none d-md-table-cell">
                                <c:forEach items="${usuarios}" var="usuario">
                                    <c:if test="${usuario.id == evento.id_usuario}">
                                        <a href="/atividades/eventos?opcao=filtrarUsers&usuarioId=${usuario.id}">
                                            ${usuario.nome}
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
        	<a href="/atividades/eventos?opcao=cadastrar"><button type="button" class=" btn btn-outline-dark me-2">Cadastrar evento</button></a>
        	<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
        	<p><a href="/atividades/eventos" class="btn btn-secondary">Todos os eventos</a></p>
        </div>
    </div>
</body>
</html>
