package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    //métodos

    @Override
    public double calcularCostoMateriales(){
    if (requiereNotebook){
        return 5000;
    }
    else return 2000;
    }

    @Override
    public String getTipo(){
    return "Taller";
    }

    @Override

    public String generarCertificado (Estudiante estudiante){
        return "Se genera certificado expedido por: " + ENTIDAD_EMISORA + " para la actividad: " + getTitulo() + "  para el estudiante: " + estudiante.getNombre();

    }

    //setters y getters

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }
    public boolean getRequiereNotebook(){
        return requiereNotebook;
    }
}
