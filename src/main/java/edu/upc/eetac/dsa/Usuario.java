package edu.upc.eetac.dsa;

import java.util.HashMap;

/*
    Defino la clase usuario que será el jugador.
    Tendrá un identificador de tipo String, un historial que guardará
    todas las partidas que el usuario lleva en un HashMap, y una variable
    booleana para saber si el usuario está jugando o no y evitar que
    pueda estar en dos partidas a la vez
 */
public class Usuario {

    String idUsuario; // Identificador del usuario.
    HashMap<String, Partida> partidas; // Historial de partidas del usuario.
    Boolean jugando; // Variable para saber si está jugando actualmente.

    /*
        Al crear un usuario, necesitaré pasarle el identificador
        del usuario, crearé una lista con las partidas de ese jugador a
        través de un HashMap y crearé una variable booleana a false que
        indicará si el usuario está actualmente en una partida o no.
     */
    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
        this.jugando = false;
        this.partidas = new HashMap<>();
    }

    /*
        Creación de los getters i setters para obtener y crear todos
        los datos de los usuarios.
     */
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public HashMap<String, Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(HashMap<String, Partida> partidas) {
        this.partidas = partidas;
    }

    public Boolean getJugando() {
        return jugando;
    }
    public void setJugando(Boolean jugando) {
        this.jugando = jugando;
    }

    /*
        Inicio de una partida, a través de su identificador y fijando
        la variable jugando a True.
     */
    public void addPartida(Partida partida){
        this.partidas.put(partida.idPartida, partida);
        this.jugando = true;
    }
}
