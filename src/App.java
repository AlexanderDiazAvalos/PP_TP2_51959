import exepciones.CupoExcedidoException;
import modelo.Inscripcion;
import modelo.actividades.Actividad;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //creamos eventos
        EventoUniversitario eventouni1 = new EventoUniversitario("1", "clase jueves", 500.0, false);

        Sala sala1 = new Sala(01, "pequeña");

        //asiganmos las salas
        eventouni1.asignarSala(sala1);

        //asignamos y creamos las actividades de cada evento
        eventouni1.crearActividad(01, "Taller Java", 1, "Taller", true);
        eventouni1.crearActividad(02, "Charla Java", 20, "Charla", false);

        eventouni1.crearActividad(03,"Curso de Java", 15, "Curso", false);

        //cramos a los estudiantes
        Estudiante estudiante1 = new Estudiante("51959", "Alexander");
        Estudiante estudiante2 = new Estudiante("51960", "Franco");
        Estudiante estudiante3 = new Estudiante("51961", "Santiago");

        //ahora pasamos a inscribirlos en las actividades
        //los getters para obtener la primera actividad asociada al evento
        Actividad actividad1 = eventouni1.getActividades().get(0);
        Actividad actividad2 = eventouni1.getActividades().get(1);

        Actividad actividad3 = eventouni1.getActividades().get(2);

        //la inscripcion separado en dos try y catch para que no se bloquee en el primero y siga en el segundo
        try {
            actividad1.inscribir(estudiante1);
            actividad1.inscribir(estudiante2);
        } catch (CupoExcedidoException x) {
            System.out.println("\n¡¡¡Ocurrio un error: " + x.getMessage() + "!!!");
        }
        try {
            actividad2.inscribir(estudiante2);
            actividad2.inscribir(estudiante3);
        } catch (CupoExcedidoException x) {
            System.out.println("\n¡¡¡Ocurrio un error: " + x.getMessage() + "!!!");
        }
        try {
            actividad3.inscribir(estudiante3);
            actividad3.inscribir(estudiante1);
        } catch (CupoExcedidoException x) {
            System.out.println("\n¡¡¡Ocurrio un error: " + x.getMessage() + "!!!");
        }
        //mostramos datos
        System.out.println("\nDATOS DEL EVENTO 1");
        eventouni1.mostrarDatos();

        //mostramos

        actividad1.mostrarInscripciones();
        actividad2.mostrarInscripciones();
        actividad3.mostrarInscripciones();

        System.out.println("\nCantidad total de eventos: " + EventoUniversitario.getCantidadEventos());

        //nuevo persistir y recuperar - TP2 ejercicio1

        //persistimos el evento - con el try y el catch
        try {
            eventouni1.persistir();
        } catch (FileNotFoundException x) {
            System.out.println("¡¡ No se encontró el archivo !!");
        } catch (IOException x) {
            System.out.println("¡¡ Error de entrada/salida !!");
        }
        finally {
            System.out.println("\n*Finalizo el proceso de persistir*");
        }

        //recuperamos el evento y lo metemos en eventorecuperado para poder referenciarlo nuevamente
        EventoUniversitario eventorecuperado = null;
        try {
            eventorecuperado = EventoUniversitario.recuperar();
        } catch (FileNotFoundException y) {
            System.out.println("¡¡ Error archivo no encontrado !!");
        } catch (IOException y) {
            System.out.println("¡¡ Error de entrada/salida !!");
        } catch (ClassNotFoundException y) {
            System.out.println("¡¡ Error no se encontro la clase !!");
        }
        finally {
            System.out.println("*Finalizo el proceso de recuperar*");
        }

        //mostramos datos del eventorecuperado, que son los mismos que el eventouni1
        if (eventorecuperado !=null) {
            eventorecuperado.mostrarDatos();
            //accedemos a la lista de actividades y luego mostramos las listas de inscripciones para cada actividad
            for (Actividad actividad : eventorecuperado.getActividades()) {
                actividad.mostrarInscripciones();
             }
        }
        for(Actividad actividad: eventouni1.getActividades()){
            for (Inscripcion inscripcion: actividad.getInscripciones ()){
                Estudiante estudiante = inscripcion.getEstudiante();
                if (actividad.getTipo().equals("Taller")){
                    Taller taller = (Taller) actividad;
                    System.out.println(taller.generarCertificado(estudiante));
                }
                else if (actividad.getTipo().equals("Curso")){
                    Curso curso = (Curso) actividad;
                    System.out.println(curso.generarCertificado(estudiante));
                }

            }
        }
            //TP2 Ejercicio3------------------------------

        System.out.println("\n========== EJERCICIO 3 ==========");

        // Filtramos las actividades por tipo
        List<Charla> charlas =
                eventouni1.filtrarActividadesPorTipo(Charla.class);

        List<Taller> talleres =
                eventouni1.filtrarActividadesPorTipo(Taller.class);

        List<Curso> cursos =
                eventouni1.filtrarActividadesPorTipo(Curso.class);


        // Mostramos cantidades
        System.out.println("\nCantidad de Charla: " + charlas.size());
        System.out.println("Cantidad de Taller: " + talleres.size());
        System.out.println("Cantidad de Curso: " + cursos.size());


        // Calculamos costo de materiales
        System.out.println("\nCosto de materiales de las Charlas: " + eventouni1.calcularCostoMateriales(charlas));

        System.out.println("Costo de materiales de los Talleres: " + eventouni1.calcularCostoMateriales(talleres));

        System.out.println("Costo de materiales de los Cursos: " + eventouni1.calcularCostoMateriales(cursos));

    }
}
