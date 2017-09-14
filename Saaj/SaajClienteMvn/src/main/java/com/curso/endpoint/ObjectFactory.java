
package com.curso.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.curso.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetSaludoMsg_QNAME = new QName("http://endpoint.curso.com/", "getSaludoMsg");
    private final static QName _GetSaludoMsgResponse_QNAME = new QName("http://endpoint.curso.com/", "getSaludoMsgResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.curso.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSaludoMsg }
     * 
     */
    public GetSaludoMsg createGetSaludoMsg() {
        return new GetSaludoMsg();
    }

    /**
     * Create an instance of {@link GetSaludoMsgResponse }
     * 
     */
    public GetSaludoMsgResponse createGetSaludoMsgResponse() {
        return new GetSaludoMsgResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSaludoMsg }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.curso.com/", name = "getSaludoMsg")
    public JAXBElement<GetSaludoMsg> createGetSaludoMsg(GetSaludoMsg value) {
        return new JAXBElement<GetSaludoMsg>(_GetSaludoMsg_QNAME, GetSaludoMsg.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSaludoMsgResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.curso.com/", name = "getSaludoMsgResponse")
    public JAXBElement<GetSaludoMsgResponse> createGetSaludoMsgResponse(GetSaludoMsgResponse value) {
        return new JAXBElement<GetSaludoMsgResponse>(_GetSaludoMsgResponse_QNAME, GetSaludoMsgResponse.class, null, value);
    }

}
