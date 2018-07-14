
package com.shreeRamGIC;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shreeramIns package. 
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

    private final static QName _AuthHeader_QNAME = new QName("http://tempuri.org/", "AuthHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shreeramIns
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetQuotResponse }
     * 
     */
    public GetQuotResponse createGetQuotResponse() {
        return new GetQuotResponse();
    }

    /**
     * Create an instance of {@link MPCProposalResponseETT }
     * 
     */
    public MPCProposalResponseETT createMPCProposalResponseETT() {
        return new MPCProposalResponseETT();
    }

    /**
     * Create an instance of {@link PolicyScheduleURL }
     * 
     */
    public PolicyScheduleURL createPolicyScheduleURL() {
        return new PolicyScheduleURL();
    }

    /**
     * Create an instance of {@link GetQuot }
     * 
     */
    public GetQuot createGetQuot() {
        return new GetQuot();
    }

    /**
     * Create an instance of {@link AuthHeader }
     * 
     */
    public AuthHeader createAuthHeader() {
        return new AuthHeader();
    }

    /**
     * Create an instance of {@link GetQuotCommercialResponse }
     * 
     */
    public GetQuotCommercialResponse createGetQuotCommercialResponse() {
        return new GetQuotCommercialResponse();
    }

    /**
     * Create an instance of {@link PCCVProposalResponseETT }
     * 
     */
    public PCCVProposalResponseETT createPCCVProposalResponseETT() {
        return new PCCVProposalResponseETT();
    }

    /**
     * Create an instance of {@link GeneratePCCVProposalResponse }
     * 
     */
    public GeneratePCCVProposalResponse createGeneratePCCVProposalResponse() {
        return new GeneratePCCVProposalResponse();
    }

    /**
     * Create an instance of {@link PolicyScheduleURLResponse }
     * 
     */
    public PolicyScheduleURLResponse createPolicyScheduleURLResponse() {
        return new PolicyScheduleURLResponse();
    }

    /**
     * Create an instance of {@link PolicyApproveResponse }
     * 
     */
    public PolicyApproveResponse createPolicyApproveResponse() {
        return new PolicyApproveResponse();
    }

    /**
     * Create an instance of {@link PolicyApprovalResponseETT }
     * 
     */
    public PolicyApprovalResponseETT createPolicyApprovalResponseETT() {
        return new PolicyApprovalResponseETT();
    }

    /**
     * Create an instance of {@link GetQuotCommercial }
     * 
     */
    public GetQuotCommercial createGetQuotCommercial() {
        return new GetQuotCommercial();
    }

    /**
     * Create an instance of {@link GenerateProposalResponse }
     * 
     */
    public GenerateProposalResponse createGenerateProposalResponse() {
        return new GenerateProposalResponse();
    }

    /**
     * Create an instance of {@link GeneratePCCVProposal }
     * 
     */
    public GeneratePCCVProposal createGeneratePCCVProposal() {
        return new GeneratePCCVProposal();
    }

    /**
     * Create an instance of {@link PCCVProposalEntryETT }
     * 
     */
    public PCCVProposalEntryETT createPCCVProposalEntryETT() {
        return new PCCVProposalEntryETT();
    }

    /**
     * Create an instance of {@link PolicyApprove }
     * 
     */
    public PolicyApprove createPolicyApprove() {
        return new PolicyApprove();
    }

    /**
     * Create an instance of {@link PolicyApprovalETT }
     * 
     */
    public PolicyApprovalETT createPolicyApprovalETT() {
        return new PolicyApprovalETT();
    }

    /**
     * Create an instance of {@link GenerateProposal }
     * 
     */
    public GenerateProposal createGenerateProposal() {
        return new GenerateProposal();
    }

    /**
     * Create an instance of {@link MPCProposalEntryETT }
     * 
     */
    public MPCProposalEntryETT createMPCProposalEntryETT() {
        return new MPCProposalEntryETT();
    }

    /**
     * Create an instance of {@link CoverDtl }
     * 
     */
    public CoverDtl createCoverDtl() {
        return new CoverDtl();
    }

    /**
     * Create an instance of {@link ArrayOfCoverDtl }
     * 
     */
    public ArrayOfCoverDtl createArrayOfCoverDtl() {
        return new ArrayOfCoverDtl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "AuthHeader")
    public JAXBElement<AuthHeader> createAuthHeader(AuthHeader value) {
        return new JAXBElement<AuthHeader>(_AuthHeader_QNAME, AuthHeader.class, null, value);
    }

}
