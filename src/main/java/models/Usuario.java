package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Usuario {

	private int id;
	private String usuario;
	private String senha;
	private String email;
	private String nome;
	private String tipo;
	private int nivel;
	
	Connection conexao = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;
	
	DAO banco = new DAO();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Usuario() {}
	
	public Usuario(int id, String usuario,String senha, String email, String nome, String tipo, int nivel) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.tipo = tipo;
		this.nivel = nivel;
	}
	
	public Usuario(String usuario, String senha,  String email, String nome, String tipo, int nivel) {
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.tipo = tipo;
		this.nivel = nivel;
	}
	
	public Usuario(int id) {
		this.id = id;
	}
	
	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public boolean save() throws Exception {
		String sql = "insert into usuarios (usuario, senha, email, nome, tipo, nivel) values (?, ?, ?, ?, ?, ?)";
		this.conexao = banco.conectar();
		boolean salvo = false;
		
		try {
			pstm = this.conexao.prepareStatement(sql);
			pstm.setString(1, this.usuario);
			pstm.setString(2,  this.senha);
			pstm.setString(3, this.email);
			pstm.setString(4, this.nome);
			pstm.setString(5, this.tipo);
			pstm.setInt(6, this.nivel);
			pstm.executeUpdate();
			salvo = true;
		} catch (SQLException e) {
			throw new SQLException ("Não foi possível cadastrar o usuário: " +  e.getMessage());
		} finally {
			this.conexao = banco.desconectar(conexao);
		}
		return salvo;
	}
	
	public ArrayList<Usuario> getAll() throws Exception {
	String query = "select * from usuarios order by nome";
	this.conexao = banco.conectar();
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	try {
		pstm = conexao.prepareStatement(query);
		rs = pstm.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String usuario = rs.getString("usuario");
			String senha = rs.getString("senha");
			String email = rs.getString("email");
			String nome = rs.getString("nome");
			String tipo = rs.getString("tipo");
			int nivel = rs.getInt("nivel");
			
			Usuario u = new Usuario(id, usuario, senha, email, nome, tipo, nivel);
			usuarios.add(u);
		}
	} catch (SQLException e) {
		throw new SQLException ("Não foi possível retornar os usuários: " + e.getMessage());
} finally {
	this.conexao = banco.desconectar(conexao);
} 
	return usuarios;
}
	
	public boolean update() throws Exception {
		String sql = "update usuarios set usuario=?, senha=?, email=?, nome=?, tipo=?, nivel=? where id=?";
		this.conexao = banco.conectar();
		boolean editado = false;
		
		try {
			pstm = this.conexao.prepareStatement(sql);
			pstm.setString(1, this.usuario);
			pstm.setString(2, this.senha);
			pstm.setString(3,  this.email);
			pstm.setString(4, this.nome);
			pstm.setString(5, this.tipo);
			pstm.setInt(6, this.nivel);
			pstm.setInt(7, this.id);
			pstm.executeUpdate();
			editado = true;
		} catch (SQLException e) {
			throw new SQLException("Não foi possível editar o usuário: " + e.getMessage());
		} finally {
			this.conexao = banco.desconectar(conexao);
		}
		return editado;
	}
	
	public boolean delete() throws Exception{
		String sql = "delete from usuarios where id=?";
		this.conexao = banco.conectar();
		boolean deletado = false;
		
		try {
			pstm = this.conexao.prepareStatement(sql);
			pstm.setInt(1, this.id);
			pstm.executeUpdate();
			deletado = true;
		} catch (SQLException e) {
			throw new SQLException ("Não foi possível excluir o usuário: " + e.getMessage());
		} finally {
			this.conexao = banco.desconectar(conexao);
		}
		return deletado;
	}

	public Usuario verificar() throws Exception {
		String sql = "select * from usuarios where usuario=? and senha=?";
		Usuario u = null;
		
		try {
			this.conexao = banco.conectar();
			pstm = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstm.setString(1, this.usuario);
			pstm.setString(2, this.senha);
			rs = pstm.executeQuery();
			if (rs.next()){
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setUsuario(rs.getString("usuario"));
				u.setSenha(rs.getString("senha"));
				u.setEmail(rs.getString("email"));
				u.setNome(rs.getString("nome"));
				u.setTipo(rs.getString("tipo"));
				u.setNivel(rs.getInt("nivel"));
				return u;
			} else
				return null;
		} catch (SQLException e) {
			throw new SQLException("Usuário e/ou senha inválidos!");
		} finally {
			this.conexao = banco.desconectar(conexao);
		}
	
	}
}