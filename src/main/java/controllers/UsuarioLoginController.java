package controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Usuario;

@WebServlet("/sessao")
public class UsuarioLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsuarioLoginController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("opcao");

        if (op != null && op.equals("logar")) {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost chamado!");
        
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String opcao = request.getParameter("opcao");

        if ("logar".equals(opcao)) {
            System.out.println("Usuario: " + usuario);
            System.out.println("Senha: " + senha);
            try {
                Usuario logado = new Usuario(usuario, senha).verificar();
                if (logado != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("logado", logado);
                    session.setMaxInactiveInterval(60 * 60); // Sessão expira em 1 hora

                    Cookie userCookie = new Cookie("usuarioLogado", usuario);
                    userCookie.setMaxAge(60 * 60); // Cookie expira em 1 hora
                    response.addCookie(userCookie);

                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("erro", "Usuário e/ou senha incorretos!");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                request.setAttribute("erro", "Usuário e/ou senha incorretos!");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        }
    }
}
