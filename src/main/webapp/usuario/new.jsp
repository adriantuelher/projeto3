	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% String erro=(String) request.getAttribute("erro"); %>
        <%@ include file="../checkLogin.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Cadastrar usuário</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                    crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
                    crossorigin="anonymous"></script>
                <script>
                    function validatePasswords() {
                        var password = document.getElementsByName("senha1")[0];
                        var confirmPassword = document.getElementsByName("senha2")[0];
                        if (password.value !== confirmPassword.value) {
                            confirmPassword.setCustomValidity("As senhas não coincidem");
                        } else {
                            confirmPassword.setCustomValidity("");
                        }
                    }
                </script>
                <link rel="stylesheet" href="/eventos/css/estilos.css">
            </head>

            <body>
            
            <%@ include file="../navbar.jsp" %>
            
            
            
                    <div class="container" style="
    margin-top: 25px;">
                        <form action="/atividades/usuarios" method="post">
                            <div class="form-group col-md-6">
                                <label for="user">User</label>
                                <input type="text" class="form-control" name="usuario" required />
                                <label for="senha">Senha</label>
                                <input type="password" class="form-control" name="senha1" required
                                    oninput="validatePasswords()" />
                                <label for="senha">Confirmar Senha</label>
                                <input type="password" class="form-control" name="senha2" required
                                    oninput="validatePasswords()" />
                                <p class="text-danger">
                                    <% if(erro !=null) out.println(erro); %>
                                </p>
                                <label for="email">E-mail</label>
                                <input type="text" class="form-control" name="email" required />
                                <label for="nome">Nome</label>
                                <input type="text" class="form-control" name="nome" required />
                                <label for="tipo">Tipo de usuário</label>
                                <select class="form-control" name="tipo">
                                    <option value="Aluno">Aluno</option>
                                    <option value="Professor">Professor</option>
                                    <option value="TAE">TAE</option>
                                </select>
                            </div>
                            
                            <div >
				<button class="btn btn-outline-dark me-2" type="submit" name="opcao"
                                value="cadastrar" style="margin-top: 25px;">Cadastrar</button>
				<a href="/atividades/usuario"><button style="margin-top: 25px;" type="button" class=" btn btn-outline-dark me-2">Voltar</button></a>
				</div>
                        </form>
                    </div>
            </body>

            </html>