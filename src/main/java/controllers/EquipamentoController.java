package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Equipamento;
import models.Espaco;
import models.Usuario;

@WebServlet("/equipamentos")
public class EquipamentoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EquipamentoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        try {
            ArrayList<Equipamento> equipamentos = index();
        	ArrayList<Espaco> espacos = espacos();

            request.setAttribute("espacos", espacos);
            request.setAttribute("equipamentos", equipamentos);

            if (op == null) {
                String url = "/equipamento/index.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
          
            } else if (op.equals("cadastrar")) {
                    if (logado.getNivel() != 1) {
                        request.getSession().setAttribute("status", "error");
                        request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                        response.sendRedirect(request.getContextPath() + "/equipamentos");
                        return;
            	}
            	request.setAttribute("espacos", espacos);
            	RequestDispatcher rd = request.getRequestDispatcher("/equipamento/new.jsp");
            	rd.forward(request, response);
           
            }  else if (op.equals("editar")) {
                if (logado.getNivel() != 1) {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                    response.sendRedirect(request.getContextPath() + "/equipamentos");
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

                RequestDispatcher rd = request.getRequestDispatcher("/equipamento/edit.jsp");
                rd.forward(request, response);
            
            }  else if (op.equals("filtrarEspacos")) {
                // Pega o ID do espaço da requisição
                int espacoId = Integer.parseInt(request.getParameter("espacoId"));
                
                // Carrega a lista de equipamentos e espaços
                espacos = espacos();  // Supondo que este método retorne todos os espaços
                equipamentos = index();  // Supondo que este método retorne todos os equipamentos

                // Filtra os equipamentos que pertencem ao espaço selecionado
                ArrayList<Equipamento> equipamentosFiltrados = new ArrayList<>();
                for (Equipamento equipamento : equipamentos) {
                    if (equipamento.getId_espaco() == espacoId) {
                        equipamentosFiltrados.add(equipamento);
                    }
                }

                // Atribui os dados filtrados aos atributos da requisição
                request.setAttribute("espacos", espacos);
                request.setAttribute("equipamentos", equipamentosFiltrados);

                // Direciona para a página JSP que exibe os equipamentos filtrados
                String page = "/equipamento/filtrarequipamento.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/equipamentos?status=error&message=Não foi possível retornar os equipamentos: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        Usuario logado = (Usuario) request.getSession().getAttribute("logado");

        if (logado == null || logado.getNivel() != 1) {
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
            response.sendRedirect(request.getContextPath() + "/equipamentos");
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
                    request.getSession().setAttribute("message", "Não foi possível cadastrar o equipamento.");
                }
            } else if (opcao.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");
                int idEspaco = Integer.parseInt(request.getParameter("id_espaco"));

                boolean editado = this.edit(id, nome, descricao, idEspaco);

                if (editado) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Equipamento salvo!!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível editar o equipamento.");
                }
            } else if (opcao.equals("excluir")) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean excluido = this.delete(id);

                if (excluido) {
                    request.getSession().setAttribute("status", "success");
                    request.getSession().setAttribute("message", "Equipamento excluído com sucesso!");
                } else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível excluir o equipamento.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("status", "error");
            request.getSession().setAttribute("message", "Ocorreu um erro: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/equipamentos");
    }

    public boolean create(String nome, String descricao, int id_espaco) throws Exception {
        Equipamento e = new Equipamento(nome, descricao, id_espaco);
        return e.save();
    }

    public ArrayList<Equipamento> index() throws Exception {
        Equipamento e = new Equipamento();
        return e.getAll();
    }
    

    public ArrayList<Espaco> espacos() throws Exception {
        Espaco e = new Espaco();
        return e.getAll();
    }

    public boolean edit(int id, String nome, String descricao, int id_espaco) throws Exception {
        Equipamento e = new Equipamento(id, nome, descricao, id_espaco);
        return e.update();
    }

    public boolean delete(int id) throws Exception {
        Equipamento e = new Equipamento(id);
        return e.delete();
    }
}
