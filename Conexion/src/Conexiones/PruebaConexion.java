package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;

public class PruebaConexion {
	
	public static void main(String[] args) {
		//Statement myStatement = null;
		//Connection conn= null;
		//ResultSet myResultset=null;
		
		String url = "jdbc:mysql://localhost:3306/Proyecto?serverTimezone=UTC";
        String user = "root";    // Usuario por defecto de XAMPP
        String password = "";    // Contraseña por defecto de XAMPP (vacío)

     // Usando try-with-resources para asegurar que todos los recursos se cierran
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            if (conn != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");

                // Insertando datos en la tabla "employees"
                String sqlInsert = "INSERT INTO employees (first_name, pa_surname) VALUES (?, ?)";
                try (PreparedStatement insertStatement = conn.prepareStatement(sqlInsert)) {
                    insertStatement.setString(1, "John");
                    insertStatement.setString(2, "Doe");
                    int rowsInserted = insertStatement.executeUpdate();
                    System.out.println("Filas insertadas: " + rowsInserted);
                    
                    if(rowsInserted >0) {
                    	System.out.println("Se ha credo un nuevo cliente");
                    }
                }
                
                //Eliminando un empleado con Delete
                String sqlDelete = "DELETE FROM employees WHERE first_name = ?";
                try(PreparedStatement deleteStatement=conn.prepareStatement(sqlDelete) ){
                	deleteStatement.setString(1, "Jane");
                	int rowsDelete = deleteStatement.executeUpdate();
                	System.out.println("filas eliminadas "+rowsDelete);
                	
                	if(rowsDelete >0) {
                		System.out.println("Se elimino un empleado");
                	}else {
                		System.out.println("No se encontro el empleado");
                	}
                }
                

                // Haciendo un SELECT para consultar los datos
                String sqlSelect = "SELECT * FROM employees";
                try (PreparedStatement selectStatement = conn.prepareStatement(sqlSelect);
                     ResultSet myResultset = selectStatement.executeQuery()) {

                    while (myResultset.next()) {
                        System.out.println(myResultset.getString("first_name") + " " + myResultset.getString("pa_surname"));
                    }
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Algo salio mal");
        }
	}

}
