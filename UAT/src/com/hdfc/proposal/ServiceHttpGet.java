
package com.hdfc.proposal;

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
@WebService(name = "ServiceHttpGet", targetNamespace = "http://tempuri.org/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceHttpGet {


    /**
     * 
     * @param str
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String xmlstring(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "str")
        String str);

    /**
     * 
     * @param agentCode
     * @param prevPolicyNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewal(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PrevPolicyNo")
        String prevPolicyNo);

    /**
     * 
     * @param pgTransNo
     * @param agentCode
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetBreakinInspectionStatus")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String getBreakinInspectionStatus(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PGTransNo")
        String pgTransNo);

    /**
     * 
     * @param pgTransNo
     * @param agentCode
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetBreakinProposalDataFinalPremium")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String getBreakinProposalDataFinalPremium(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PGTransNo")
        String pgTransNo);

    /**
     * 
     * @param agentCode
     * @param prevPolicyNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_Dynamic")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalDynamic(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PrevPolicyNo")
        String prevPolicyNo);

    /**
     * 
     * @param vehicleRegistratonNo
     * @param agentCode
     * @param prevPolicyNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_Extract")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalExtract(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PrevPolicyNo")
        String prevPolicyNo,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "VehicleRegistratonNo")
        String vehicleRegistratonNo);

    /**
     * 
     * @param renewalPremiumInputXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_Premium")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalPremium(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalPremiumInputXml")
        String renewalPremiumInputXml);

    /**
     * 
     * @param rwxmlDetails
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_GenerateRWTransaction")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalGenerateRWTransaction(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RWXMLDetails")
        String rwxmlDetails);

    /**
     * 
     * @param renewalInputAddressDetails
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_UpdateAddressDetails")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalUpdateAddressDetails(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalInputAddressDetails")
        String renewalInputAddressDetails);

    /**
     * 
     * @param agentCode
     * @param vehicleRegistrationNo
     * @param prevPolicyNo
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_GetRenewalPlans")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalGetRenewalPlans(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "AgentCode")
        String agentCode,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "PrevPolicyNo")
        String prevPolicyNo,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "VehicleRegistrationNo")
        String vehicleRegistrationNo);

    /**
     * 
     * @param renewalInputXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICTWRenewal_Extract")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegictwRenewalExtract(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalInputXml")
        String renewalInputXml);

    /**
     * 
     * @param renewalInputXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICTWRenewal_Premium")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegictwRenewalPremium(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalInputXml")
        String renewalInputXml);

    /**
     * 
     * @param renewalInputXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICTWRenewal_GenerateRWTransaction")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegictwRenewalGenerateRWTransaction(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalInputXml")
        String renewalInputXml);

    /**
     * 
     * @param renewalInputXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HEGICMotorRenewal_GenerateRWTransactionDirect")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hegicMotorRenewalGenerateRWTransactionDirect(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "RenewalInputXml")
        String renewalInputXml);

}
