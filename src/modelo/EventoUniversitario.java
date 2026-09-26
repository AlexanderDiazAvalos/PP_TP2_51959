package modelo;

import java.io.*;
import java.io.Serializable;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.util.ArrayList;
import java.util.List;

//agregue el serializable para poder serializar el objeto eventouniversitario

public class EventoUniversitario implements Serializable {

    //Atributos de cada instancia de eventouniversitario

    public final String id;
    private String titulo;
    private Double costoBase;
    private Boolean gratuito;

    //agregar lo nuevo
    private Sala sala;
    private List<Actividad> actividades;

    //Atributo de la clase, compartido por todas las instancias

    private static int cantidadEventos;

    //contador para cantidad de eventos estatico
    static {
        cantidadEventos = 0;
    }

    //Constructor de evento
    public EventoUniversitario(String id, String titulo, Double costoBase, Boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        ++cantidadEventos;
        //Agragamos la lista para las actividades
        this.actividades = new ArrayList<>();
    }

    //METODOS

    //calcular el costo estimado
    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }

        double costoTotal = costoBase;

        for (Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }

        return costoTotal * 1.21;
    }

    //asignar sala
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }


    //crear actividad
    public void crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook) {

        Actividad actividad;
        //dependiendo del tipo de activdad:
        if (tipo.equals("Charla")) {
            actividad = new Charla(id, titulo, cupo);
        }
        else if (tipo.equals("Taller")) {
            actividad = new Taller(id, titulo, cupo, requiereNotebook);
        }
        else if (tipo.equals("Curso")) {
            actividad = new Curso(id, titulo, cupo);
        }
         else{   System.out.println("Actividad no válida");
            return;
        }
        actividades.add(actividad);

    }


    //mostrar datos
    public void mostrarDatos() {
        System.out.println("\n    Id: " + id);
        System.out.println("    Titulo: " + titulo);
        System.out.println("    Costo estimado: " + calcularCostoEstimado());
        System.out.println("    Gratuito: " + gratuito);

        //agregamos las salas a los datos mostrados, pero como puede no tener valor, hay que tenerlo en cuenta
        if (sala != null) {
            System.out.println("    Sala para el evento: " + getSala().getId() + " - " + getSala().getNombre());
        } else {
            System.out.println("    Aun no hay sala asignada");
        }
        System.out.println("--------------------------");


        //actividades del evento:
        System.out.println("\nActividades del evento:");

        for (Actividad actividad : actividades) {
            System.out.println("- " + actividad.getTitulo());
        }

    }
    //setters y getters

    //id
    public String getId() {
        return id;
    }

    //titulo
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    //costo base
    public void setCostoBase(Double costoBase) {
        this.costoBase = costoBase;
    }

    public Double getCostoBase() {
        return costoBase;
    }

    //gratuito
    public void setGratuito(Boolean gratuito) {
        this.gratuito = gratuito;
    }

    public Boolean getGratuito() {
        return gratuito;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    //para las salas
    public Sala getSala() {
        return sala;
    }

    //para la lista de actividades
    public List<Actividad> getActividades() {
        return actividades;
    }

    public void persistir() throws IOException{

        FileOutputStream fos = new FileOutputStream("gaurdado de prueba eventouniversitario\\" + titulo + ".tata");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();

    }

    public static EventoUniversitario recuperar() throws IOException, ClassNotFoundException{

        FileInputStream fis = new FileInputStream("gaurdado de prueba eventouniversitario\\" + "clase jueves.tata");
        ObjectInputStream ois = new ObjectInputStream(fis);
        EventoUniversitario obj = (EventoUniversitario)
            ois.readObject();
            ois.close();
            fis.close();
            return obj;

    }

    //TP2 Ejercicio 3 -------------------------------------

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {

        List<T> resultado = new ArrayList<>();

        for (Actividad actividad : actividades) {
            if (tipo.isInstance(actividad)) {
                resultado.add(tipo.cast(actividad));
            }
        }

        return resultado;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividades) {

        double total = 0;

        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();
        }

        return total;
    }
}
