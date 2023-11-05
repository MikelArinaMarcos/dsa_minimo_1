package edu.upc.eetac.dsa;

import java.util.List;

public interface GestorManager {

    public Juego nuevoJuego(String idJuego, String descripcion, int numeroNiveles);
    public Juego inicioPartida(String idJuego, String idUsuario);
    public Usuario getUsuario(String idUsuario);
    public int getNivelActual(String idUsuario);
    public Partida getPartidaUsuario(String idUsuario);
    public String getPuntosActuales(String idUsuario);
    public Usuario cambioNivel(String idUsuario, int puntos, String fecha);
    public Usuario finalPartida(String idUsuario);
    public List<Usuario> sortUsuarios(Juego j);
    public List<Partida> partidaUsuario(String idUsuario);
    public List<Partida> actividadUsuario(String idUsuario, String idJuego);

    int tamanoJuego();
    int tamanoUsuarios();

    public Usuario addUsuario(String idUsuario);

    public Juego getJuego(String idJuego);
}
