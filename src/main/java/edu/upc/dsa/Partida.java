package edu.upc.dsa;

import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;

public class Comanda {

    String id;
    HashMap<String, Producto> productos;
    int precioT;
    boolean completado;

    public Comanda() {
        this.setProductos();
        this.setId(idGenerator.getId());
        this.setNoCompletado();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Producto> getProductos() {
        return productos;
    }

    public void setProductos() {
        this.productos = new HashMap<>();
    }

    public int getPrecioT() {
        return precioT;
    }

    public void setPrecioT(int precioT) {
        this.precioT = precioT;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setNoCompletado() {
        this.completado = false;
    }

    public void setCompletado(){
        this.completado = true;
    }

    public void addProducto(String idProd, Producto producto){
        productos.put(idProd, producto);
    }
}

