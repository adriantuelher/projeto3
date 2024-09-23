<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controllers.EspacoController" %>
<%@ include file="../../checkLogin.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/estilos.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<title>Editar ${nome}</title>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container">
<h1>Editar ${nome}</h1>
<form action="/atividades/espacos" method="post">
<div class="form-group col-md-6">
<input type="hidden" name="id" value="${id}"/>
<label for="Nome">Nome</label>
<input type="text" class="form-control" name="nome" value="${nome}" required/>
<select class="form-control" name="id_bloco" required>
					<c:forEach items="${blocos}" var="bloco">
						<option value="${bloco.id}" ${bloco.id == id_bloco ? "selected" : ""}>${bloco.nome}</option>
					</c:forEach>
				</select>
</div>

<div >
				<button type="submit" class="btn btn-outline-dark me-2" name="opcao" value="editar">Editar</button>
				<a href="/atividades/espaco"><button  type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>

</div>

</form>
</div>
</body>
</html>