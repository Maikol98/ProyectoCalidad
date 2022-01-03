/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.sql.PreparedStatement;

/**
 *
 * @author greco
 */
public class Transaccion {
    private int id;
    private int cuentaOrigen;
    private String nombreOrigen;
    private int cuentaDestino;
    private String NombreDestino;
    private double monto;
    private int prioridad;
    private Conexion con;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getNombreDestino() {
        return NombreDestino;
    }

    public void setNombreDestino(String NombreDestino) {
        this.NombreDestino = NombreDestino;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Transaccion() {
        this.con = new Conexion();
    }

    public Transaccion(int cuentaOrigen, String nombreOrigen, int cuentaDestino, String NombreDestino, double monto, int prioridad) {
        this.con = new Conexion();
        this.cuentaOrigen = cuentaOrigen;
        this.nombreOrigen = nombreOrigen;
        this.cuentaDestino = cuentaDestino;
        this.NombreDestino = NombreDestino;
        this.monto = monto;
        this.prioridad = prioridad;
    }
    
    
    
    //INSERTAR
    public boolean insertar() {
        try {
            con.openConnection();
            String query = "INSERT INTO transacciones (cuentaorigen,nombreorigen,cuentadestino,nombredestino,monto,prioridad) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.preparedState(query);
            preparedStmt.setInt(1, this.cuentaOrigen);
            preparedStmt.setString(2, this.nombreOrigen);
            preparedStmt.setInt(3, this.cuentaDestino);
            preparedStmt.setString(4, this.NombreDestino);
            preparedStmt.setDouble(5, this.monto);
            preparedStmt.setInt(6, this.prioridad);
            preparedStmt.execute();
            con.closedConnection();
            return true;
        } catch (Exception e) {
            con.closedConnection();
            System.out.println("Transaccion error insertar: " + e);
            return false;
        }
    }
    
    
    //LISTAR
    public LinkedList<Transaccion> listar(){
        LinkedList<Transaccion> lista = new LinkedList<>();
        Transaccion transaccion = new Transaccion();
        try {
            con.openConnection();
            String query = "SELECT * FROM transacciones ";
            Statement consulta = con.crearStatement();
            ResultSet rs = consulta.executeQuery(query);
            while (rs.next()) {
                transaccion.id = rs.getInt("id");
                transaccion.cuentaOrigen = rs.getInt("cuentaorigen");
                transaccion.nombreOrigen = rs.getString("nombreorigen");
                transaccion.cuentaDestino = rs.getInt("cuentadestino");
                transaccion.NombreDestino = rs.getString("nombredestino");
                transaccion.monto = rs.getDouble("monto");
                transaccion.prioridad = rs.getInt("prioridad");
                
                lista.add(transaccion);
                transaccion = new Transaccion();
            }
            con.closedConnection();
            return lista;
        } catch (Exception e) {
            con.closedConnection();
            System.out.println("ERROR TRANSACCION " + e);
            return null;
        }
    }
    
    public static void main(String[] args) {
        
    }
}
