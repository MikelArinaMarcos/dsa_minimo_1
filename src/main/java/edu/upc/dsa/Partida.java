package edu.upc.dsa;

import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;

public class Partida {

    String id;
    HashMap<String, Juego> juegos;
    boolean completado;

    public Partida() {
        this.setJuegos();
        this.setId(idGenerator.getId());
        this.setNoCompletado();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Juego> getProductos() {
        return juegos;
    }

    public void setJuegos() {
        this.juegos = new HashMap<>();
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

    public void addJuego(String idGame, Juego juego){
        juegos.put(idGame, juego);
    }

    public void addPartida(String id, Juego juego) {

    }
}