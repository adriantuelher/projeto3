package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import classes.DAO;

public class Evento {
    private int id;
    private String nome;
    private String tipo;
    private LocalDate data_evento;
    private LocalTime hora_inicio;
    private LocalTime hora_fim;
    private boolean realizado;
    private int id_espaco;
    private int id_usuario;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData_evento() {
        return data_evento;
    }

    public void setData_evento(LocalDate data_evento) {
        this.data_evento = data_evento;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(LocalTime hora_fim) {
        this.hora_fim = hora_fim;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public int getId_espaco() {
        return id_espaco;
    }

    public void setId_espaco(int id_espaco) {
        this.id_espaco = id_espaco;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Evento() {}

    public Evento(int id, String nome, String tipo, LocalDate data_evento, LocalTime hora_inicio,
            LocalTime hora_fim, boolean realizado, int id_espaco, int id_usuario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.data_evento = data_evento;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.realizado = realizado;
        this.id_espaco = id_espaco;
        this.id_usuario = id_usuario;
    }

    public Evento(String nome, String tipo, LocalDate data_evento, LocalTime hora_inicio,
            LocalTime hora_fim, boolean realizado, int id_espaco, int id_usuario) {
        this.nome = nome;
        this.tipo = tipo;
        this.data_evento = data_evento;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.realizado = realizado;
        this.id_espaco = id_espaco;
        this.id_usuario = id_usuario;
    }
    
    public Evento(int id, String nome, String tipo, LocalDate data_evento, LocalTime hora_inicio,
            LocalTime hora_fim, boolean realizado, int id_espaco) {
    	this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.data_evento = data_evento;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.realizado = realizado;
        this.id_espaco = id_espaco;
    }
    
    public Evento(int id) {
    	this.id = id;
    }

    public boolean save() throws Exception {
        String sql = "INSERT INTO eventos (nome, tipo, data_evento, hora_inicio, hora_fim, realizado, id_espaco, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        this.conexao = banco.conectar();
        boolean salvo = false;

        try {
            pstm = this.conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setString(2, this.tipo);
            pstm.setDate(3, java.sql.Date.valueOf(this.data_evento));
            pstm.setTime(4, Time.valueOf(this.hora_inicio));
            pstm.setTime(5, Time.valueOf(this.hora_fim));
            pstm.setBoolean(6, this.realizado);
            pstm.setInt(7, this.id_espaco);
            pstm.setInt(8, this.id_usuario);
            pstm.executeUpdate();
            salvo = true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000"))
                throw new SQLException(e.getMessage());
            else
                throw new Exception("Impossível atualizar dados: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return salvo;
    }

    public ArrayList<Evento> getAll() throws Exception {
        String query = "SELECT * FROM eventos";
        this.conexao = banco.conectar();
        ArrayList<Evento> eventos = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String tipo = rs.getString(3);
                LocalDate data_evento = rs.getDate(4).toLocalDate();
                LocalTime hora_inicio = rs.getTime(5).toLocalTime();
                LocalTime hora_fim = rs.getTime(6).toLocalTime();
                Boolean realizado = rs.getBoolean(7);
                int id_espaco = rs.getInt(8);
                int id_usuario = rs.getInt(9);

                Evento e = new Evento(id, nome, tipo, data_evento, hora_inicio, hora_fim, realizado, id_espaco, id_usuario);
                eventos.add(e);
            }
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("45000"))
                throw new SQLException(ex.getMessage());
            else
                throw new SQLException("Não foi possível encontrar os eventos: " + ex.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return eventos;
    }

    public boolean update() throws Exception {
        String sql = "UPDATE eventos SET nome=?, tipo=?, data_evento=?, hora_inicio=?, hora_fim=?, realizado=?, id_espaco=? WHERE id=?";
        this.conexao = banco.conectar();
        boolean editado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setString(2, this.tipo);
            pstm.setDate(3, java.sql.Date.valueOf(this.data_evento));
            pstm.setTime(4, Time.valueOf(this.hora_inicio));
            pstm.setTime(5, Time.valueOf(this.hora_fim));
            pstm.setBoolean(6, this.realizado);
            pstm.setInt(7, this.id_espaco);
            pstm.setInt(8, this.id);
            pstm.executeUpdate();
            editado = true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("45000"))
                throw new SQLException(e.getMessage());
            else
                throw new Exception("Impossível atualizar dados: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return editado;
    }

    public boolean delete() throws Exception {
        String sql = "DELETE FROM eventos WHERE id=?";
        this.conexao = banco.conectar();
        boolean deletado = false;

        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, this.id);
            pstm.executeUpdate();
            deletado = true;
        } catch (SQLException e) {
            throw new Exception("Não foi possível deletar o evento: " + e.getMessage());
        } finally {
            this.conexao = banco.desconectar(conexao);
        }
        return deletado;
    }
}
