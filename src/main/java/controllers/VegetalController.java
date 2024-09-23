package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vegetal;
import models.Espaco;
import models.Usuario;

@WebServlet("/vegetais")
public class VegetalController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VegetalController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        try {
            ArrayList<Vegetal> vegetais = index();
            ArrayList<Espaco> espacos = espacos();

            request.setAttribute("espacos", espacos);
            request.setAttribute("vegetais", vegetais);

            if (op == null) {
                String url = "/vegetal/index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
          
            } else if (op.equals("cadastrar")) {
                if (logado.getNivel() != 1) {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                    response.sendRedirect(request.getContextPath() + "/vegetais");
                    return;
                }
                request.setAttribute("espacos", espacos);
                RequestDispatcher rd = request.getRequestDispatcher("/vegetal/new.jsp");
                rd.forward(request, response);
           
            } else if (op.equals("editar")) {
                if (logado.getNivel() != 1) {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                    response.sendRedirect(request.getContextPath() + "/vegetais");
                    return;
                }
                String id = request.getParameter("id");
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                String id_espaco = request.getParameter("id_espaco");
                
                request.setAttribute("espacos", espacos);
                request.setAttribute("id", id);
                request.setAttribute("nome", nome);
                request.setAttribute("descricao", descricao);
                request.setAttribute("id_espaco", id_espaco);

                RequestDispatcher rd = request.getRequestDispatcher("/vegetal/edit.jsp");
                rd.forward(request, response);
            
            } else if (op.equals("filtrarEspacos")) {
                int espacoId = Integer.parseInt(request.getParameter("espacoId"));
                espacos = espacos();
                vegetais = index();

                ArrayList<Vegetal> vegetaisFiltrados = new ArrayList<>();
                for (Vegetal vegetal : vegetais) {
                    if (vegetal.getId_espaco() == espacoId) {
                        vegetaisFiltrados.add(vegetal);
                    }
                }

                request.setAttribute("espacos", espacos);
                request.setAttribute("vegetais", vegetaisFiltrados);

                String page = "/vegetal/filtrarvegetal.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/vegetais?status=error&message=Não foi possível retornar os vegetais: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        if (logado == null || logado.getNivel() != 1) {
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
            response.sendRedirect(request.getContextPath() + "/vegetais");
            return;
        }

        try {
            if (opcao.equals("cadastrar")) {
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                int id_espaco = Integer.parseInt(request.getParameter("id_espaco"));

                boolean salvo = this.create(nome, descricao, id_espaco);

                if (salvo) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível cadastrar o vegetal.");
                }
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                int idEspaco = Integer.parseInt(request.getParameter("id_espaco"));

                boolean editado = this.edit(id, nome, descricao, idEspaco);

                if (editado) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Vegetal salvo!!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível editar o vegetal.");
                }
            } else if (opcao.equals("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean excluido = this.delete(id);

                if (excluido) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Vegetal excluído com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível excluir o vegetal.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Ocorreu um erro: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/vegetais");
    }

    public boolean create(String nome, String descricao, int id_espaco) throws Exception {
        Vegetal v = new Vegetal(nome, descricao, id_espaco);
        return v.save();
    }

    public ArrayList<Vegetal> index() throws Exception {
        Vegetal v = new Vegetal();
        return v.getAll();
    }

    public ArrayList<Espaco> espacos() throws Exception {
        Espaco e = new Espaco();
        return e.getAll();
    }

    public boolean edit(int id, String nome, String descricao, int id_espaco) throws Exception {
        Vegetal v = new Vegetal(id, nome, descricao, id_espaco);
        return v.update();
    }

    public boolean delete(int id) throws Exception {
        Vegetal v = new Vegetal(id);
        return v.delete();
    }
}
