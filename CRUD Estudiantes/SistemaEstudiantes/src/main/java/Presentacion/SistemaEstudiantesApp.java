package Presentacion;

import Datos.EstudianteDAO;
import Dominio.Estudiante;
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

    }

    public static void mostrarMenu() {
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

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDao) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;

        switch (opcion) { //listado del MENU OPCIONES
            case 1 -> {//Listar estudiantes
                System.out.println("Listado de estudiantes: ");
                var estudiantes = estudianteDao.listarEstudiante();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {//buscar estudiante por ID
                System.out.println("Introduce el id_estudiante a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorID(estudiante);
                if (encontrado) {
                    System.out.println("El estudiante fue encontrado: " + estudiante);

                }else{
                    System.out.println("El estudiante no fue encontrado"+estudiante);
                }
            }
            case 3 -> { //Agregar estudiante
                System.out.println("Agregar Estudiante: ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email= consola.nextLine();
                
                // Se crea el objeto estudiante 
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);
                if(agregado){
                    System.out.println("El estudiante fue agregado : "+estudiante);
                }else{
                    System.out.println("El estudiante no fue agregado: "+estudiante);
                }
            }
            case 4 ->{ //Modificar Estudiante
                System.out.println("Modificar Estudiante");
                System.out.println("Identificacion del estudiante: ");
                var identificacion =Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido=consola.nextLine();
                System.out.print("telefono: ");
                var telefono= consola.nextLine();
                System.out.print("Email: ");
                var email= consola.nextLine();
                
                //creamos el Objeto Estudiante
                var estudiante = new Estudiante(identificacion, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificarEstudiante(estudiante);
                if(modificado){
                    System.out.println("El estudiante fue modificado :"+estudiante);
                }else{
                    System.out.println("El estudiante no pudo ser modificado: "+estudiante);
                }
                
            }
            case 5 ->{ //Eliminar estudiante
                System.out.println("Eliminar Estudiante: ");
                System.out.println("Identificacion del estudiante: ");
                var identificacion = Integer.parseInt(consola.nextLine());
                var estudiante= new Estudiante(identificacion);
                var eliminado= estudianteDao.eliminarEstudiante(estudiante);
                if(eliminado){
                    System.out.println("El estudiante fue eliminado: "+estudiante);
                }else{
                    System.out.println("El estudiante no fue eliminado: "+estudiante);
                }
            }
            case 6 ->{
                System.out.println(" Salir! ");
                salir = true;
            }
            default ->{
                System.out.println("La opcion elegida no fue reconocida, intente de nuevo");
            }
        }
        return salir;
    }

}
