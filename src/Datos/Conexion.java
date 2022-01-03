/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;

/**
 *
 * @author greco
 */
public class Conexion {
    private Connection con = null;
    private String host;
    private String user;
    private String password;

    Conexion() {
        this.host = "localhost";
        this.user = "postgres";
        this.password = "nirvana18";
    }

    
    public void openConnection() {
        String db = "calidad";
        String url_db = "jdbc:postgresql://" + this.host + ":5432/" + db;

        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url_db, this.user, this.password);
            System.out.println("Entre a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void closedConnection() {
        try {
            this.con.close();
            System.out.println("Me sali de la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al cerar: " + e.getMessage());
        }
    }

    public PreparedStatement preparedState(String query) {
        try {
            return this.con.prepareStatement(query);
        } catch (Exception e) {
            System.out.println("ERROR preparedStatemen: " + e);
            return null;
        }
    }

    public Statement crearStatement() {
        try {
            return this.con.createStatement();
        } catch (Exception e) {
            System.out.println("ERROR crearStatenen: " + e);
            return null;
        }
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.openConnection();
        con.closedConnection();
    }
}
