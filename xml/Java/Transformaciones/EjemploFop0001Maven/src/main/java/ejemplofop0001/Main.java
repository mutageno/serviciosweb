/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplofop0001;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 *
 * @author jose maria
 */
public class Main {

    private static final String ARCHIVOXML = "/recursos/test.xml";
    private static final String ARCHIVOXSL = "/recursos/test.xsl";
    private static final String ARCHIVOPDF = "test.pdf";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        OutputStream out = null;
        try {
            InputStream xml = Main.class.getResourceAsStream(ARCHIVOXML);
            InputStream xsl = Main.class.getResourceAsStream(ARCHIVOXSL);
            FopFactory fopFactory = FopFactory.newInstance();
            out = new BufferedOutputStream(new FileOutputStream(new File(ARCHIVOPDF)));
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsl));
            Source src = new StreamSource(xml);
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);
            System.out.println("Se ha generado un nuevo archivo pdf llamado " + ARCHIVOPDF);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
