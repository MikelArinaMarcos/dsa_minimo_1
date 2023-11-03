package edu.upc.dsa;

import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;
import java.util.Vector;

public class Producto{

    String id;
    int precio;
    String nombre;
    int n;

    public Producto(int precio, String nombre) {
        this.setId(idGenerator.getId());
        this.setPrecio(precio);
        this.setNombre(nombre);
        this.setN(0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void addN(){
        this.n++;
    }

}
