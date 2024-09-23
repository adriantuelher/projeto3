package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("opcao");
		Usuario logado = (Usuario) request.getSession().getAttribute("logado");

		if (op != null && op.equals("editar")) {
			String id = request.getParameter("id");

			if (id != null && !id.equals("1")) {
				if (logado.getNivel() == 1 || logado.getId() == Integer.parseInt(id)) {
					String usuario = request.getParameter("usuario");
					String senha = request.getParameter("senha");
					String email = request.getParameter("email");
					String nome = request.getParameter("nome");
					String tipo = request.getParameter("tipo");

					request.setAttribute("id", id);
					request.setAttribute("usuario", usuario);
					request.setAttribute("senha", senha);
					request.setAttribute("email", email);
					request.setAttribute("nome", nome);
					request.setAttribute("tipo", tipo);

					RequestDispatcher rd = request.getRequestDispatcher("/usuario/editar.jsp");
					rd.forward(request, response);
					return;
				} else {
					request.getSession().setAttribute("status", "error");
					request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
				}
			} else {
				request.getSession().setAttribute("status", "error");
				request.getSession().setAttribute("message", "Super usuário não pode ser editado!");
			}
			response.sendRedirect(request.getContextPath() + "/usuarios");
			return;
		}

		if(logado.getNivel() == 1) {
			try {
		
			ArrayList<Usuario> usuarios = index();
			request.setAttribute("usuarios", usuarios);
			RequestDispatcher rd = request.getRequestDispatcher("/usuario/index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()
					+ "/usuarios?status=error&message=Não foi possível retornar os usuários: " + e.getMessage());
		}
	} else {
		request.getSession().setAttribute("status", "error");
		request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String opcao = request.getParameter("opcao");
	    Usuario logado = (Usuario) request.getSession().getAttribute("logado");

	    if (logado == null) {
	        response.sendRedirect(request.getContextPath() + "/usuarios");
	        return;
	    }

	    if (opcao.equals("cadastrar")) {
	        String usuario = request.getParameter("usuario");
	        String senha1 = request.getParameter("senha1");
	        String senha2 = request.getParameter("senha2");
	        String email = request.getParameter("email");
	        String nome = request.getParameter("nome");
	        String tipo = request.getParameter("tipo");

	        if (senha1.equals(senha2)) {
	            int nivel = ("TAE".equals(tipo)) ? 2 : ("Aluno".equals(tipo)) ? 4 : 3;
	            try {
	                if (create(usuario, senha1, email, nome, tipo, nivel)) {
	                    request.getSession().setAttribute("status", "success");
	                    request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
	                } else {
	                    request.getSession().setAttribute("status", "error");
	                    request.getSession().setAttribute("message", "Não foi possível cadastrar o usuário.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	                request.getSession().setAttribute("status", "error");
	                request.getSession().setAttribute("message", "Não foi possível cadastrar o usuário: " + e.getMessage());
	            }
	        } else {
	            request.getSession().setAttribute("status", "error");
	            request.getSession().setAttribute("message", "As senhas não coincidem!");
	        }
	        response.sendRedirect(request.getContextPath() + "/usuarios");
	        return;
	    }

	    if (opcao.equals("excluir")) {
	        int id = Integer.parseInt(request.getParameter("id"));

	        if (id != 1) {
	            if (logado.getNivel() == 1 || logado.getId() == id) {
	                try {
	                    if (delete(id)) {
	                        request.getSession().setAttribute("status", "success");
	                        request.getSession().setAttribute("message", "Usuário excluído com sucesso!");
	                    } else {
	                        request.getSession().setAttribute("status", "error");
	                        request.getSession().setAttribute("message", "Não foi possível deletar o usuário!");
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    request.getSession().setAttribute("status", "error");
	                    request.getSession().setAttribute("message", "Não foi possível deletar o usuário: " + e.getMessage());
	                }
	            } else {
	                request.getSession().setAttribute("status", "error");
	                request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
	            }
	        } else {
	            request.getSession().setAttribute("status", "error");
	            request.getSession().setAttribute("message", "Super usuário não pode ser excluído!");
	        }
	        response.sendRedirect(request.getContextPath() + "/usuarios");
	        return;
	    }

	    if (opcao.equals("editar")) {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String usuario = request.getParameter("usuario");
	        String senha1 = request.getParameter("senha1");
	        String senha2 = request.getParameter("senha2");
	        String email = request.getParameter("email");
	        String nome = request.getParameter("nome");
	        String tipo = request.getParameter("tipo");

	        if (id != 1) {
	            if (senha1.equals(senha2)) {
	                int nivel = ("TAE".equals(tipo)) ? 2 : ("Professor".equals(tipo)) ? 3 : 4;

	                try {
	                    if (edit(id, usuario, senha1, email, nome, tipo, nivel)) {
	                        request.getSession().setAttribute("status", "success");
	                        request.getSession().setAttribute("message", "Usuário editado com sucesso!");
	                    } else {
	                        request.getSession().setAttribute("status", "error");
	                        request.getSession().setAttribute("message", "Não foi possível editar o usuário!");
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    request.getSession().setAttribute("status", "error");
	                    request.getSession().setAttribute("message", "Não foi possível editar o usuário: " + e.getMessage());
	                }
	            } else {
	                request.getSession().setAttribute("status", "error");
	                request.getSession().setAttribute("message", "As senhas não coincidem!");
	            }
	        } else {
	            request.getSession().setAttribute("status", "error");
	            request.getSession().setAttribute("message", "Super usuário não pode ser editado!");
	        }
	        response.sendRedirect(request.getContextPath() + "/usuarios");
	    }
	}

	public boolean create(String usuario, String senha, String email, String nome, String tipo, int nivel) throws Exception {
		Usuario u = new Usuario(usuario, senha, email, nome, tipo, nivel);
		return u.save();
	}

	public boolean delete(int id) throws Exception {
		Usuario u = new Usuario(id);
		return u.delete();
	}

	public boolean edit(int id, String usuario, String senha, String email, String nome, String tipo, int nivel) throws Exception {
		Usuario u = new Usuario(id, usuario, senha, email, nome, tipo, nivel);
		return u.update();
	}

	public ArrayList<Usuario> index() throws Exception {
		Usuario u = new Usuario();
		return u.getAll();
	}
}
