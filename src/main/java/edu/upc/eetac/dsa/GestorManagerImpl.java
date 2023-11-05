package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class GestorManagerImpl implements GestorManager {

    private static GestorManager instance;
    protected Map<String, Usuario> lUsuarios;
    protected Queue<Partida> cPartidas;
    protected List<Juego> lJuegos;

    final static Logger logger = Logger.getLogger(GestorManagerImpl.class);
    private String idJuego;

    public GestorManagerImpl() {
        this.lUsuarios = new HashMap<>();
        this.lJuegos = new LinkedList<Juego>();
    }

    public static GestorManager getInstance() {
        if (instance == null) instance = new GestorManagerImpl();
        return instance;
    }

    public Juego nuevoJuego(String idJuego, String descripcion, int nivelesTotales) {
        Juego j = getJuego(idJuego);
        if(j == null) { // Si no existe el juego, se crea.
            j = new Juego(idJuego, descripcion, nivelesTotales);
            lJuegos.add(j);
            logger.info("Juego " + idJuego + " añadido.");
            return j;
        }
        // Si el juego existe, se notifica.
        logger.info("El juego ya existe.");
        return null;
    }

    public Juego inicioPartida(String idJuego, String idUsuario) {
        Juego j = getJuego(idJuego);
        Usuario u = getUsuario(idUsuario);

        if(j == null || u == null) { // Si alguno no existe, se notifica.
            logger.info("El usuario o el juego no existe.");
            return null;
        }

        if (u.getJugando() == true) { // Si ya está en una partida, se notifica.
            logger.info("El jugador ya está en una partida.");
            return null;
        }

        // Si es correcto, se inicia la partida.

        Partida partida = new Partida(idUsuario, idUsuario, String.valueOf(java.time.LocalDate.now()));
        logger.info("Añadiendo partida al usuario " + u.getIdUsuario());
        return j;
    }

    // Búsqueda del usuario
    public Usuario getUsuario(String idUsuario) {
        logger.info("Buscando el usuario con id: " + idUsuario);
        if(this.lUsuarios.get(idUsuario) == null) { // Si no existe, se notifica.
            logger.info("Jugador no encontrado");
            return null;
        }
        return this.lUsuarios.get(idUsuario);
    }

    // Obtención del nivel actual. Se devuelve -1 si falla.
    public int getNivelActual(String idUsuario) {
        Partida p = getPartidaUsuario(idUsuario);
        Usuario u = getUsuario(idUsuario);

        if(p != null || u != null) {
            logger.info("El usuario " + u.getIdUsuario() + " está en el nivel " + p.getNivelActual());
            int res = p.getNivelActual();
            return p.getNivelActual();
        }
        return -1;
    }

    public Partida getPartidaUsuario(String idUsuario) {
        Usuario u = getUsuario(idUsuario);
        logger.info("Buscando la última partida del usuario: " + idUsuario);
        if(u.getJugando() == true) {
            Partida p = u.getPartidas().get(u.getPartidas().size()-1);
            logger.info("");
            return p;
        }
        logger.info("No se encuentra la partida");
        return null;
    }

    // Obtención de los puntos actuales del usuario.
    public String getPuntosActuales(String idUsuario) {
        Partida p = getPartidaUsuario(idUsuario);
        if(p != null){
            logger.info("El usuario tiene " + p.getPuntos());
            return p.getPuntos().toString();
        }
        return null;
    }

    // Cambio de nivel
    public Usuario cambioNivel(String idUsuario, int puntos, String fecha) {
        Partida p = getPartidaUsuario(idUsuario);
        if (p != null){ // Compración para saber si está en el último nivel
            logger.info("El usuario está en el nivel " + p.getNivelActual() + " de un total de: " + getJuego(p.getIdJuego()).getNivelesTotales());
            if(p.getNivelActual()==getJuego(p.getIdJuego()).getNivelesTotales()){ // Se informa que está en el último nivel
                logger.info("El usuario está en el último nivel.");
                // Se suman 100 puntos a la puntuación actual
                this.lUsuarios.get(idUsuario).getPartidas().get(p.getIdJuego()).sumamosPuntos(100);
                this.lUsuarios.get(idUsuario).setJugando(false);
                logger.info("Final de la partida.");
                return getUsuario(idUsuario);
            }
            Partida nuevaPartida = p;
            nuevaPartida.setNivelActual(p.getNivelActual()+1);
            nuevaPartida.setPuntos(puntos);
            nuevaPartida.setFecha(String.valueOf((java.time.LocalDate.now())));
            getUsuario(idUsuario).addPartida(nuevaPartida);
            logger.info("El usuario " + idUsuario + " ha avanzado de nivel.");
            return getUsuario(idUsuario);
        }
        return null;
    }

    // Final de la partida
    public Usuario finalPartida(String idUsuario) {
        Partida p = getPartidaUsuario(idUsuario);
        if(p != null) {
            this.lUsuarios.get(idUsuario).setJugando(false);
            logger.info("El usuario " +idUsuario+ " ha termiando la partida actual");
            return this.lUsuarios.get(idUsuario);
        }
        return null;
    }

    // Lista de usuarios de un juego descendientemente por puntos
    public List<Usuario> sortUsuarios(Juego j) {
        Juego j1 = getJuego(j.getIdJuego());
        List<Partida> partidas = new ArrayList<>();
        for(Map.Entry<String,Usuario> entry: this.lUsuarios.entrySet()) {
            Partida p = getPartida(j.getIdJuego(), entry.getKey());
            if(p != null) {
                partidas.add(p);
            }
        }
        if(partidas.size() != 0) {
            partidas.sort((Partida p1, Partida p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));
            List<Usuario> sorted = new ArrayList<>();
            for (Partida partida : partidas) {
                sorted.add(this.lUsuarios.get(partida.getIdUsuario()));
            }
            return sorted;
        }
        return null;
    }

    private Partida getPartida(String idJuego, String idUsuario) {
        List<Partida> l = getPartidas(idJuego, idUsuario);
        if(l.size() != 0) {
            return l.get(l.size()-1);
        }
        return null;
    }

    // Listado de partidas de un usuario
    private List<Partida> getPartidas(String idJuego, String idUsuario) {
        logger.info("intentando obtener las partidas de un usuario del juego.");
        Juego j = getJuego(idJuego);
        if(j == null) {
            logger.info("El juego no existe.");
        }
        Usuario u = this.lUsuarios.get(idUsuario);
        if(u == null) {
            logger.info("El usuario no existe");
        }
        HashMap<String, Partida> partidas = u.getPartidas();
        List<Partida> partidaJugada = new ArrayList<>();
        for(Map.Entry<String,Partida> entry: partidas.entrySet()) {
            if(entry.getValue().getIdJuego().equals(idJuego)) {
                partidaJugada.add(entry.getValue());
                logger.info("Partida añadida a la lista, total " + partidaJugada.size());
            }
        }
        return partidaJugada;
    }

    public List<Partida> partidaUsuario(String idUsuario) {
        List<Partida> partidas = getUsuario(idUsuario).getPartidas().values().stream().collect(toList());
        return partidas;
    }
    public List<Partida> actividadUsuario(String idUsuario, String idJuego) {
        return null;
    }

    public int tamanoJuego() {
        return this.lJuegos.size();
    }

    public int tamanoUsuarios() {
        return this.lUsuarios.size();
    }

    public Usuario addUsuario(String idUsuario) {
        if(getUsuario(idUsuario)==null){
            logger.info("Añadiendo al usuario.");
            return getUsuario(idUsuario);
        }
        return null;
    }

    public Juego getJuego(String idJuego) {
        logger.info("Buscando el juego con id " + idJuego);
        for(Juego j: this.lJuegos){
            if(j.getIdJuego().equals(idJuego)){ //Si el juego existe
                logger.info("Juego encontrado. " + j.getDescripcion());
                return j;
            }
        }
        logger.info("Juego no encontrado");
        return null;
    }
}
