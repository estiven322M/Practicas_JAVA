package Datos;

import Dominio.Estudiante;
import static com.uniquindio.sistemaestudiantes.Conexion.Conexion.getConexion;
import java.sql.Connection; // Importamos la clase Connection para manejar la conexión con la base de datos.
import java.sql.PreparedStatement; // Importamos PreparedStatement para ejecutar sentencias SQL parametrizadas.
import java.sql.ResultSet; // Importamos ResultSet para manejar el resultado de consultas SQL.
import java.util.ArrayList; // Importamos ArrayList para crear listas.
import java.util.List; // Importamos List para usar una colección de estudiantes.

public class EstudianteDAO {

    // Método para listar todos los estudiantes desde la base de datos.
    public List<Estudiante> listarEstudiante() {
        List<Estudiante> estudiantes = new ArrayList<>(); // Creamos una lista para almacenar los estudiantes.
        PreparedStatement ps; // Variable para la sentencia SQL.
        ResultSet rs; // Variable para almacenar el resultado de la consulta SQL.
        Connection con = getConexion(); // Obtenemos la conexión a la base de datos.

        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante"; // Consulta SQL para obtener todos los estudiantes.
        try {
            ps = con.prepareStatement(sql); //Preparamos la consulta
            rs = ps.executeQuery(); //Ejecutamos la consulta y obtenemos el resultado
            while (rs.next()) { //Recorremos el resultado de la consulta
                var estudiante = new Estudiante(); //Creamos un nuevo objeto Estudiante
                estudiante.setIdEstudiante(rs.getInt("id_estudiante")); //Asignamos el ID o identificacion
                estudiante.setNombre(rs.getString("nombre"));//Asignamos el nombre
                estudiante.setApellido(rs.getString("apellido"));//Asignamos el apellido
                estudiante.setTelefono(rs.getString("telefono"));//Asignamos el telefono
                estudiante.setEmail(rs.getString("email"));//Asignamos el email
                estudiantes.add(estudiante);//Agregamos el estudiante a la lista
            }
        } catch (Exception e) {
            //En caso de error mostramos el mensaje
            System.out.println("Ocurrio un erro al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close(); //Se cierra la conexion a la base de datos
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion");
            }
        }
        return estudiantes; //Retornamos la lista de los estudiantes
    }

    //buscamos estudiante por ID o identificacion
    public boolean buscarEstudiantePorID(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion(); // Obtenemos la conexión.
        String sql = "SELECT * FROM estudiante WHERE id_estudiante=?"; // Consulta SQL con un parámetro para buscar por ID.
        try {
            ps = con.prepareStatement(sql); // Preparamos la consulta.
            ps.setInt(1, estudiante.getIdEstudiante()); // Asignamos el valor del ID.
            rs = ps.executeQuery();// Ejecutamos la consulta.
            if (rs.next()) { // Si encontramos un resultado, se ejecutan las lineas posteriores
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true; // Retornamos true si se encontró el estudiante.
            }
        } catch (Exception e) {  // En caso de error, mostramos un mensaje.
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close(); // Cerramos la conexión.
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerra la conexion " + e.getMessage());
            }
        }
        return false; // Si no se encuentra, retornamos false.

    }
    
    // Método para agregar un estudiante a la base de datos.
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion(); // Obtenemos la conexión.
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono,email) "+ "VALUES(?,?,?,?)"; // Consulta SQL para insertar un nuevo estudiante.
        
        try{
            ps= con.prepareStatement(sql); // Preparamos la consulta.
            ps.setString(1, estudiante.getNombre()); // Asignamos los valores correspondientes.
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute(); // Ejecutamos la consulta.
            return true; // Retornamos true si el estudiante se agregó correctamente.
        }catch(Exception e){ 
            System.out.println("Error al agregar estudiante "+e.getMessage()); // Mostramos mensaje de error.
        }
        finally{
            try{
                con.close(); // Cerramos la conexión.
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion"+e.getMessage());
            }
        }
        return false; // Retornamos false si hubo un error.
    }
    
    // Método para modificar un estudiante existente.
    public boolean modificarEstudiante (Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion(); // Obtenemos la conexión.
        String sql ="UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante =?"; // Consulta SQL para actualizar o modificar un estudiante.
        try{
            ps= con.prepareStatement(sql); // Obtenemos la conexión.
            ps.setString(1, estudiante.getNombre()); // Asignamos los valores.
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute(); // Ejecutamos la consulta.
            return true;  // Retornamos true si se actualizó correctamente.
            
            
        }catch(Exception e){
            System.out.println("Error al modifcar el estudiante "+e.getMessage());
        }
        finally{
            try{
                con.close(); // Cerramos la conexión.
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion "+e.getMessage());
            }
        }
        return false; // Retornamos false si hubo un error.
    }
    
   // Método para eliminar un estudiante.
    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion(); // Obtenemos la conexión.
        String sql ="DELETE FROM estudiante WHERE id_estudiante =?"; // Consulta SQL para eliminar un estudiante.
        
        try{
            ps= con.prepareStatement(sql); // Preparamos la consulta.
            ps.setInt(1, estudiante.getIdEstudiante()); // Asignamos el ID del estudiante a eliminar.
            ps.execute(); // Ejecutamos la consulta.
            return true; // Retornamos true si se eliminó correctamente.
        }catch(Exception e){
            System.out.println("Error al eliminar estudiante "+e.getMessage());
        }
        finally{
            try{
                con.close(); // Cerramos la conexión.
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion "+e.getMessage());
            }
        }
        return false; // Retornamos false si hubo un error.
    }

     public static void main(String[] args) {
        // Creamos una instancia de EstudianteDAO.
        var estudianteDao = new EstudianteDAO();

        // Agregar un nuevo estudiante.
        var nuevoEstudiante = new Estudiante("Pedro", "Alvarez", "456897", "pedro@email.com");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado) {
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        } else {
            System.out.println("No se pudo agregar el estudiante: " + nuevoEstudiante);
        }

        // Listar todos los estudiantes.
        List<Estudiante> estudiantes = estudianteDao.listarEstudiante();
        estudiantes.forEach(System.out::println);

        // Modificar un estudiante existente.
        var estudianteModificar = new Estudiante(1, "Jennifer", "Lorenzo", "567093", "jennifer@email.com");
        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
        if (modificado) {
            System.out.println("Estudiante modificado: " + estudianteModificar);
        } else {
            System.out.println("Estudiante no modificado: " + estudianteModificar);
        }

        // Eliminar un estudiante.
        var estudianteEliminar = new Estudiante(3);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
        if (eliminado) {
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        } else {
            System.out.println("No se pudo eliminar el estudiante: " + estudianteEliminar);
        }
    }

}
