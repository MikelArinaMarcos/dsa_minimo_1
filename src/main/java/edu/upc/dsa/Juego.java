package edu.upc.dsa;

import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;
import java.util.Vector;

/*
    Defino la clase juego.
    Tendrá un identificador de tipo String que se generará automáticamente,
    una descripción tipo String también, y un número de niveles de tipo int.
 */
public class Juego{

    String idJuego;
    String descripcion;
    int nivelesTotales;
    //int n;

    public Juego(String descripcion, int nivelesTotales) {
        this.setId(idGenerator.getId());
        this.setDescripcion(descripcion);
        //this.setN(0);
    }

    public String getId() {
        return idJuego;
    }

    public void setId(String id) {
        this.idJuego = id;
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
}

