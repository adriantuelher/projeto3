package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Bloco;
import models.Espaco;
import models.Usuario;

@WebServlet("/espacos")
public class EspacoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EspacoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        try {
            ArrayList<Espaco> espacos = index();
            ArrayList<Bloco> blocos = blocos();
            request.setAttribute("espacos", espacos);
            request.setAttribute("blocos", blocos);

            if (op == null) {
                String url = "/espaco/index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }  else {          
                if (logado.getNivel() != 1) {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                    response.sendRedirect(request.getContextPath() + "/espacos");
                    return;
                } else {
                if (op.equals("editar")) {
                String id = request.getParameter("id");
                String nome = request.getParameter("nome");
                int id_bloco = Integer.parseInt(request.getParameter("id_bloco"));
                request.setAttribute("blocos", blocos);
                request.setAttribute("id", id);
                request.setAttribute("nome", nome);
                request.setAttribute("id_bloco", id_bloco);
                RequestDispatcher rd = request.getRequestDispatcher("/espaco/editar.jsp");
                rd.forward(request, response);
            } else if (op.equals("cadastrar")){
            	request.setAttribute("blocos", blocos);
            	RequestDispatcher rd = request.getRequestDispatcher("/espaco/new.jsp");
            	rd.forward(request, response);
            }
            }
            }
            } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/espacos?status=error&message=Não foi possível retornar os espaços: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        if (logado == null || logado.getNivel() != 1) {
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
            response.sendRedirect(request.getContextPath() + "/espacos");
            return;
        }

        try {
            if (opcao.equals("cadastrar")) {
                String nome = request.getParameter("nome");
                int id_bloco = Integer.parseInt(request.getParameter("id_bloco"));
                
                boolean salvo = this.create(nome, id_bloco);

                if (salvo) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível cadastrar o espaço.");
                }
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                int id_bloco = Integer.parseInt(request.getParameter("id_bloco"));
                boolean editado = this.edit(id, nome, id_bloco);

                if (editado) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Espaço editado com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível editar o espaço.");
                }
            } else if (opcao.equals("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean excluido = this.delete(id);

                if (excluido) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Espaço excluído com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível excluir o espaço.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Ocorreu um erro: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/espacos");
    }

    public boolean create(String nome, int id_bloco) throws Exception {
        Espaco e = new Espaco(nome, id_bloco);
        return e.save();
    }

    public ArrayList<Bloco> blocos() throws Exception {
    	Bloco b = new Bloco();
    	return b.getAll();
    }
    
    public ArrayList<Espaco> index() throws Exception {
        Espaco e = new Espaco();
        return e.getAll();
    }

    public boolean edit(int id, String nome, int id_bloco) throws Exception {
        Espaco e = new Espaco(id, nome, id_bloco);
        return e.update();
    }

    public boolean delete(int id) throws Exception {
        Espaco e = new Espaco(id);
        return e.delete();
    }
}
