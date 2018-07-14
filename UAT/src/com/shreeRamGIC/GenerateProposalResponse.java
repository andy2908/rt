
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
 *         &lt;element name="GenerateProposalResult" type="{http://tempuri.org/}MPCProposalResponseETT" minOccurs="0"/>
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
    "generateProposalResult"
})
@XmlRootElement(name = "GenerateProposalResponse")
public class GenerateProposalResponse {

    @XmlElement(name = "GenerateProposalResult")
    protected MPCProposalResponseETT generateProposalResult;

    /**
     * Gets the value of the generateProposalResult property.
     * 
     * @return
     *     possible object is
     *     {@link MPCProposalResponseETT }
     *     
     */
    public MPCProposalResponseETT getGenerateProposalResult() {
        return generateProposalResult;
    }

    /**
     * Sets the value of the generateProposalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MPCProposalResponseETT }
     *     
     */
    public void setGenerateProposalResult(MPCProposalResponseETT value) {
        this.generateProposalResult = value;
    }

}
