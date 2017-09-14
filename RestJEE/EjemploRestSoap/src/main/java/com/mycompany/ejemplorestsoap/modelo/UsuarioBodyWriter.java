/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestsoap.modelo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author khepherer
 */
@Provider
@Produces("application/json")
public class UsuarioBodyWriter implements MessageBodyWriter<Usuario> {

    private static final Logger LOG = Logger.getLogger(UsuarioBodyWriter.class.getName());

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
       LOG.log(Level.INFO,"isWriteable invocado...");
        return type == Usuario.class;
    }

    @Override
    public long getSize(Usuario t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.log(Level.INFO,"getSize invocado");
        return 0;
    }

    @Override
    public void writeTo(Usuario t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        LOG.log(Level.INFO,"writeTo invocado");
        JsonObject objeto = Json.createObjectBuilder().add("id", t.getId()).add("nombre", t.getNombre()).build();        
        DataOutputStream dos = new DataOutputStream(entityStream);
        dos.writeUTF(objeto.toString());
    }

}
