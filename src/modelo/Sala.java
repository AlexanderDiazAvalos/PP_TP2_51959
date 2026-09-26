package modelo;

import java.io.*;

public class Sala implements Serializable {

    //atributos de las estancias
    private int id;
    private String nombre;

    //Constructor
    public Sala (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    //seters y geters

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
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
