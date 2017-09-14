/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplorestsoap.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author usuario
 */
@Provider
@Consumes("application/json")
public class UsuarioBodyReader implements MessageBodyReader<Usuario> {

    private static final Logger LOG = Logger.getLogger(UsuarioBodyReader.class.getName());

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.log(Level.INFO, "isReadable invocado...");
        return type == Usuario.class;
    }

    @Override
    public Usuario readFrom(Class<Usuario> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        LOG.log(Level.INFO, "readFrom invocado...");
        Usuario usuario = new Usuario(1L,"test");
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {            
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();                    
                    parser.next();
                    switch (key) {
                        case "nombre":
                            usuario.setNombre(parser.getString());
                            break;
                        case "id":
                            usuario.setId(parser.getLong());
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }

        return usuario;
    }

}
