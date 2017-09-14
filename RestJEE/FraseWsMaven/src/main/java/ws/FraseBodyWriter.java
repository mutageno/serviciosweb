/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FraseBodyWriter implements MessageBodyWriter<Frase> {

    private static final Logger LOG = Logger.getLogger(FraseBodyWriter.class.getName());

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
       LOG.log(Level.INFO,"isWriteable invocado...");
        return type == Frase.class;
    }

    @Override
    public long getSize(Frase t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        LOG.log(Level.INFO,"getSize invocado");
        return 0;
    }

    @Override
    public void writeTo(Frase t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        LOG.log(Level.INFO,"writeTo invocado");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"objeto\":\"").append(t.toString()).append("\"}");
        DataOutputStream dos = new DataOutputStream(entityStream);
        dos.writeUTF(sb.toString());
    }

}
