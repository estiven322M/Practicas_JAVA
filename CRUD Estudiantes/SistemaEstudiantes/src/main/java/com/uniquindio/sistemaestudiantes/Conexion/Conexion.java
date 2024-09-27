
package com.uniquindio.sistemaestudiantes.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    /*public static Connection  getConexion(){
        Connection conexion= null;
        
        var baseDatos ="Estudiantes_DB";
        var url= "jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario = "root";
        var password= "";
        //cargamos la clase del criver de mysql en memoria
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        conexion =DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Ocurrio un error en la conexion "+e.getMessage());
        }
        
        
        
        return conexion;
    }*/
    
    public static Connection getConexion() { //metodo estatico para conectarse 
        Connection conexion = null; //objeto Connection
        String baseDatos = "Estudiantes_DB"; //nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + baseDatos; //url o direccion de la base de datos local
        String usuario = "root"; //usuario
        //String password = "root";
        
        try { //se maneja en caso de error
            conexion = DriverManager.getConnection(url, usuario,""); //utiliza el driver de JDBC 4.0á
            System.out.println("Conexión exitosa a la base de datos " + baseDatos);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        return conexion;
    }
    
    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion !=null){
            System.out.println("Conexión Exitosa!"+conexion);
        }else{
            System.out.println("Error al Conectarse");
        }
    }
    
    
}
