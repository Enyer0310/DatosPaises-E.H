package Modelo;

public class Ciudad {

    //Características --> Atributos
    private String nombre;
    private String paisPert;
    private String distrito;
    private String poblacion;
    
    //Constructores
    public Ciudad(){
    }
    
    public Ciudad(String nombre, String paisPert, String distrito, String poblacion) {
        this.nombre = nombre;
        this.paisPert = paisPert;
        this.distrito = distrito;
        this.poblacion = poblacion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisPert() {
        return paisPert;
    }

    public void setPaisPert(String paisPert) {
        this.paisPert = paisPert;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    
}
