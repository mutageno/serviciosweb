/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.microservicios0001.rest;

import com.curso.microservicios0001.repos.ClienteRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
@Path("/")
public class ClienteRest {

    @Inject
    private ClienteRepo clienteRepo;

    public ClienteRest() {
    }

    @PostConstruct
    private void init() {
        Stream.iterate(1, n -> n + 1).limit(5).map(
                i -> new Cliente("Nombre" + i, "Nombre" + i + "@gmail.com")
        ).forEach(clienteRepo::save);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @GET
    @Path("cliente/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getCliente(@PathParam("id") long id) {
        return Optional.ofNullable(clienteRepo.findOne(id)).orElse(
                new Cliente("No existe", "No existe")
        );
    }
}
