<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Eventos IF</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/atividades">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="/atividades/blocos">Blocos</a></li>
                   <li class="nav-item"><a class="nav-link" href="/atividades/equipamentos">Equipamentos</a></li>
                <li class="nav-item"><a class="nav-link" href="/atividades/espacos">Espaços</a></li>
                <li class="nav-item"><a class="nav-link" href="/atividades/eventos">Eventos</a></li>
                <li class="nav-item"><a class="nav-link" href="/atividades/unidades">Unidades</a></li>
                <c:if test="${logado.getNivel() == 1}"> 
                <li class="nav-item"><a class="nav-link" href="/atividades/usuarios">Usuários</a></li>                    </c:if>
                   <li class="nav-item"><a class="nav-link" href="/atividades/vegetais">Vegetais</a></li>
                   <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Minha conta</a>
                    <ul class="dropdown-menu"><c:if test="${logado.getNivel() != 1}">
                        <li><a class="dropdown-item" href="/atividades/usuarios?id=${logado.id}&usuario=${logado.usuario}&senha=${logado.senha}&email=${logado.email}&nome=${logado.nome}&tipo=${logado.tipo}&opcao=editar">Editar perfil</a></li>                     
                        <li>
                            <form action="/atividades/usuarios" method="post" onsubmit="return confirm('Você deseja excluir sua conta, ${logado.usuario}?')">
                                <input type="hidden" name="id" value="${logado.id}">
                                <button class="dropdown-item" type="submit" name="opcao" value="excluir">Excluir conta</button>
                            </form>
                        </li>
                                        </c:if>
                        
                        <li><a class="dropdown-item" href="/atividades/logout">Sair</a></li>
                    </ul>
                </li>
                            </ul>
        </div>
    </div>
</nav>
