package modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Estudiante implements Serializable {

    private String legajo;
    private String nombre;

    //Constructor de alumno
    public Estudiante  (String legajo, String nombre){
        this.legajo = legajo;
        this.nombre = nombre;

    }
    //seter y geters

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    public String getLegajo(){
        return legajo;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void persistir () throws IOException{
            FileOutputStream fos = new FileOutputStream("F:\\PPUTN\\Paradigamas de Programación\\Elementos Unidad 1\\Prueba 1\\TP2 Ejercicio 3\\gaurdado de prueba eventouniversitario\\" + nombre + ".tata");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();


    }
}
