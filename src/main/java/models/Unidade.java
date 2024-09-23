package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Unidade {
    private int id;
    private String nome;
    
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
    
    public Unidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Unidade(String nome) {
        this.nome = nome;
    }
    
    public Unidade(int id) {
    	this.id = id;
    }
    
    public Unidade() {}
    
    Connection conexao = null;
    ResultSet rs = null;
    PreparedStatement pstm = null;

    DAO banco = new DAO();

    // Método para salvar uma unidade
    public boolean save() throws Exception {
        String sql = "INSERT INTO unidades (nome) VALUES (?)";
        this.conexao = banco.conectar();
        boolean salvo = false;

        try {
            pstm = this.conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.executeUpdate();
            salvo = true;
        } catch (SQLException e) {
            throw new Exception("Impossível salvar unidade: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return salvo;
    }

    // Método para buscar todas as unidades
    public ArrayList<Unidade> getAll() throws Exception {
        String query = "SELECT * FROM unidades ORDER BY nome";
        this.conexao = banco.conectar();
        ArrayList<Unidade> unidades = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);

                Unidade u = new Unidade(id, nome);
                unidades.add(u);
            }
        } catch (SQLException ex) {
            throw new Exception("Não foi possível encontrar as unidades: " + ex.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return unidades;
    }

    // Método para atualizar uma unidade
    public boolean update() throws Exception {
        String sql = "UPDATE unidades SET nome=? WHERE id=?";
        this.conexao = banco.conectar();
        boolean editado = false;

        try {
            pstm = this.conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setInt(2, this.id);
            pstm.executeUpdate();
            editado = true;
        } catch (SQLException e) {
            throw new Exception("Impossível atualizar unidade: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return editado;
    }

    // Método para deletar uma unidade
    public boolean delete() throws Exception {
        String sql = "DELETE FROM unidades WHERE id=?";
        this.conexao = banco.conectar();
        boolean deletado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            deletado = true;
        } catch (SQLException e) {
            throw new Exception("Não foi possível deletar a unidade: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return deletado;
    }
}
