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
		<form action="/atividades/blocos" method="post">
	<div class="form-group col-md-6">
				<label for="nome">Nome</label>
				<input type="hidden" value="${id}" name="id" required/>
				<input type="text" class="form-control" value="${nome}" name="nome" />
					<label for="unidade">Bloco</label>
					<select class="form-control" name="id_unidade" required>
					<c:forEach items="${unidades}" var="unidade">
						<option value="${unidade.id}" ${unidade.id == id_unidade ? "selected" : ""}>${unidade.nome}</option>
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