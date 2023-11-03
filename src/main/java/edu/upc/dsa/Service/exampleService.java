package edu.upc.dsa.Service;

import edu.upc.dsa.Exceptions.usuarioNoExisteException;
import edu.upc.dsa.Producto;
import edu.upc.dsa.Usuario;
import edu.upc.dsa.exampleManager;
import edu.upc.dsa.exampleManagerImpl;
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

@Api(value = "/example", description = "Endpoint to example service")
@Path("/example")
public class exampleService {

   private exampleManager em;

   public exampleService() throws usuarioNoExisteException {
       this.em = exampleManagerImpl.getInstance();
       if(em.sizeUsers() == 0){
           this.em.addUser("SuperPG@correo.com", "SuperPG", "TuMadre");
           this.em.addUser("Jlarrinzal@correo.com", "Zipi", "1234");
           this.em.addUser("christianL@correo.com", "Zape", "1234");
       }
       if(em.sizeProductos() == 0){
           this.em.addProducto("Bocadillo", 3);
           this.em.addProducto("Cocacola", 1);
           this.em.addProducto("Croisant", 2);
       }

   }

   @GET
   @ApiOperation(value = "get productos ordenados", notes = "Listado productos ordenados de forma ascendente")
   @ApiResponses(value = {
           @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer = "List")
   })
   @Path("/")
   @Produces(MediaType.APPLICATION_JSON)
   public Response  getAllProducts(){
       List<Producto> productos = this.em.getAllProducts();
       GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
       return Response.status(201).entity(entity).build()  ;
   }

}
