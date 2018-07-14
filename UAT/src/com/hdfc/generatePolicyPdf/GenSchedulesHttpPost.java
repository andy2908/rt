
package com.hdfc.generatePolicyPdf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GenSchedulesHttpPost", targetNamespace = "http://tempuri.org/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GenSchedulesHttpPost {


    /**
     * 
     * @param policyNo
     * @param agentCode
     * @return
     *     returns byte[]
     */
    @WebMethod(operationName = "GetPSSPDFInBytes")
    @WebResult(name = "base64Binary", targetNamespace = "http://tempuri.org/", partName = "Body")
    public byte[] getPSSPDFInBytes(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PolicyNo")
        String policyNo,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode);

}