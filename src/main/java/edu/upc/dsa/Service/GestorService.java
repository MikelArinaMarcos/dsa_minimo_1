package edu.upc.dsa.Service;

import edu.upc.dsa.Exceptions.usuarioNoExisteException;
import edu.upc.dsa.Juego;
import edu.upc.dsa.GestorManager;
import edu.upc.dsa.GestorManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/example", description = "Endpoint to example service")
@Path("/example")
public class GestorService {

   private GestorManager em;

   public GestorService() throws usuarioNoExisteException {
       this.em = GestorManagerImpl.getInstance();
       if(em.sizeUsers() == 0){
           this.em.addUser("mikel@correo.com", "mikel", "arina");
           this.em.addUser("arina@correo.com", "arina", "mikel");
           this.em.addUser("mikelarina@correo.com", "mikelarina", "arinamikel");
       }
       if(em.sizeJuegos() == 0){
           this.em.addJuego("j0001", 10);
           this.em.addJuego("j0002", 20);
           this.em.addJuego("j0003", 30);
       }
   }

   @GET
   @ApiOperation(value = "get usuarios ordenados", notes = "Listado de usuarios de forma descendente")
   @ApiResponses(value = {
           @ApiResponse(code = 201, message = "Successful", response = Juego.class, responseContainer = "List")
   })
   @Path("/")
   @Produces(MediaType.APPLICATION_JSON)
   public Response  getAllProducts(){
       List<Juego> juegos = this.em.getAllJuegos();
       GenericEntity<List<Juego>> entity = new GenericEntity<List<Juego>>(juegos) {};
       return Response.status(201).entity(entity).build()  ;
   }

}
