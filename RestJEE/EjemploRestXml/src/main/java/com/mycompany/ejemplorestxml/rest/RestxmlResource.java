/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestxml.rest;

import com.mycompany.ejemplorestxml.modelo.Persona;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("restxml")
public class RestxmlResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestxmlResource
     */
    public RestxmlResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.ejemplorestxml.modelo.RestxmlResource
     *
     * @return an instance of com.mycompany.ejemplorestxml.modelo.Persona
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Persona getXml() {
        //TODO return proper representation object
        return new Persona(1000L, "Cualquiera");
    }

    /**
     * PUT method for updating or creating an instance of RestxmlResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(Persona content) {
        System.out.println("Recibido: " + content.toString());
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Persona getById(@PathParam("id") String id) {
        return new Persona(Long.parseLong(id), "Cualquiera con id " + id);
    }
}
