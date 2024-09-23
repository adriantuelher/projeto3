<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../checkLogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blocoslocos</title>
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
    <h1>Controle de Blocos</h1>
    <c:choose>
        <c:when test="${empty blocos}">
            <h2>Não há blocos cadastrados.</h2>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Unidade</th>
                            <th scope="col">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${blocos}" var="bloco">
                            <tr>
                                <td>${bloco.id}</td>
                                <td>${bloco.nome}</td>
                                <td>
                                    <c:forEach items="${unidades}" var="unidade">
                                        <c:if test="${bloco.id_unidade == unidade.id}">
                                            ${unidade.nome}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <c:if test="${logado.nivel == 1}">
                                    <td>
                                        <a class="btn btn-primary"
                                            href="/atividades/blocos?id=${bloco.id}&nome=${bloco.nome}&id_unidade=${bloco.id_unidade}&opcao=editar">
                                            Editar
                                        </a>
                                        <form action="/atividades/blocos" method="post"
                                            onsubmit="return confirm('Você deseja excluir ${bloco.nome}?')"
                                            class="d-inline">
                                            <input type="hidden" name="id" value="${bloco.id}">
                                            <button class="btn btn-danger" type="submit" name="opcao" value="excluir">
                                                Excluir
                                            </button>
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
        	<a href="/atividades/blocos?opcao=cadastrar"><button type="button" class=" btn btn-outline-dark me-2">Cadastrar bloco</button></a>
        	<a href="/atividades"><button type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
        </div>
</div>
</body>
</html>