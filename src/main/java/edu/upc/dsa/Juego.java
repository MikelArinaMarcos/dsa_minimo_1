package edu.upc.dsa;

import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;
import java.util.Vector;

public class Juego{

    String idJuego;
    String descripcion;
    int nivelesTotales;
    //int n;

    public Juego(String descripcion, int nivelesTotales) {
        this.setId(idGenerator.getId());
        this.setDescripcion(descripcion);
        this.setNivelesTotales(nivelesTotales);
        //this.setN(0);
    }

    public String getId() {
        return idJuego;
    }

    public void setId(String id) {
        this.idJuego = id;
    }

    public int getNivelesTotales() {
        return nivelesTotales;
    }

    public void setNivelesTotales(int nivelesTotales) {
        this.nivelesTotales = nivelesTotales;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /*
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void addN(){
        this.n++;
    }
    */
}

