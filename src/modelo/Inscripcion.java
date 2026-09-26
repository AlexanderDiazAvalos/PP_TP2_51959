package modelo;

import modelo.actividades.Actividad;

import java.io.*;
import java.time.LocalDate;

public class Inscripcion implements Serializable {

    private LocalDate fecha;
    private String estado;

    //relacion con estudiante y actividad
    private Estudiante estudiante;
    private Actividad actividad;

    //Constructor
    public Inscripcion (LocalDate fecha, String estado, Estudiante estudiante, Actividad actividad) {
        this.fecha = fecha;
        this.estado = estado;
        this.estudiante = estudiante;
        this.actividad = actividad;

    }



    //setters y getters

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado() {
        return estado;
    }

    public void persistir () throws IOException{
        FileOutputStream fos = new FileOutputStream("F:\\PPUTN\\Paradigamas de Programación\\Elementos Unidad 1\\Prueba 1\\TP2 Ejercicio 3\\gaurdado de prueba eventouniversitario\\" + estudiante + ".tata");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
        fos.close();


    }
//agregamos los seter y geters de estudiante y actividad

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    public Actividad getActividad() {
        return actividad;
    }

}
