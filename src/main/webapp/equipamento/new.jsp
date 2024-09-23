<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar equipamento</title>
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
<%@ include file="../navbar.jsp" %>
	<div class="container">
		<h1>Cadastrar equipamento</h1>
		<form action="/atividades/equipamentos" method="post">
	<div class="form-group col-md-6">
				<label for="nome">Nome</label>
				<input type="text" class="form-control" name="nome" required />
				<label for="nome">Descrição</label>
				<input type="text" class="form-control" name="descricao" required />
					<label for="unidade">Espaço</label>
					<select class="form-control" name="id_espaco" required>
					<c:forEach items="${espacos}" var="espaco">
						<option value="${espaco.id}">${espaco.nome}</option>
					</c:forEach>
				</select>
				<button type="submit" value="cadastrar" name="opcao" class="btn btn-primary">Cadastrar</button>
				<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
				</div>
				</form>
				</div>
</body>
</html>