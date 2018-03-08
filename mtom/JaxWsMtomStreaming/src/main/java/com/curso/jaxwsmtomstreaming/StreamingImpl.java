/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwsmtomstreaming;

import com.sun.xml.ws.developer.StreamingAttachment;
import com.sun.xml.ws.developer.StreamingDataHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author usuario
 */
/*
parseEagerly = true significa que el destino 
debe esperar hasta que el archivo adjunto sea recibido completamente,
y guardado en el disco, y luego puede proceder con la lógica de 
negocio del método web.
*/
/*
memoryThreshold = 40000L
Los archivos adjuntos de menor peso se almacenan en la memoria
*/
@StreamingAttachment(parseEagerly = true, memoryThreshold = 40000L)
@MTOM
@WebService(name = "MtomStreaming",
        serviceName = "MtomStreamingService",
        targetNamespace = "http://ws.curso.com")
public class StreamingImpl {

    private static final Logger LOG = Logger.getLogger(StreamingImpl.class.getName());

    public void fileUpload(String fileName,
            @XmlMimeType("application/octet-stream") DataHandler data) {
        try {
            LOG.log(Level.INFO,"Invocado fileUpload");
            try (StreamingDataHandler dh = (StreamingDataHandler) data) {
                String[] split = fileName.split("\\.");
                File file = new File(split[0] + System.currentTimeMillis() + "." + split[1]);
                dh.moveTo(file);
                LOG.log(Level.INFO, "Archivo: {0}", file.getCanonicalPath());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error", e);
            throw new WebServiceException(e);
        }
    }

    @XmlMimeType("application/octet-stream")
    @WebMethod
    public DataHandler fileDownload(String filename) {
        LOG.log(Level.INFO, "Invocado fileDownload");
        LOG.log(Level.INFO, "Archivo para descargar: {0}", filename);
        return new DataHandler(new FileDataSource(filename));
    }
}
