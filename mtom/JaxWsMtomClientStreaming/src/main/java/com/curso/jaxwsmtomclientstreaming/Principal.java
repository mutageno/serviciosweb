/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwsmtomclientstreaming;

import com.curso.ws.MtomStreaming;
import com.curso.ws.MtomStreamingService;
import java.util.Map;
import javax.xml.ws.soap.MTOMFeature;
import javax.activation.DataHandler;
import javax.xml.ws.BindingProvider;
import com.sun.xml.ws.developer.JAXWSProperties;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/**
 *
 * @author usuario
 */
public class Principal {

    private static final Logger LOG = Logger.getLogger(Principal.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Configuración del servicio Web
            MtomStreamingService service = new MtomStreamingService();
            MTOMFeature feature = new MTOMFeature();
            MtomStreaming port = service.getMtomStreamingPort(feature);
            Map<String, Object> ctxt = ((BindingProvider) port).getRequestContext();
            ctxt.put(JAXWSProperties.HTTP_CLIENT_STREAMING_CHUNK_SIZE, 8 * 1024);
            //Subir un archivo
            DataHandler dh = new DataHandler(new FileDataSource("e:/Test/tmp/Rose.jpg"));
            port.fileUpload("e:/Test/tmp/Rose.jpg", dh);
            //Descarga del servicio web
            DataHandler data = port.fileDownload("e:/Test/tmp/Rose.jpg");
            String filePath = "e:/Test/tmp1/Rosedownload" + System.currentTimeMillis() + ".jpg";
            FileOutputStream fos = new FileOutputStream(filePath);
            try (InputStream is = data.getInputStream(); BufferedOutputStream outputStream = new BufferedOutputStream(fos)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Ha ocurrido un error", ex);
        }
    }
}
