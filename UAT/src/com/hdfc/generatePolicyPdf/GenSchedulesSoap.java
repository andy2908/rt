
package com.hdfc.generatePolicyPdf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GenSchedulesSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GenSchedulesSoap {


    /**
     * 
     * @param policyNo
     * @param agentCode
     * @return
     *     returns byte[]
     */
    @WebMethod(operationName = "GetPSSPDFInBytes", action = "http://tempuri.org/GetPSSPDFInBytes")
    @WebResult(name = "GetPSSPDFInBytesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetPSSPDFInBytes", targetNamespace = "http://tempuri.org/", className = "com.hdfc.generatePolicyPdf.GetPSSPDFInBytes")
    @ResponseWrapper(localName = "GetPSSPDFInBytesResponse", targetNamespace = "http://tempuri.org/", className = "com.hdfc.generatePolicyPdf.GetPSSPDFInBytesResponse")
    public byte[] getPSSPDFInBytes(
        @WebParam(name = "PolicyNo", targetNamespace = "http://tempuri.org/")
        String policyNo,
        @WebParam(name = "AgentCode", targetNamespace = "http://tempuri.org/")
        String agentCode);

}
