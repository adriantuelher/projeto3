<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page import="models.Usuario" %>
            <%@ include file="checkLogin.jsp" %>
                <!DOCTYPE html>
				<html lang="pt-br">

                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet"
                        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                        crossorigin="anonymous">
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                        crossorigin="anonymous"></script>
                    <link rel="stylesheet" href=/atividades/css/estilos.css">
                    <link rel='stylesheet' href='css/bootstrap/bootstrap.min.css' />
                    <title>Registro de Eventos do IF</title>
                </head>

                <body>
                
                <%@ include file="../navbar.jsp" %>
                
                		<div class="d-flex flex-column flex-md-row p-4 gap-4 py-md-5 align-items-center justify-content-center">
  <div class="list-group list-group-checkable d-grid gap-2 border-0">
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/eventos">Eventos</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir eventos</span>
    </label>

    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/espacos">Espaços</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir espaços</span>
    </label>

<c:if test="${logado.nivel == 1}">
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/usuarios">Usuários</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir usuarios</span>
    </label>
    </c:if>
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/blocos">Blocos</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir blocos</span>
    </label>
    
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
    	<a href="/atividades/unidades">Unidades</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir unidades</span>
    </label>
    
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/equipamentos">Equipamentos</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir equipamentos</span>
    </label>
    
    <label class="list-group-item rounded-3 py-3 " for="listGroupCheckableRadios1">
      <a href="/atividades/vegetais">Vegetais</a>
      <span class="d-block small opacity-50">Cadastrar, editar ou excluir vegetais</span>
    </label>
  </div>
</div>
                             
                </body>

                </html>