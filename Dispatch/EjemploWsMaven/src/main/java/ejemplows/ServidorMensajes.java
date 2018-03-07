/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplows;

import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceProvider;
import lib.Constantes;
import lib.MensajeJaxb;

/**
 *
 * @author Chema
 */
@ServiceMode(Mode.PAYLOAD)
@WebServiceProvider(
        portName = "Saludo",
        serviceName = "Ejemplos",
        targetNamespace = "urn:ejemplos")
public class ServidorMensajes implements Provider<Source> {

    private final JAXBContext jaxbContext;

    ServidorMensajes() throws JAXBException {
        jaxbContext = JAXBContext.newInstance(MensajeJaxb.class);
    }

    public static void main(String[] args) throws Exception {
        String url = Constantes.url;
        Endpoint.publish(url, new ServidorMensajes());
        System.out.println("Servicio web arrancado con éxito en " + Constantes.url);
    }

    @Override
    public Source invoke(Source payload) {
        try {
            Unmarshaller u = jaxbContext.createUnmarshaller();
            MensajeJaxb mensaje = (MensajeJaxb) u.unmarshal(payload);
            Thread.sleep(3000);
            mensaje.setResultado("Saludos, " + mensaje.getArgumento()
                    + " desde el servidor: " + new Date());
            return new JAXBSource(jaxbContext, mensaje);
        } catch (JAXBException | InterruptedException ex) {
            throw new WebServiceException(ex);
        }
    }
}
