package modelo.actividades;

public class Charla extends Actividad {

        private String disertante;

        //constructor
        public Charla (int id, String titulo, int cupoMaximo){
            super (id, titulo, cupoMaximo);
        }

        //métodos
        @Override
        public double calcularCostoMateriales(){
            return 0;
        }

        @Override
        public String getTipo (){
        return "Charla";
        }


        //setters y getters
        public void setDisertante(String disertante) {
            this.disertante = disertante;
        }
        public String getDisertante() {
            return disertante;
        }
    }
