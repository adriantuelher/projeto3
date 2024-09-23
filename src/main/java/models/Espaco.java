package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Espaco {

private int id;
private String nome;
private int id_bloco;

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
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

public int getId_bloco() {
	return id_bloco;
}
public void setId_bloco(int id_bloco) {
	this.id_bloco = id_bloco;
}

public Espaco() {}

public Espaco (int id, String nome, int id_bloco) {
	this.id = id;
	this.nome = nome;
	this.id_bloco = id_bloco;
}

public Espaco(int id) {
	this.id = id;
}

public Espaco (String nome, int id_bloco) {
	this.nome = nome;
	this.id_bloco = id_bloco;
}

public boolean save() throws Exception {
	String sql = "insert into espacos (nome, id_bloco) values (?, ?)";
	this.conexao = banco.conectar();
	boolean salvo = false;
	
	try {
		pstm = this.conexao.prepareStatement(sql);
		pstm.setString(1, this.nome);
		pstm.setInt(2,  this.id_bloco);
		pstm.executeUpdate();
		salvo = true;
	} catch (SQLException e) {
		throw new SQLException ("Não foi possível cadastrar o espaço: " + e.getMessage(), e);
} finally {
	this.conexao = banco.desconectar(conexao);
}
	return salvo;
}

public ArrayList<Espaco> getAll() throws Exception{
	String query = "select * from espacos order by nome";
	this.conexao = banco.conectar();
	ArrayList<Espaco> espacos = new ArrayList<>();
	
	try {
		pstm = conexao.prepareStatement(query);
		rs = pstm.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt(1);
			String nome = rs.getString(2);
			int id_bloco = rs.getInt(3);
			
			Espaco e = new Espaco(id, nome, id_bloco);
			espacos.add(e);
		}
	} catch (SQLException ex) {
		throw new SQLException("Não foi possível encontrar os espaços: " + ex.getMessage());
	} finally {
		this.conexao = banco.desconectar(conexao);
	}
	return espacos;
}


public boolean update() throws Exception {
	String sql = "update espacos set nome=?, id_bloco=? where id=?";
	this.conexao = banco.conectar();
	boolean editado = false;
	
	try {
		pstm = this.conexao.prepareStatement(sql);
		pstm.setString(1,  this.nome);
		pstm.setInt(2, this.id_bloco);
		pstm.setInt(3,  this.id);
		pstm.executeUpdate();
		editado = true;
	} catch (SQLException e) {
		throw new SQLException ("Não foi possível editar o espaço: " + e.getMessage());
	} finally {
		this.conexao = banco.desconectar(conexao);
	}
	return editado;
}

public boolean delete() throws Exception {
	String sql = "delete from espacos where id=?";
	this.conexao = banco.conectar();
	boolean deletado = false;
	
	try {
		pstm = this.conexao.prepareStatement(sql);
		pstm.setInt(1, this.id);
		pstm.executeUpdate();
		deletado = true;
	} catch (SQLException e) {
		throw new Exception ("Não foi possível deletar o espaço: " + e.getMessage());
	} finally {
		this.conexao = banco.desconectar(conexao);
	}
	return deletado;
}



}

