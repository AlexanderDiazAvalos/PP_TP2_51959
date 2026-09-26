
package modelo.actividades;
//hacemos esto para poder utilizar la lista de insscripciones que pide el ejercicio

import exepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {

    private int id;
    private String titulo;
    private int cupoMaximo;

    //seteo el cupo minimo, para que funcione, ya que actividad reecibe solo 3 valores del evento universitario
    public static final int cupoMinimo = 1;


    //agregamos la lista como atributo de las instancias de activdad, para que cada actividad tenga su lista
    private List<Inscripcion> inscripciones;



    //Constructor
    public Actividad (int id, String titulo, int cupoMaximo){
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;


        //agregamos la lista en el constructor para que se inicialice para cada instancia creada
        this.inscripciones = new ArrayList<>();

    }

    //metodos

    //el metodo de inscripcion de alumno - ejer2 , ppunto 1 : ahora puede arrojar una exception
    public Inscripcion inscribir (Estudiante estudiente) throws CupoExcedidoException{
        if (cupoMaximo <= this.inscripciones.size()) {
            throw new CupoExcedidoException("Ya se alcanzó el maximo de cupos para esta actividad: " + titulo);
        }
            //le pasamos a la clase inscripcion los datos
        Inscripcion inscripcion = new Inscripcion(LocalDate.now(), "activo", estudiente, this);

        //guardamos la la inscricion en la lista
        inscripciones.add(inscripcion);
        return inscripcion; //como defini modelo.Inscripcion inscribir (---) debe volver un objeto modelo.Inscripcion
    }

    //mostrar inscripciones
    public void mostrarInscripciones() {
        System.out.println("\nLISTA DE INSCRIPCIONES: ");
        System.out.println("Actividad: "+ titulo);

        if (inscripciones.isEmpty()) {
            System.out.println("Aun no hay inscripciones");
        }

            for (Inscripcion inscripcion : inscripciones) {

                System.out.println("\nEstudiante: " + inscripcion.getEstudiante().getNombre());
                System.out.println("Nro. de Legajo: " + inscripcion.getEstudiante().getLegajo());
                System.out.println("Fecha de inscripcion: " + inscripcion.getFecha());
                System.out.println("Estado de Inscripcion: " + inscripcion.getEstado());
            }
        if (inscripciones.size() < cupoMinimo) {
            System.out.println("No supera el cupo mínimo");
        }

        System.out.println("\n*FIN LISTA DE INSCRIPCIONES*");
        System.out.println("----------------------------------------------");


    }

//    //mostrar Identificación
//    public final void mostrarIdentificacion (){
//        System.out.println("ID: " + id);
//        System.out.println("Titulo: " + titulo);
//    }

    //calcular costo de materiales
    public abstract double calcularCostoMateriales ();



    //usamos este gettipo para recorrer la lista de actividades y de inscripciones -
    //gettipo de tipo abstract obligando a toda la clase modelo.actividades.Actividad a volverse abstract
    public abstract String getTipo ();


    //nuevo persistir, hacemos que tenga throws de ioexception para que app pueda catchearlo y escribir que pasa - TP2 ejercicio1
    public void persistir() throws IOException{

            FileOutputStream fos = new FileOutputStream("F:\\PPUTN\\Paradigamas de Programación\\Elementos Unidad 1\\Prueba 1\\TP2 Ejercicio 3\\gaurdado de prueba eventouniversitario\\" + titulo + ".tata");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();

    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    //seters y geters
    public void setTitulo (String titulo){
        this.titulo = titulo;
    }
    public String getTitulo(){
        return titulo;
    }

    public void setCupoMaximo (int cupoMaximo){
        this.cupoMaximo = cupoMaximo;
    }
    public int getCupoMaximo(){
        return cupoMaximo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCupoMinimo() {
        return cupoMinimo;
    }
}
