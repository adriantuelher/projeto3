package controllers;

import java.io.IOException;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Espaco;
import models.Evento;
import models.Usuario;

@WebServlet("/eventos")
public class EventoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    // Verifique se o usuário está logado
	    Usuario logado = (Usuario) request.getSession().getAttribute("logado");

	    String opcao = request.getParameter("opcao");

	    try {
	        if (opcao == null) {
	            ArrayList<Espaco> espacos = espacos();
	            ArrayList<Usuario> usuarios = usuarios();
	            ArrayList<Evento> eventos = index();

	            // Configura os atributos para a página index.jsp
	            request.setAttribute("espacos", espacos);
	            request.setAttribute("usuarios", usuarios);
	            request.setAttribute("eventos", eventos);

	            String page = "/evento/index.jsp";
	            RequestDispatcher rd = request.getRequestDispatcher(page);
	            rd.forward(request, response);

	        } else if (opcao.equals("cadastrar")) {
	            ArrayList<Espaco> espacos = espacos();
	            request.setAttribute("espacos", espacos);
	            RequestDispatcher rd = request.getRequestDispatcher("/evento/new.jsp");
	            rd.forward(request, response);

	        } else if (opcao.equals("filtrarUsers")){
	        	String usuarioIdParam = request.getParameter("usuarioId");
	        	int usuarioId = Integer.parseInt(usuarioIdParam);
	            
	            ArrayList<Espaco> espacos = espacos();
	            ArrayList<Usuario> usuarios = usuarios();
	            ArrayList<Evento> eventos = index();

	            // Filtrando eventos que pertencem ao usuário com o ID especificado
	            ArrayList<Evento> eventosFiltrados = new ArrayList<>();
	            for (Evento evento : eventos) {
	                if (evento.getId_usuario() == usuarioId) {
	                    eventosFiltrados.add(evento);
	                }
	            }

	            request.setAttribute("espacos", espacos);
	            request.setAttribute("usuarios", usuarios);
	            request.setAttribute("eventos", eventosFiltrados);  // Passa a lista filtrada

	            String page = "/evento/filtrarusuario.jsp";
	            RequestDispatcher rd = request.getRequestDispatcher(page);
	            rd.forward(request, response);
	        }else if (opcao.equals("filtrarEspacos")) {
	            String espacoId = request.getParameter("espacoId");

	            // Obtenha todos os eventos
	            ArrayList<Evento> eventos = index(); 
	            ArrayList<Evento> eventosFiltrados = new ArrayList<>();

	            // Filtrar eventos com base no espacoId
	            for (Evento evento : eventos) {
	                if (evento.getId_espaco() == Integer.parseInt(espacoId)) {
	                    eventosFiltrados.add(evento);
	                }
	            }

	            ArrayList<Espaco> espacos = espacos();
	            ArrayList<Usuario> usuarios = usuarios();

	            // Passa a lista filtrada
	            request.setAttribute("espacos", espacos);
	            request.setAttribute("usuarios", usuarios);
	            request.setAttribute("eventos", eventosFiltrados);

	            String page = "/evento/filtrarespaco.jsp"; // Caminho correto da sua JSP
	            RequestDispatcher rd = request.getRequestDispatcher(page);
	            rd.forward(request, response);
	        } else if (opcao.equals("filtrarPorData")) {
	            String dataSelecionada = request.getParameter("data");
	            
	            ArrayList<Evento> eventos = index(); // Obtenha todos os eventos
	            ArrayList<Evento> eventosFiltrados = new ArrayList<>();

	            for (Evento evento : eventos) {
	                if (evento.getData_evento().toString().equals(dataSelecionada)) {
	                    eventosFiltrados.add(evento);
	                }
	            }

	            ArrayList<Espaco> espacos = espacos();
	            ArrayList<Usuario> usuarios = usuarios();

	            // Passa a lista filtrada
	            request.setAttribute("espacos", espacos);
	            request.setAttribute("usuarios", usuarios);
	            request.setAttribute("eventos", eventosFiltrados);

	            String page = "/evento/index.jsp"; // Ou o caminho correto da sua JSP
	            RequestDispatcher rd = request.getRequestDispatcher(page);
	            rd.forward(request, response);
	        
	        } else if (opcao.equals("editar")) {
	            String id = request.getParameter("id");
	            String nome = request.getParameter("nome");
	            String tipo = request.getParameter("tipo");
	            String data_evento = request.getParameter("data_evento");
	            String hora_inicio = request.getParameter("hora_inicio");
	            String hora_fim = request.getParameter("hora_fim");
	            int id_espaco = Integer.parseInt(request.getParameter("id_espaco"));
	            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
	            boolean realizado = Boolean.parseBoolean(request.getParameter("realizado"));
	            	            // formatando a data para ser exibida na página de edição do evento
	            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate eventDate = LocalDate.parse(data_evento, dateFormatter);
	            String formattedDate = eventDate.format(dateFormatter);

	            // Converter hora_inicio e hora_fim para LocalTime
	            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	            LocalTime startTime = LocalTime.parse(hora_inicio, timeFormatter);
	            LocalTime endTime = LocalTime.parse(hora_fim, timeFormatter);

	            if (logado.getId() == id_usuario || logado.getNivel() == 1) {

	            // Verifique se o usuário logado tem permissão para editar o evento
	                request.setAttribute("id", id);
	                request.setAttribute("nome", nome);
	                request.setAttribute("tipo", tipo);
	                request.setAttribute("data_evento", formattedDate);
	                request.setAttribute("hora_inicio", startTime.toString());
	                request.setAttribute("hora_fim", endTime.toString());
	                request.setAttribute("realizado", realizado);
	                request.setAttribute("id_espaco", id_espaco);
	                
	                request.setAttribute("espacos", this.espacos());
	                RequestDispatcher rd = request.getRequestDispatcher("/evento/editar.jsp");
	                rd.forward(request, response);

	            } else {
	                request.getSession().setAttribute("status", "error");
	                request.getSession().setAttribute("message", "Você não tem permissão para essa operação!");
	                response.sendRedirect(request.getContextPath() + "/eventos");
	            }
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        String mensagem = "Não foi possível retornar os dados: " + e.getMessage();
	        request.getSession().setAttribute("errorMessage", mensagem);
	        response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String opcao = request.getParameter("opcao");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (opcao != null && opcao.equals("cadastrar")) {
		    String nome = request.getParameter("nome");
		    String tipo = request.getParameter("tipo");

		    String dataEventoStr = request.getParameter("data_evento");
		    String horaInicioStr = request.getParameter("hora_inicio");
		    String horaFimStr = request.getParameter("hora_fim");
		    LocalDate dataEvento = LocalDate.parse(dataEventoStr, dateFormatter);
		    LocalTime horaInicio = LocalTime.parse(horaInicioStr);
		    LocalTime horaFim = LocalTime.parse(horaFimStr);

		    boolean realizado = request.getParameter("realizado") != null
		            && request.getParameter("realizado").equals("on");
		    int id_espaco = Integer.parseInt(request.getParameter("id_espaco"));
		    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

		    try {
		        boolean salvo = this.create(nome, tipo, dataEvento, horaInicio, horaFim, realizado, id_espaco, id_usuario);

		        if (salvo) {
		            request.getSession().setAttribute("status", "success");
		            request.getSession().setAttribute("message", "Cadastro realizado com sucesso!");
		        } else {
		            request.getSession().setAttribute("status", "error");
		            request.getSession().setAttribute("message", "Não foi possível cadastrar o evento.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("status", "error");
		        request.getSession().setAttribute("message", e.getMessage());
		    }

		    response.sendRedirect(request.getContextPath() + "/eventos");

		} else if (opcao != null && opcao.equals("editar")) {
		    int id = Integer.parseInt(request.getParameter("id"));
		    String nome = request.getParameter("nome");
		    String tipo = request.getParameter("tipo");
		    
		    String dataEventoStr = request.getParameter("data_evento");
		    String horaInicioStr = request.getParameter("hora_inicio");
		    String horaFimStr = request.getParameter("hora_fim");
		    
		    LocalDate dataEvento = LocalDate.parse(dataEventoStr, dateFormatter);
		    LocalTime horaInicio = LocalTime.parse(horaInicioStr);
		    LocalTime horaFim = LocalTime.parse(horaFimStr);

		    boolean realizado = request.getParameter("realizado") != null
		            && request.getParameter("realizado").equals("on");

		    int id_espaco = Integer.parseInt(request.getParameter("id_espaco"));

		    try {
		        boolean editado = this.edit(id, nome, tipo, dataEvento, horaInicio, horaFim, realizado, id_espaco);

		        if (editado) {
		            request.getSession().setAttribute("status", "success");
		            request.getSession().setAttribute("message", "Evento salvo!");
		        } else {
		            request.getSession().setAttribute("status", "error");
		            request.getSession().setAttribute("message", "Não foi possível editar o evento.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.getSession().setAttribute("status", "error");
		        request.getSession().setAttribute("message", e.getMessage());
		    }

		    response.sendRedirect(request.getContextPath() + "/eventos");
		
		} else if (opcao != null && opcao.equals("excluir")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
			boolean excluido = this.delete(id);
			
			if (excluido) {
				request.getSession().setAttribute("status", "message");
				request.getSession().setAttribute("message", "Evento excluído com sucesso!");
			} else {
				request.getSession().setAttribute("status", "sucess");
				request.getSession().setAttribute("message," , "Não foi possível excluir o evento.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("status", "error");
				request.getSession().setAttribute("message", e.getMessage());
			}
			response.sendRedirect(request.getContextPath() + "/eventos");			
		}
	}

	public boolean create(String nome, String tipo, LocalDate data_evento, LocalTime hora_inicio, LocalTime hora_fim,
			boolean realizado, int id_espaco, int id_usuario) throws Exception {
		Evento e = new Evento(nome, tipo, data_evento, hora_inicio, hora_fim, realizado, id_espaco, id_usuario);
		return e.save();
	}

	public ArrayList<Evento> index() throws Exception {
		Evento e = new Evento();
		return e.getAll();
	}

	public ArrayList<Espaco> espacos() throws Exception {
		Espaco esp = new Espaco();
		return esp.getAll();
	}

	public ArrayList<Usuario> usuarios() throws Exception {
		Usuario u = new Usuario();
		return u.getAll();
	}
	
	public boolean edit(int id, String nome, String tipo, LocalDate data_evento, LocalTime hora_inicio, LocalTime hora_fim, boolean realizado, int id_espaco) throws Exception {
		Evento e = new Evento (id, nome, tipo, data_evento, hora_inicio, hora_fim, realizado, id_espaco);
		return e.update();
	}
	
	public boolean delete(int id) throws Exception {
		Evento e = new Evento(id);
		return e.delete();
	}
	
}
