/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojaxbesquema;

import generado.TipoBiblioteca;
import generado.TipoLibro;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author jose maria
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TipoBiblioteca biblioteca = new TipoBiblioteca();
        TipoLibro libro = new TipoLibro();
        libro.setAutor("uno");
        libro.setIsbn("123");
        libro.setPrecio(30);
        libro.setTitulo("cualquiera");
        biblioteca.getLibro().add(libro);
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(biblioteca.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(biblioteca, System.out);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(biblioteca.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            JAXBElement<TipoBiblioteca> b = (JAXBElement<TipoBiblioteca>) unmarshaller.unmarshal(Main.class.getResourceAsStream("/recursos/biblioteca.xml")); //NOI18N
            for(TipoLibro l : b.getValue().getLibro()){
                System.out.println(l.getAutor());
                System.out.println(l.getIsbn());
                System.out.println(l.getPrecio());
                System.out.println(l.getTitulo());
            }
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

    }
}
