package Modelo;

public class Idioma {

    private String idioma;
    private boolean idiomaOficial;
    private double porcPoblacion;
    
    //Constructores
    public Idioma(){
    }
    
    public Idioma(String idioma, boolean idiomaOficial, double porcPoblacion) {
        this.idioma = idioma;
        this.idiomaOficial = idiomaOficial;
        this.porcPoblacion = porcPoblacion;
    }
    
     public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isIdiomaOficial() {
        return idiomaOficial;
    }

    public void setIdiomaOficial(boolean idiomaOficial) {
        this.idiomaOficial = idiomaOficial;
    }

    public double getPorcPoblacion() {
        return porcPoblacion;
    }

    //Características --> Atributos
    public void setPorcPoblacion(double porcPoblacion) {
        this.porcPoblacion = porcPoblacion;
    }
    
   
}
