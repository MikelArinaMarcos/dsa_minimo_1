package edu.upc.eetac.dsa;

/*
    Defino la clase juego.
    Tendrá un identificador de tipo String, una descripción tipo
    String también, y un número de niveles totales de tipo int.
 */
public class Juego {
    String idJuego;
    String descripcion;
    int nivelesTotales;

    /*
        Al crear el juego le pasaré como parámetros un identificador
        de juego, una descripción y el número de niveles que tiene el juego.
     */
    public Juego(String idJuego, String descripcion, int nivelesTotales) {
        this.idJuego = idJuego;
        this.descripcion = descripcion;
        this.nivelesTotales = nivelesTotales;
    }

    // Implementación de los getters y setters del juego
    public String getIdJuego() {
        return idJuego;
    }
    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivelesTotales() {
        return nivelesTotales;
    }
    public void setNivelesTotales(int nivelesTotales) {
        this.nivelesTotales = nivelesTotales;
    }
}

