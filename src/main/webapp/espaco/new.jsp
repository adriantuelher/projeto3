<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../checkLogin.jsp" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/eventos/css/estilos.css">
		<title>Cadastrar espaço</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
			crossorigin="anonymous"></script>
	</head>

	<body>
	
	<%@ include file="../navbar.jsp" %>
	
	
	
		<div class="container" style="margin-top: 25px;">
			<form action="/atividades/espacos" method="post">
				<div class="form-group col-md-6">
					<label for="Nome">Nome</label>
					<input type="text" class="form-control" placeholder="Digite o nome do espaço" name="nome" required>
				</div>
				<div >
				<button style="margin-top: 25px;" type="submit" class="btn btn-primary" name="opcao" value="cadastrar">Cadastrar</button>
				<a href="/atividades/espaco"><button style="margin-top: 25px;" type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
				</div>
			</form>
		</div>
	</body>

	</html>