package Presentacion;

import Datos.EstudianteDAO;
import java.util.Scanner;

public class SistemaEstudiantesApp {

    public static void main(String[] args) {

        var salir = false;
        var consola = new Scanner(System.in);

        //se crea una instancia de clase servicio
        var estudianteDao = new EstudianteDAO();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            } catch (Exception e) {
                System.out.println("Ocurrio un error al ejecutar operacion" + e.getMessage());
            }
            System.out.println();
            //fin del while

        }
        
        public static void mostrarMenu (){
            System.out.println("""
                               ***Sistema de Estudiantes ***
                               1. Listar Estudiantes
                               2. Buscar Estudiante
                               3. Agregar Estudiante
                               4. Modificar Estudiante
                               5. Eliminar Estudiante
                               6. Salir
                               Elige una opcion
                               """);
        }
        
        private static boolean ejecutarOpciones (Scanner consola, EstudianteDAO estudiantDao){
            var opcion =Integer.parseInt(consola.nextLine());
            var salir =false;
            
            switch(opcion){
                case 1 -> {//Listar estudiantes
                    System.out.println("Listado de estudiantes: ");
                    var estudiantes= estudianteDao.listarEstudiantes();
                    estudiantes.forEach(System.out::println);
                }
                case 2 ->{//buscar estudiante por ID
                    System.out.println("Introduce el id_estudiante a buscar: ");
                    var idEstudiante = Integer.parseInt(consola.nextLine());
                    var estudiante = new Estudiante(idEstudiante);
                    var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                    if(encontrado){
                        System.out.println("Estudiante encontrado: "+estudiante);
                        
                    }
                }
            }
        }

    }

}
