package com.curso.serializacionxml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Hello world!
 *
 */
public class App {

    private static final String ARCHIVO_XML = "provincia.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        System.out.println("Este ejemplo está adaptado de http://josedeveloper.com/2012/03/04/serializacion-de-objetos-java-en-xml/");
        JAXBContext context = JAXBContext.newInstance(Provincia.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Provincia provincia = crearProvincia();

        //Mostramos el documento XML generado por la salida estándar
        marshaller.marshal(provincia, System.out);
        //Serializamos el objeto Java a un documento XML
        try (FileOutputStream fos = new FileOutputStream(ARCHIVO_XML)) {
            marshaller.marshal(provincia, fos);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Deserializamos a partir de un documento XML
        Provincia provinciaAux = (Provincia) unmarshaller.unmarshal(new File(ARCHIVO_XML));
        System.out.println("********* Provincia cargado desde fichero XML***************");
        //Mostramos por linea de comandos el objeto Java obtenido
        //producto de la deserialización
        marshaller.marshal(provinciaAux, System.out);

    }

    private static Provincia crearProvincia() {

        String[] nombreLocalidad = {"Madrid", "Coslada"};
        Integer[] cpLocalidad = {28028, 28820};
        List<Localidad> localidades = new ArrayList<>();
        for (Integer i = 0; i < 2; i++) {
            Localidad l = new Localidad();
            l.setCodigoPostal(cpLocalidad[i]);
            l.setNombre(nombreLocalidad[i]);
            localidades.add(l);
        }

        Provincia provincia = new Provincia();
        provincia.setNombre("Madrid");
        provincia.setLocalidades(localidades);

        return provincia;
    }
}
