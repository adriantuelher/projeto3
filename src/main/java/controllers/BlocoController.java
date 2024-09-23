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
import models.Unidade;
import models.Usuario;

/**
 * Servlet implementation class BlocoController
 */
@WebServlet("/blocos")
public class BlocoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlocoController() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		Usuario logado = (Usuario) request.getSession().getAttribute("logado");
		
        String opcao = request.getParameter("opcao");
        
        try {
            if (opcao == null) {
                ArrayList<Unidade> unidades = unidades();
                ArrayList<Bloco> blocos = index();
             
                request.setAttribute("blocos", blocos);
                request.setAttribute("unidades", unidades);
                RequestDispatcher rd = request.getRequestDispatcher("/bloco/index.jsp");
                rd.forward(request, response);
        		
            } else if (logado.getNivel() == 1) {
            	if (opcao.equals("cadastrar")) {
                    ArrayList<Unidade> unidades = unidades();
                    request.setAttribute("unidades", unidades);
                    RequestDispatcher rd = request.getRequestDispatcher("/bloco/new.jsp");
                    rd.forward(request, response);
            
            	} else if(opcao.equals("editar")) {
            		String id = request.getParameter("id");
            		String nome = request.getParameter("nome");
            		int id_unidade = Integer.parseInt(request.getParameter("id_unidade"));
            		
            		request.setAttribute("id", id);
            		request.setAttribute("nome", nome);
            		request.setAttribute("id_unidade", id_unidade);
            		request.setAttribute("unidades", this.unidades());
            		RequestDispatcher rd = request.getRequestDispatcher("/bloco/edit.jsp");
            		rd.forward(request, response);
            	}
                    
            	else {
                request.getSession().setAttribute("status", "error");
                request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                response.sendRedirect(request.getContextPath() + "/blocos");
            }
           }
        }catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Não foi possível retornar os dados: " + e.getMessage();
            request.getSession().setAttribute("errorMessage", mensagem);
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
        }
            
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String opcao = request.getParameter("opcao");
			
			Usuario logado = (Usuario) request.getSession().getAttribute("logado");
			
			if(logado.getNivel() == 1 && opcao != null) {
				if(opcao.equals("cadastrar")) {
					String nome = request.getParameter("nome");
					int id_unidade = Integer.parseInt(request.getParameter("id_unidade"));
					
					try {
						boolean salvo = this.create(nome, id_unidade);
						
						if(salvo) {
				            request.getSession().setAttribute("status", "success");
				            request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
						}
					} catch(Exception e) {
						   e.printStackTrace();
					        request.getSession().setAttribute("status", "error");
					        request.getSession().setAttribute("message", e.getMessage());
					}
				    response.sendRedirect(request.getContextPath() + "/blocos");

				} else if (opcao.equals("editar")){
					int id = Integer.parseInt(request.getParameter("id"));
					String nome = request.getParameter("nome");
					int id_unidade = Integer.parseInt(request.getParameter("id_unidade"));
					
					try {
						boolean editado = this.edit(id, nome, id_unidade);
						if(editado) {
							request.getSession().setAttribute("status", "success");
				            request.getSession().setAttribute("message", "Bloco salvo!");
						}
					} catch(Exception e) {
						e.printStackTrace();
				        request.getSession().setAttribute("status", "error");
				        request.getSession().setAttribute("message", e.getMessage());
					}
				    response.sendRedirect(request.getContextPath() + "/blocos");

			} else if(opcao.equals("excluir")) {
				int id = Integer.parseInt(request.getParameter("id"));
				
				try {
					boolean excluido = this.delete(id);
					
					if(excluido) {
						request.getSession().setAttribute("status", "message");
						request.getSession().setAttribute("message", "Bloco excluído com sucesso!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					request.getSession().setAttribute("status", "error");
					request.getSession().setAttribute("message", e.getMessage());
				}
				response.sendRedirect(request.getContextPath() + "/blocos");			
			}
				
				else {
				request.getSession().setAttribute("status", "error");
                request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
                response.sendRedirect(request.getContextPath() + "/blocos");
				}
			}
		}

	public boolean create(String nome, int id_unidade) throws Exception {
		Bloco b = new Bloco(nome, id_unidade);
		return b.save();
	}
	
	public boolean edit(int id, String nome, int id_unidade) throws Exception {
		Bloco b = new Bloco(id, nome, id_unidade);
		return b.update();
	}
	
	public boolean delete(int id) throws Exception {
		Bloco b = new Bloco(id);
		return b.delete();
	}
	
	public ArrayList<Bloco> index() throws Exception{
		Bloco b = new Bloco();
		return b.getAll();
	}
	
	public ArrayList<Unidade> unidades() throws Exception{
		Unidade e = new Unidade();
		return e.getAll();
	}
}
