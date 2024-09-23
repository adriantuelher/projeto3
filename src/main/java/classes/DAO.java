package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    private Connection conexao = null;
    private final String url = "jdbc:mysql://localhost:3306/eventos";
    private final String user = "root";
    private final String password = "12345678";

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão efetuada com sucesso!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e);
            conexao = null;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado: " + e);
            conexao = null;
        }
        return conexao;
    }

    public Connection desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Não foi possível encerrar a conexão! Erro: " + e);
            } finally {
                conexao = null;
            }
        }
		return conexao;
    }
}