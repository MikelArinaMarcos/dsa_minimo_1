package edu.upc.dsa;

import edu.upc.dsa.Exceptions.juegoNoExisteException;
import edu.upc.dsa.Exceptions.usuarioNoExisteException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface GestorManager {

    public void addJuego(String nombre, int nivelesTotales);
    public List<Juego> getAllProducts();
    public Juego getJuego(String game) throws juegoNoExisteException;
    public int sizeJuegos();

    public static void sortList() {
        
    }

    public void addUser(String mail, String name, String contra) throws usuarioNoExisteException;
    public List<Usuario> getAllUsers();
    public int sizeUsers();
    public Usuario getUser(String mail) throws usuarioNoExisteException;
    public void addPartida(LinkedList<Juego> juegos, Usuario u) throws usuarioNoExisteException;
    public int sizePartida();
    public void iniciarPartida();
    //public void sortBestSellers();
    public LinkedList<Partida> partidaCompletadaUser(Usuario u)throws usuarioNoExisteException;

    List<Juego> getAllJuegos();

    void finalizarPartida();
}
