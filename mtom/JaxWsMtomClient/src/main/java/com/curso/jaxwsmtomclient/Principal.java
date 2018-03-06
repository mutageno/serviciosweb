/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwsmtomclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.ws.soap.MTOMFeature;

/**
 *
 * @author usuario
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        llamadaAHello();

        llamadaAUpload("UploadCliente.pdf");

        llamadaADownload("DownloadServidor.pdf");

    }

    private static void llamadaADownload(String fileName) {
        try { // Call Web Service Operation
            com.curso.jaxwsmtom.MtomWs_Service service = new com.curso.jaxwsmtom.MtomWs_Service();
            com.curso.jaxwsmtom.MtomWs port = service.getMtomWsPort(new MTOMFeature(true, 10240));
            // TODO initialize WS operation arguments here
            // TODO process result here
            byte[] result = port.download(fileName);
            String filePath = DOWNLOAD_DIR + fileName;
            FileOutputStream fos = new FileOutputStream(filePath);
            try (BufferedOutputStream outputStream = new BufferedOutputStream(fos)) {
                outputStream.write(result);
            }
            System.out.println("Recibido = " + filePath);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private static final String DOWNLOAD_DIR = "c:/Test/Client/Download/";

    private static void llamadaAUpload(String fileName) {
        try { // Call Web Service Operation
            com.curso.jaxwsmtom.MtomWs_Service service = new com.curso.jaxwsmtom.MtomWs_Service();
            com.curso.jaxwsmtom.MtomWs port = service.getMtomWsPort(new MTOMFeature(true, 10240));
            // TODO initialize WS operation arguments here
            String filePath = UPLOAD_DIR + fileName;
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] imageBytes;
            try (BufferedInputStream inputStream = new BufferedInputStream(fis)) {
                imageBytes = new byte[(int) file.length()];
                inputStream.read(imageBytes);
                port.upload(file.getName(), imageBytes);
            }
            System.out.println("Archivo enviado: " + filePath);
            port.upload(fileName, imageBytes);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private static final String UPLOAD_DIR = "c:/Test/Client/Upload/";

    private static void llamadaAHello() {
        // TODO code application logic here

        try { // Call Web Service Operation
            com.curso.jaxwsmtom.MtomWs_Service service = new com.curso.jaxwsmtom.MtomWs_Service();
            com.curso.jaxwsmtom.MtomWs port = service.getMtomWsPort(new MTOMFeature(true, 10240));
            // TODO initialize WS operation arguments here
            java.lang.String name = "abc";
            // TODO process result here
            java.lang.String result = port.hello(name);
            System.out.println("Resultado = " + result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
