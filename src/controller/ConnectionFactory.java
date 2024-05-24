package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public static String status = "Didn't connect...";

    // Método de Conexão
    public static Connection getConnectionMySQL() {

        Connection connection = null;

        try {
            // Configurando a nossa conexão com um banco de dados
            String driverName = "com.mysql.cj.jdbc.Driver"; // Classe do driver atualizada
            String serverName = "localhost:3306";    // caminho do servidor do BD
            String mydatabase = "odsmanager";               // nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root";                // nome de um usuário de seu BD      
            String password = "admin";               // sua senha de acesso

            // Carregar a classe do driver
            Class.forName(driverName);

            // Estabelecer a conexão
            connection = DriverManager.getConnection(url, username, password);

            // Testa sua conexão
            if (connection != null) {
                status = "STATUS--->successfully connected!";
            } else {
                status = "STATUS--->could not connect!";
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("O driver especificado não foi encontrado.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Não foi possível conectar ao Banco de Dados.");
            e.printStackTrace();
            return null;
        }
    }

    // Método que retorna o status da sua conexão
    public static String statusConection() {
        return status;
    }

    // Método que fecha sua conexão
    public static boolean closeConnection() {
        try {
            getConnectionMySQL().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método que reinicia sua conexão
    public static Connection restartConnection() {
        closeConnection();
        return getConnectionMySQL();
    }
}