<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Usuario"%>
<%@ page import="controllers.UsuarioController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../checkLogin.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuários</title> 
<link rel="stylesheet" href="/css/estilos.css">
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
	
		<c:choose>
			<c:when test="${empty usuarios}">
				<h2>Não há usuários cadastrados.</h2>
			</c:when>
			<c:otherwise>
				<div class="table-responsive" style="
    margin-top: 25px;">
					<table class="table table-striped table-hover">
						<thead class="table-dark">
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Usuário</th>
								<th scope="col" class="d-none d-md-table-cell">Email</th>
								<th scope="col">Nome</th>
								<th scope="col" class="d-none d-lg-table-cell">Tipo</th>
								<th scope="col" class="d-none d-lg-table-cell">Nível</th>
								<th scope="col">Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<td>${usuario.id}</td>
									<td>${usuario.usuario}</td>
									<td class="d-none d-md-table-cell">${usuario.email}</td>
									<td>${usuario.nome}</td>
									<td class="d-none d-lg-table-cell">${usuario.tipo}</td>
									<td class="d-none d-lg-table-cell">${usuario.nivel}</td>
									<c:if test="${logado.nivel == 1 || logado.id == usuario.id}">
									<c:if test="${usuario.nivel != 1}">
										<td><a class="btn btn-primary"
											href="/atividades/usuarios?id=${usuario.id}&usuario=${usuario.usuario}&senha=${usuario.senha}&email=${usuario.email}&nome=${usuario.nome}&tipo=${usuario.tipo}&opcao=editar">Editar</a>
											<form action="/atividades/usuarios" method="post"
												onsubmit="return confirm('Você deseja excluir ${usuario.usuario}?')"
												class="d-inline">
												<input type="hidden" name="id" value="${usuario.id}">
												<button class="btn btn-danger" type="submit" name="opcao"
													value="excluir">Excluir</button>
											</form></td>
									</c:if>
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
        	<a href="/atividades/usuarios/new.jsp"><button type="button" class=" btn btn-outline-dark me-2">Cadastrar usuário</button></a>
        	<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
        </div>
</body>
</html>