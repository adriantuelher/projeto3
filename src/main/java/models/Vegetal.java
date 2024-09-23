package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.DAO;

public class Vegetal {
    private int id;
    private String nome;
    private String descricao;
    private int id_espaco;

    public int getId_espaco() {
        return id_espaco;
    }

    public void setId_espaco(int id_espaco) {
        this.id_espaco = id_espaco;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Construtores
    public Vegetal(int id, String nome, String descricao, int id_espaco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.id_espaco = id_espaco;
    }

    public Vegetal(String nome, String descricao, int id_espaco) {
        this.nome = nome;
        this.descricao = descricao;
        this.id_espaco = id_espaco;
    }

    public Vegetal(int id) {
        this.id = id;
    }

    public Vegetal() {}

    // Conexão com banco de dados
    Connection conexao = null;
    ResultSet rs = null;
    PreparedStatement pstm = null;

    DAO banco = new DAO();

    // Método para salvar um vegetal
    public boolean save() throws Exception {
        String sql = "INSERT INTO vegetais (nome, descricao, id_espaco) VALUES (?, ?, ?)";
        this.conexao = banco.conectar();
        boolean salvo = false;

        try {
            pstm = this.conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setString(2, this.descricao);
            pstm.setInt(3, this.id_espaco);
            pstm.executeUpdate();
            salvo = true;
        } catch (SQLException e) {
            throw new Exception("Impossível salvar vegetal: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return salvo;
    }

    // Método para buscar todos os vegetais
    public ArrayList<Vegetal> getAll() throws Exception {
        String query = "SELECT * FROM vegetais ORDER BY nome";
        this.conexao = banco.conectar();
        ArrayList<Vegetal> vegetais = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String descricao = rs.getString(3);
                int id_espaco = rs.getInt(4);

                Vegetal v = new Vegetal(id, nome, descricao, id_espaco);
                vegetais.add(v);
            }
        } catch (SQLException ex) {
            throw new Exception("Não foi possível encontrar os vegetais: " + ex.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return vegetais;
    }

    // Método para atualizar um vegetal
    public boolean update() throws Exception {
        String sql = "UPDATE vegetais SET nome=?, descricao=?, id_espaco=? WHERE id=?";
        this.conexao = banco.conectar();
        boolean editado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setString(2, this.descricao);
            pstm.setInt(3, this.id_espaco);
            pstm.setInt(4, this.id);
            pstm.executeUpdate();
            editado = true;
        } catch (SQLException e) {
            throw new Exception("Impossível atualizar vegetal: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return editado;
    }

    // Método para deletar um vegetal
    public boolean delete() throws Exception {
        String sql = "DELETE FROM vegetais WHERE id=?";
        this.conexao = banco.conectar();
        boolean deletado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            deletado = true;
        } catch (SQLException e) {
            throw new Exception("Não foi possível deletar o vegetal: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return deletado;
    }
}
