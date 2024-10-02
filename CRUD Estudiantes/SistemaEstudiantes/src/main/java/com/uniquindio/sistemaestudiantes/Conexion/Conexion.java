
package com.uniquindio.sistemaestudiantes.Conexion;

import java.sql.Connection; //Importa la clase Connection para manejar la conexion a la BD
import java.sql.DriverManager; //Importa la clase DriverManager que permite crear conexiones JDBC
import java.sql.SQLException; //Importa SQLException para manejar los errores relacionados a la BD


public class Conexion {
    //Estas lineas comentadas son otra manera de conectarse a la BD
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
        
        
        
        return conexion; //Se retorna un objeto conexion
    }*/
    
    public static Connection getConexion() { //metodo estatico para conectarse 
        Connection conexion = null; // Declaramos la variable conexion que será el objeto que maneje la conexión.
        String baseDatos = "Estudiantes_DB"; //Nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + baseDatos; //URL o direccion de la base de datos local (localhost) puerto 3306
        String usuario = "root"; //usuario
        //String password = "root";
        
        try { //se maneja en caso de error
            // DriverManager.getConnection establece la conexión con los parámetros (URL, usuario, password).
            conexion = DriverManager.getConnection(url, usuario,""); //utiliza el driver de JDBC 4.0á
            System.out.println("Conexión exitosa a la base de datos " + baseDatos); // Mensaje de éxito si la conexión se establece.
        } catch (SQLException e) { // Si ocurre una excepción SQLException, muestra un mensaje de error y la descripción del problema.
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        return conexion; // Devuelve el objeto Connection si la conexión es exitosa, de lo contrario, devuelve null.
    }
    
    /*
     * Método main para probar la conexión.
     * Este método intenta obtener la conexión mediante el método getConexion()
     * y muestra un mensaje indicando si la conexión fue exitosa o fallida.
     */
    
    public static void main(String[] args) {
        var conexion = Conexion.getConexion(); // Llama al método getConexion para intentar conectarse.
        if(conexion !=null){
            System.out.println("Conexión Exitosa!"+conexion); // Si la conexión es exitosa, muestra un mensaje de éxito.
        }else{
            System.out.println("Error al Conectarse"); // Si la conexión falla, muestra un mensaje de error.
        }
    }
    
    
}
