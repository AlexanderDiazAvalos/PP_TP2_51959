package modelo.actividades;

import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.certificacion.Certificable;

public class Curso extends Actividad implements Certificable {
    //atributos de la clase
    private int nivel;

    //constructor
    public Curso (int id, String titulo, int cupoMaximo) {
        super (id, titulo, cupoMaximo);
    }

    //metodos de la clase
    @Override
    public double calcularCostoMateriales (){
        return 0;
    }

    @Override
    public String getTipo () {
        return "Curso";
    }

    @Override
    public String generarCertificado (Estudiante estudiante){

        return "Se genera certificado expedido por: " + ENTIDAD_EMISORA + " para la actividad: " + getTitulo() + "  para el estudiante: " + estudiante.getNombre();
    }

    //metodo de la interface certificable

}
