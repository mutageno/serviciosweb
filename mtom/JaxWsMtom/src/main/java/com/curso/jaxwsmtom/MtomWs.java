/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.jaxwsmtom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author usuario
 */
@WebService(serviceName = "MtomWs")
@MTOM(enabled = true, threshold = 10240)
public class MtomWs {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     *
     * @param fileName
     * @param imageBytes
     */
    @WebMethod(operationName = "upload")
    public void upload(@WebParam(name = "fileName") final String fileName, @WebParam(name = "imageBytes") final byte[] imageBytes) {
        String filePath = "c:/Test/Server/Upload/" + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            try (BufferedOutputStream outputStream = new BufferedOutputStream(fos)) {
                outputStream.write(imageBytes);
            }
            System.out.println("Recibido archivo: " + filePath);
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

    @WebMethod
    public byte[] download(String fileName) {
        String filePath = "c:/Test/Server/Download/" + fileName;
        System.out.println("Enviando archivo: " + filePath);

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] fileBytes;
            try (BufferedInputStream inputStream = new BufferedInputStream(fis)) {
                fileBytes = new byte[(int) file.length()];
                inputStream.read(fileBytes);
            }
            return fileBytes;
        } catch (IOException ex) {
            System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }
}
