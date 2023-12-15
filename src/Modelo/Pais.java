package Modelo;

public class Pais {
    
    //Características --> Atributos
    private String codigo;
    private String nombre;
    private String continente;
    private int poblacion;
    private String observacion;
    
    //Constructores
    public Pais(){
    }
    
    public Pais(String code, String name, String continent, int population, String observ){
        this.codigo = code;
        this.nombre = name;
        this.continente = continent;
        this.poblacion = population;
        this.observacion = observ;
    }
    
    //Accesadores y mutadores.
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setCodigo(String code){
        this.codigo = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}