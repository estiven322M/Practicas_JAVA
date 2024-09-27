package Datos;

import Dominio.Estudiante;
import static com.uniquindio.sistemaestudiantes.Conexion.Conexion.getConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public List<Estudiante> listarEstudiante() {
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();

        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un erro al seleccionar datos: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion");
            }
        }
        return estudiantes;
    }

    //buscamos estudiante por ID
    public boolean buscarEstudiantePorID(Estudiante estudiante) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar estudiante: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerra la conexion " + e.getMessage());
            }
        }
        return false;

    }
    
    //metodo para agregar estudiante
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono,email) "+ "VALUES(?,?,?,?)";
        
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al agregar estudiante "+e.getMessage());
        }
        finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion"+e.getMessage());
            }
        }
        return false;
    }
    
    //metodo modificar estudiante
    public boolean modificarEstudiante (Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql ="UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante =?";
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
            
            
        }catch(Exception e){
            System.out.println("Error al modifcar el estudiante "+e.getMessage());
        }
        finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion "+e.getMessage());
            }
        }
        return false;
    }
    
    //metodo para eliminar estudiante
    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql ="DELETE FROM estudiante WHERE id_estudiante =?";
        
        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar estudiante "+e.getMessage());
        }
        finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar la conexion "+e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //listamos todos los estudintes
        var estudianteDao = new EstudianteDAO();
        System.out.println("Listado de Estudiantes: ");
        
        
        //buscar por ID
        /*var estudiante1= new Estudiante (2);
        System.out.println("Estudiante antes de la busqueda");
        var encontrado = estudianteDao.buscarEstudiantePorID(estudiante1);
        if(encontrado){
            System.out.println("Estudiante encontrado: "+estudiante1);
            
        }else{
            System.out.println("No se encontro estudiante: "+estudiante1.getIdEstudiante());
        }*/
        
        //Agregar Estudiante
        var nuevoEstudiante = new Estudiante("Pedro","Alvarez","456897","pedro@email.com");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if(agregado){
            System.out.println("Estudiante agregado "+nuevoEstudiante);
        }else{
            System.out.println("No se pudo agregar estudiante "+ nuevoEstudiante);
        }
        List<Estudiante> estudiantes = estudianteDao.listarEstudiante();
        estudiantes.forEach(System.out::println);
        
        //Modificamos un estudiante
        
        var estudianteModificar = new Estudiante (1, "Jennifer", "Lorenzo","567093","jennifer@email.com");
        var modificado =estudianteDao.modificarEstudiante(estudianteModificar);
        if(modificado){
            System.out.println("Estudiante modificado "+estudianteModificar);
        }else{
            System.out.println("Estudiante no modificado "+estudianteModificar);
        }
        
        List<Estudiante> estudiantes1 = estudianteDao.listarEstudiante();
        estudiantes.forEach(System.out::println);
        
        //Eliminar un estudiante
        var estudianteEliminar = new Estudiante (3);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
        if(eliminado){
            System.out.println("Estudiante eliminado "+estudianteEliminar);
        }else{
            System.out.println("No se pudo eliminar estudiante: "+estudianteEliminar);
        }
        
        List<Estudiante> estudiantes3 = estudianteDao.listarEstudiante();
        estudiantes.forEach(System.out::println);
    }

}
