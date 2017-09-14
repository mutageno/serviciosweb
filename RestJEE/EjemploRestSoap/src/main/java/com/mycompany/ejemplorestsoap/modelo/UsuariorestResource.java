/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestsoap.modelo;

import com.mycompany.ejemplorestsoap.soap.Persona;
import com.mycompany.ejemplorestsoap.utilidades.GestorSoap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author usuario
 */
@Path("usuariorest")
public class UsuariorestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariorestResource
     */
    public UsuariorestResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.ejemplorestsoap.modelo.UsuariorestResource
     *
     * @return an instance of com.mycompany.ejemplorestsoap.modelo.Usuario
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getJson() {
        //TODO return proper representation object
        Persona p = new GestorSoap().llamadaSoap();
        return new Usuario(5000L, "xyz " + p.getNombre());
    }

    /**
     * PUT method for updating or creating an instance of UsuariorestResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Usuario content) {
        System.out.println("Recibido desde el cliente: " + content);
    }
}
