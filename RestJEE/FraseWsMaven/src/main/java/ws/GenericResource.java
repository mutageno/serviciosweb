/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author khepherer
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of ws.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Frase getJson() {
        //TODO return proper representation object
        return new Frase("abc");
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes("application/json")
//    public void putJson(String content) {
//        LOG.log(Level.INFO, "Recibido: {0}", content);
//    }
    @PUT
    @Consumes("application/json")
    public void putJson(Frase content) {
        LOG.log(Level.INFO, "Recibido: {0}", content);
    }
}
