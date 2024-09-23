package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Bloco {
    private int id;
    private String nome;
    private int id_unidade;

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
    public int getId_unidade() {
        return id_unidade;
    }
    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    public Bloco(int id, String nome, int id_unidade) {
        this.id = id;
        this.nome = nome;
        this.id_unidade = id_unidade;
    }

    public Bloco(String nome, int id_unidade) {
        this.nome = nome;
        this.id_unidade = id_unidade;
    }
    
    public Bloco(int id) {
    	this.id = id;
    }
    
    public Bloco() {}

    Connection conexao = null;
    ResultSet rs = null;
    PreparedStatement pstm = null;

    DAO banco = new DAO();

    // Método para salvar um bloco
    public boolean save() throws Exception {
        String sql = "INSERT INTO blocos (nome, id_unidade) VALUES (?, ?)";
        this.conexao = banco.conectar();
        boolean salvo = false;

        try {
            pstm = this.conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setInt(2, this.id_unidade);
            pstm.executeUpdate();
            salvo = true;
        } catch (SQLException e) {
            throw new Exception("Impossível salvar bloco: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return salvo;
    }

    // Método para buscar todos os blocos
    public ArrayList<Bloco> getAll() throws Exception {
        String query = "SELECT * FROM blocos order by nome";
        this.conexao = banco.conectar();
        ArrayList<Bloco> blocos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                int id_unidade = rs.getInt(3);

                Bloco b = new Bloco(id, nome, id_unidade);
                blocos.add(b);
            }
        } catch (SQLException ex) {
            throw new Exception("Não foi possível encontrar os blocos: " + ex.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return blocos;
    }

    // Método para atualizar um bloco
    public boolean update() throws Exception {
        String sql = "UPDATE blocos SET nome=?, id_unidade=? WHERE id=?";
        this.conexao = banco.conectar();
        boolean editado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setInt(2, this.id_unidade);
            pstm.setInt(3, this.id);
            pstm.executeUpdate();
            editado = true;
        } catch (SQLException e) {
            throw new Exception("Impossível atualizar bloco: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return editado;
    }

    // Método para deletar um bloco
    public boolean delete() throws Exception {
        String sql = "DELETE FROM blocos WHERE id=?";
        this.conexao = banco.conectar();
        boolean deletado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            deletado = true;
        } catch (SQLException e) {
            throw new Exception("Não foi possível deletar o bloco: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return deletado;
    }
}
