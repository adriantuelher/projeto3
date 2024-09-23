<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../checkLogin.jsp"%>
<%@ page import="models.Espaco"%>
<%@ page import="models.Evento" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Editar ${evento.nome}</title>
</head>
<body>
   <%@ include file="../navbar.jsp" %>
	<div class="container">
		<h1>Editar ${nome}</h1>
		<form action="/atividades/eventos" method="post">
			<div class="form-group col-md-6">
			<input type="hidden" name="id" value="${id}" required />
			<input type="hidden" name="id_usuario" value="${id_usuario}" required>			
				<label for="nome">Nome</label> <input type="text"
					class="form-control" name="nome" value="${nome}" required />

				<label for="tipo">Tipo</label> <input type="text"
					class="form-control" name="tipo" value="${tipo}" required />

				<label for="data_evento">Data</label> <input type="date"
					class="form-control" name="data_evento"
					value="${data_evento}" required /> <label for="hora_inicio">Horário
					de início</label> <input type="time" class="form-control"
					name="hora_inicio" value="${hora_inicio}" required /> <label
					for="hora_fim">Horário do fim</label> <input type="time"
					class="form-control" name="hora_fim" value="${hora_fim}"
					required /> <label for="espaco">Espaço</label> <select
					class="form-control" name="id_espaco" required>
					<c:forEach items="${espacos}" var="espaco">
						<option value="${espaco.id}"
							${espaco.id == id_espaco ? "selected" : ""}>${espaco.nome}</option>
					</c:forEach>
				</select>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="realizado"
						id="realizado" ${realizado ? "checked" : ""}> <label
						class="form-check-label" for="realizado">Realizado</label>
				<div >
				<button type="submit" value="editar" name="opcao" class="btn btn-primary">Editar</button>
				<a href="/atividades/eventos"><button  type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
		</div>
		</form>
		
	</div>
</body>
</html>
