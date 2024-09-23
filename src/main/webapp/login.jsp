<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/logar.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>

    <div class="page">
        <form action="/atividades/sessao" class="formLogin" method="POST" enctype="application/x-www-form-urlencoded">
            <h1>Login</h1>
            <p>Digite os seus dados de acesso.</p>
            <label for="usuario">Usuário</label>
            <input type="text" name="usuario" placeholder="Usuário" required/>
            <label for="password">Senha</label>
            <input type="password" name="senha" placeholder="Senha" required/>
            <button type="submit" name="opcao" value="logar" class="btn btn-primary">Login</button>
            <p><c:if test="${erro != null}">
                ${erro}
            </c:if></p>
            <p><a href="/atividades/usuarios/new.jsp">Não tem login?! Cadastre-se!</a></p>
        </form>
    </div>
</body>
</html>
