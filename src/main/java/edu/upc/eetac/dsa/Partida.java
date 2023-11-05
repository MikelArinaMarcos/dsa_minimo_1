package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.util.RandomUtils;

import java.util.HashMap;

public class Partida {
    /*
    Defino la clase Partida.
    Tendrá un identificador de tipo String, un identificador del usuario
    de tipo String, un identificador del juego de tipo String, una variable
    de tipo Integer para almacenar los puntos, otra variable Integer para
    almacenar el nivel actual en el que se encuentra y una variable tipo
    String para almacenar la fecha en la que se produce un cambio de nivel.
 */

    String idPartida; // Identificador de la partida.
    String idUsuario; // Identificador del usuario que realiza la partida.
    String idJuego; // Identificador del juego.
    Integer puntos; // Puntos que tiene el usuario.
    Integer nivelActual; // Nivel actual del usuario.
    String fecha; // Fecha en la que se cambia de nivel.

    /*
        Al crear una nueva partida, necesitaré pasarle el identificador
        del usuario y el del juego, crearé un identificador de partida
        aleatorio, fijaré los puntos a 50 y el nivel actual a 1.
     */
    public Partida(String idUsuario, String idJuego, String fecha) {
        this.idUsuario = idUsuario;
        this.idJuego = idJuego;
        this.idPartida = RandomUtils.getId();
        this.puntos = 50;
        this.nivelActual = 1;
        this.fecha = fecha;
    }

    /*
    Creación de los getters i setters para obtener y crear todos
    los datos de las partidas.
 */
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdPartida() {
        return idPartida;
    }
    public void setIdPartida(String idPartida) {
        this.idPartida = idPartida;
    }

    public String getIdJuego() {
        return idJuego;
    }
    public void setIdJuego(String idJuego) {
        this.idJuego = idJuego;
    }

    public Integer getPuntos() {
        return puntos;
    }
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getNivelActual() {
        return nivelActual;
    }
    public void setNivelActual(Integer nivelActual) {
        this.nivelActual = nivelActual;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Sumamos puntos al usuario
    public void sumamosPuntos(Integer p) {
        this.puntos = this.puntos + p;
    }
}