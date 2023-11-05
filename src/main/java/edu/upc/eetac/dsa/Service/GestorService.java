package edu.upc.eetac.dsa.Service;
import edu.upc.eetac.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.GestorManager;
import edu.upc.eetac.dsa.GestorManagerImpl;
import edu.upc.eetac.dsa.Juego;
import edu.upc.eetac.dsa.Usuario;
import edu.upc.eetac.dsa.Partida;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Api(value = "/game", description = "Endpoint to Game Service")
@Path("game")
class GameService {
    private GestorManager gm;
    public GameService() {
        this.gm = GestorManagerImpl.getInstance();
        if (gm.tamanoJuego()==0){
            this.gm.nuevoJuego("j001","juego 1",10);
            this.gm.nuevoJuego("j002","juego 2",20);
            this.gm.nuevoJuego("j003", "juego 3",30);

        }
        if (gm.tamanoUsuarios()==0){
            this.gm.addUsuario("Mikel");
            this.gm.addUsuario("Clara");
        }

        this.gm.nuevoJuego("j001","juego 1", 10);
    }
    @POST
    @ApiOperation(value = "Creación del juego", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Juego.class),
            @ApiResponse(code = 451, message = "You're not legally allowed to see this")

    })
    @Path("/game/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGame(Juego juego) {
        Juego j = this.gm.nuevoJuego(juego.getIdJuego(),juego.getDescripcion(),juego.getNivelesTotales());
        if (j==null){
            return Response.status(451).build();
        }
        return Response.status(201).entity(j).build();
    }

    @GET
    @ApiOperation(value = "get actual level", notes = "Get the actual lvl of a player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All good", response = Integer.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Player or game does not exists"),
    })
    @Path("/player/{id}/actualLevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActualLevel(@PathParam("id") String id) {
        int lvl = this.gm.getNivelActual(id);
        if (lvl>=0){
            return Response.status(200).entity(lvl).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @ApiOperation(value = "Inicio de juego", notes = "Iniciamos una partida?")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vamos!", response = Juego.class),
            @ApiResponse(code = 404, message = "El juego no existe"),
    })
    @Path("/player/startGame")
    public Response startGame(String idJuego, String descripcion, int numeroNiveles) {
        Juego j = this.gm.nuevoJuego(idJuego,descripcion,numeroNiveles);
        if(j!=null){
            return Response.status(201).entity(j).build();
        }
        return Response.status(404).build();
    }

    @GET
    @ApiOperation(value = "get actual points", notes = "Get the actual points of a player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All good", response = String.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Player or game does not exists"),
    })
    @Path("/player/{id}/puntosActuales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosActuales(@PathParam("id") String idUsuario) {
        String points = this.gm.getPuntosActuales(idUsuario);
        if(points!=null){
            return Response.status(200).entity(points).build();
        }
        return Response.status(404).build();
    }

    @PUT
    @ApiOperation(value = "Cambio de nivel", notes = "Usuario en nivel 1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Siguiente nivel"),
            @ApiResponse(code = 202, message = "Juego terminado"),
            @ApiResponse(code = 404, message = "El juegador o el juego no existe")
    })
    @Path("/player/cambioNivel")
    public Response cambioNivel(String idUser, int points, String date) {
        Usuario u = this.gm.cambioNivel(idUser,points,String.valueOf(java.time.LocalDate.now()));
        if(u==null){
            return Response.status(404).build();
        }
        if (u.getJugando()==true){
            return Response.status(200).build();
        }
        return Response.status(202).build();
    }

    @PUT
    @ApiOperation(value = "Final de la partida", notes = "Final de la partida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Final de la partida"),
            @ApiResponse(code = 404, message = "Juegador o partida no existe."),
    })
    @Path("/player/{id}/finalPartida")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endMatch(@PathParam("id") String id) {
        Usuario u = this.gm.finalPartida(id);
        if (u!=null){
            return Response.status(200).build();
        }
        return Response.status(404).build();
    }

    @POST
    @ApiOperation(value = "Sort players", notes = "Lista de juegadores de un juego ordenados por los puntos descendientemente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Correcto", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Error"),
    })
    @Path("/games/{id}/clasificacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortPlayers(@PathParam("id") String idJuego) {
        Juego j = this.gm.getJuego(idJuego);
        List<Usuario> usuarios = this.gm.sortUsuarios(j);
        if (usuarios==null){
            return Response.status(404).build();
        }
        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(usuarios){};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get partida del jugador", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Partida.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Error")
    })
    @Path("/player/{id}/partidas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchesUser(@PathParam("id") String id) {
        List<Partida> partidas = this.gm.partidaUsuario(id);
        if(partidas.size()!=0){
            GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(partidas){};

            return Response.status(200).entity(entity).build();
        }
        return Response.status(404).build();
    }
}