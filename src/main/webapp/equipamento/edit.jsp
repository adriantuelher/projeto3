<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar ${nome}</title>
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
		<h1>Editar ${nome}</h1>
		<form action="/atividades/equipamentos" method="post">
	<div class="form-group col-md-6">
				<label for="nome">Nome</label>
				<input type="text" class="form-control" value="${nome}" name="nome" required />
				<input type="hidden" value="${id}" name="id" required/> 
				<label for="nome">Descrição</label>
				<input type="text" class="form-control" value="${descricao}" name="descricao" required />
					<label for="unidade">Espaço</label>
					<select class="form-control" name="id_espaco" required>
					<c:forEach items="${espacos}" var="espaco">
						<option value="${espaco.id}" ${espaco.id == id_espaco ? "selected" : ""}>${espaco.nome}</option>
					</c:forEach>
				</select>
				
				<div >
				<button type="submit" class="btn btn-outline-dark me-2" name="opcao" value="editar">Editar</button>
				<a href="/atividades"><button  type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>

</div>
				</div>
				</form>
				</div>
</body>
</html>