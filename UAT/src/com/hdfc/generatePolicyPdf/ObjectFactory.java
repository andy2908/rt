
package com.hdfc.generatePolicyPdf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hdfc.generatePolicyPdf package. 
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

    private final static QName _Base64Binary_QNAME = new QName("http://tempuri.org/", "base64Binary");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hdfc.generatePolicyPdf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPSSPDFInBytesResponse }
     * 
     */
    public GetPSSPDFInBytesResponse createGetPSSPDFInBytesResponse() {
        return new GetPSSPDFInBytesResponse();
    }

    /**
     * Create an instance of {@link GetPSSPDFInBytes }
     * 
     */
    public GetPSSPDFInBytes createGetPSSPDFInBytes() {
        return new GetPSSPDFInBytes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

}
