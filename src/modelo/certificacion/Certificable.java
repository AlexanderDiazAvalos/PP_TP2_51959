package modelo.certificacion;

import modelo.Estudiante;

public interface Certificable {

    String ENTIDAD_EMISORA = "*UNIVERSIDAD TECNOLOGICA NACIONAL - FRM*";

    String generarCertificado (Estudiante estudiante);



}
