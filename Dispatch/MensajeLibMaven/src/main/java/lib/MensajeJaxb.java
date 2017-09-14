/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chema
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mensaje")
public class MensajeJaxb {

    @XmlElement
    private String argumento;
    @XmlElement
    private String resultado;

    public MensajeJaxb() {
    }

    public MensajeJaxb(String argumento) {
        this.argumento = argumento;
    }

    public String getArgumento() {
        return argumento;
    }

    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
