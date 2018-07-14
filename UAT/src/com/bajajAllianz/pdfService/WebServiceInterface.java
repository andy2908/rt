
package com.bajajAllianz.pdfService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceInterface", targetNamespace = "http://bajaj.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceInterface {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.Object
     */
    @WebMethod
    @WebResult(partName = "return")
    public Object downloadFile(
        @WebParam(name = "arg0", partName = "arg0")
        ClientInfo arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Object
     */
    @WebMethod
    @WebResult(partName = "return")
    public Object getPolicyDoc(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Object
     */
    @WebMethod
    @WebResult(partName = "return")
    public Object getPolicyPdf(
        @WebParam(name = "arg0", partName = "arg0")
        byte[] arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}