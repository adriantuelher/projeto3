package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Unidade;
import models.Usuario;

/**
 * Servlet implementation class UnidadeController
 */
@WebServlet("/unidades")
public class UnidadeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UnidadeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario logado = (Usuario) request.getSession().getAttribute("logado");
		
		String opcao = request.getParameter("opcao");
		
		try {
			if(opcao == null) {
				ArrayList<Unidade> unidades = unidades();
				
				request.setAttribute("unidades", unidades);
				String page = "/unidade/index.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			} else if (opcao.equals("editar")) {
				String id = request.getParameter("id");
				String nome = request.getParameter("nome");
				
				if (logado.getNivel() == 1){
					request.setAttribute("id", id);
					request.setAttribute("nome", nome);
					request.setAttribute("unidades", this.unidades());
					RequestDispatcher rd = request.getRequestDispatcher("/unidade/edit.jsp");
					rd.forward(request, response);
				} else {
					   request.getSession().setAttribute("status", "error");
		                request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
		                response.sendRedirect(request.getContextPath() + "/unidades");
				}
			}
		} catch(Exception e) {
			 e.printStackTrace();
		        String mensagem = "Não foi possível retornar os dados: " + e.getMessage();
		        request.getSession().setAttribute("errorMessage", mensagem);
		        response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao = request.getParameter("opcao");
		
		Usuario logado = (Usuario) request.getSession().getAttribute("logado");
		
		
		if(logado.getNivel() == 1) {
		if(opcao != null && opcao.equals("cadastrar")) {
			String nome = request.getParameter("nome");
		
			try {
				boolean salvo = this.create(nome);
				if(salvo) {
					request.getSession().setAttribute("status", "success");
		            request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
				}
			} catch (Exception e) {
				 e.printStackTrace();
			        request.getSession().setAttribute("status", "error");
			        request.getSession().setAttribute("message", e.getMessage());
			}
			response.sendRedirect(request.getContextPath() + "/unidades");
		
		} else if (opcao != null && opcao.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nome = request.getParameter("nome");
			
			try {				
				boolean editado = this.edit(id, nome);
				if(editado) {
		            request.getSession().setAttribute("status", "success");
		            request.getSession().setAttribute("message", "Unidade salva!");				
				} else {
                    request.getSession().setAttribute("status", "error");
                    request.getSession().setAttribute("message", "Não foi possível editar a unidade.");
				}
		} catch(Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("status", "error");
	        request.getSession().setAttribute("message", e.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/unidades");
		} else if (opcao != null && opcao.equals("excluir")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				boolean excluido = this.delete(id);
				
				if(excluido) {
					request.getSession().setAttribute("status", "message");
					request.getSession().setAttribute("message", "Unidade excluída com sucesso!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("status", "error");
				request.getSession().setAttribute("message", e.getMessage());
			}
			response.sendRedirect(request.getContextPath() + "/unidades");			

		}
		} else {
			 request.getSession().setAttribute("status", "error");
             request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
             response.sendRedirect(request.getContextPath() + "/unidades");
		}
	}

	public boolean create(String nome) throws Exception {
		Unidade u = new Unidade(nome);
		return u.save();
	}
	
	public ArrayList<Unidade> unidades() throws Exception {
		Unidade u = new Unidade();
		return u.getAll();
	}
	
	public boolean edit(int id, String nome) throws Exception{
		Unidade u = new Unidade(id, nome);
		return u.update();
	}
	
	public boolean delete(int id) throws Exception {
		Unidade u = new Unidade(id);
		return u.delete();
	}
	
		
}
