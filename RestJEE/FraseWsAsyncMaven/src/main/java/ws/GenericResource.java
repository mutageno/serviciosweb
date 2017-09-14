/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 * REST Web Service
 *
 * @author khepherer
 */
@Path("generic")
@RequestScoped
public class GenericResource {

    @Context
    private UriInfo context;
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of ws.GenericResource
     *
     * @param asyncResponse
     */
    @GET
    @Produces("application/json")
    public void getJson(@Suspended final AsyncResponse asyncResponse) {
        executorService.submit(() -> asyncResponse.resume(doGetJson()));
    }

    private Frase doGetJson() {
        return new Frase("Un texto cualquiera");
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param asyncResponse
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(@Suspended
            final AsyncResponse asyncResponse, final String content) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                doPutJson(content);
                asyncResponse.resume(javax.ws.rs.core.Response.ok().build());
            }
        });
    }

    private void doPutJson(String content) {
        LOG.log(Level.INFO, "Recibido: {0}", content);
    }
}
