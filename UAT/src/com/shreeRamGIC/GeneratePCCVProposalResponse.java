
package com.shreeRamGIC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeneratePCCVProposalResult" type="{http://tempuri.org/}PCCVProposalResponseETT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "generatePCCVProposalResult"
})
@XmlRootElement(name = "GeneratePCCVProposalResponse")
public class GeneratePCCVProposalResponse {

    @XmlElement(name = "GeneratePCCVProposalResult")
    protected PCCVProposalResponseETT generatePCCVProposalResult;

    /**
     * Gets the value of the generatePCCVProposalResult property.
     * 
     * @return
     *     possible object is
     *     {@link PCCVProposalResponseETT }
     *     
     */
    public PCCVProposalResponseETT getGeneratePCCVProposalResult() {
        return generatePCCVProposalResult;
    }

    /**
     * Sets the value of the generatePCCVProposalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PCCVProposalResponseETT }
     *     
     */
    public void setGeneratePCCVProposalResult(PCCVProposalResponseETT value) {
        this.generatePCCVProposalResult = value;
    }

}
