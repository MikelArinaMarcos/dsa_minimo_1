package edu.upc.dsa;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import  edu.upc.dsa.Exceptions.juegoNoExisteException;
import  edu.upc.dsa.Exceptions.usuarioNoExisteException;

import java.util.*;
import java.util.stream.Collectors;

public class GestorManagerImpl implements GestorManager{

    private static GestorManager instance;
    protected Map<String, Usuario> lUsuarios;
    protected Queue<Partida> cPartidas;
    protected List<Juego> lJuegos;

    final static Logger logger = Logger.getLogger(GestorManagerImpl.class);

    public GestorManagerImpl() {
        this.lUsuarios = new HashMap<>();
        this.cPartidas = new LinkedList<>();
        this.lJuegos = new LinkedList<Juego>();
    }

    public static GestorManager getInstance() {
        if (instance==null) instance = new GestorManagerImpl();
        return instance;
    }

    @Override
    public void addJuego(String nombre, int nivelesTotales) {

    }

    @Override
    public List<Juego> getAllProducts() {
        return null;
    }

    public Juego getJuego(String prod) throws juegoNoExisteException{
        for(Juego juego : lJuegos){
            if(juego.getId().equals(juego)){
                return juego;
            }
        }
        throw new juegoNoExisteException();
    }

    @Override
    public int sizeJuegos() {
        return 0;
    }

    // Lista de todos los juegos.
    public List<Juego> getAllJuegos(){
        return  this.lJuegos;
    }

    // Creación de una partida de un juego.
    public void addPartida(String idJuego, int numNiveles){
        try{
            Juego id = getJuego(idJuego);
            logger.info("Juego ya existe" + id);
        }
        catch (juegoNoExisteException e){
            Juego j = new Juego(idJuego, numNiveles);
            lJuegos.add(j);
        }
    }

    public int sizePartidas(){
        return this.sizePartidas();
    }

    public void sortList(){
        lJuegos.sort((Juego j1, Juego j2) -> Integer.compare(j1.getPuntos(),(j2.getPuntos())));
        for(Juego producto : lJuegos){
            logger.info("Usuario: " + producto.getId() + " - Puntos: " + producto.getPuntos());
        }
    }
/*
    public void sortBestSellers(){
        lProductos.sort((Producto o1, Producto o2) -> Integer.compare(o2.getN(),(o1.getN())));
        for(Producto producto : lProductos){
            logger.info("Producto: " + producto.getNombre() + " - Veces comprado: " + producto.getN());
        }
    }
*/
    public Usuario getUser(String mail) throws usuarioNoExisteException{
        if(lUsuarios.get(mail) == null){
            throw new usuarioNoExisteException();
        }
        return lUsuarios.get(mail);
    }

    public List<Usuario> getAllUsers(){
        return this.lUsuarios.values().stream().toList();
    }

    public void addUser(String mail, String name, String contra) {
        logger.info("Creating a new user: " + name);
        try{
            getUser(mail);
            logger.info("Correo ya en uso!");
        }catch (usuarioNoExisteException e){
            Usuario u = new Usuario(mail, name, contra);
            lUsuarios.put(mail, u);
            logger.info("Usuario creado con exito!");
        }
    }

    // Consulta de las partidas realizadas por un usuario.
    public LinkedList<Partida> comandaCompletadaUser(Usuario u) throws usuarioNoExisteException{
        LinkedList<Partida> partidasU = new LinkedList<>();
        logger.info("Partidas completadas de " + u.getName());
        for (Map.Entry<String, Partida> set : u.partidas.entrySet()) {
            if (set.getValue().isCompletado()) {
                partidasU.add(set.getValue());
                logger.info("id: " + set.getValue().getId());
            }
        }
        if (partidasU.isEmpty()){
            return null;
        }
        return partidasU;
    }

    public int sizeUsers(){
        logger.info("Numero de usuarios en la lista: " + this.lUsuarios.size());
        return this.lUsuarios.size();
    }

    // Generamos una partida nueva en la cual el usuario empieza en el nivel 1 y con 50 puntos.
    public void addPartida(LinkedList<Juego> juegos, Usuario u) throws usuarioNoExisteException {
        int puntuacion = 50;
        int nivel = 1;
        if(u == null){
            logger.info("Usuario no existe");
            return;
        }
        Partida partida = new Partida();
        logger.info("Generando partida:");
        for(Juego juego : juegos){
            logger.info("Juego: " + juego.getId() + " - Puntos: " + juego.getPuntos());
            puntuacion = puntuacion + juego.getPuntos();
            //juego.addN();
            partida.addPedido(juego.getId(), juego);
        }
        partida.setPrecioT(nivel);
        cPartidas.add(partida);
        logger.info("Generada partida con id: " + partida.getId() + " - Nivel partida: " + nivel);
        u.addPartida(partida.getId(), partida);
    }

    // Finalizamos la partida.
    public void finalizarPartida(){
        Partida p = cPartidas.remove();
        logger.info("Partida completada con id: " + p.getId());
        p.setCompletado();
    }

    // Número de partidas en la lista.
    public int sizePartida(){
        logger.info("Numero de partidas en la lista: " + this.cPartidas.size());
        return this.cPartidas.size();
    }

    @Override
    public void iniciarPartida() {

    }

    @Override
    public LinkedList<Partida> partidaCompletadaUser(Usuario u) throws usuarioNoExisteException {
        return null;
    }
}

