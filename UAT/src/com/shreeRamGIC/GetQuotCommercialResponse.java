
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
 *         &lt;element name="GetQuotCommercialResult" type="{http://tempuri.org/}PCCVProposalResponseETT" minOccurs="0"/>
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
    "getQuotCommercialResult"
})
@XmlRootElement(name = "GetQuotCommercialResponse")
public class GetQuotCommercialResponse {

    @XmlElement(name = "GetQuotCommercialResult")
    protected PCCVProposalResponseETT getQuotCommercialResult;

    /**
     * Gets the value of the getQuotCommercialResult property.
     * 
     * @return
     *     possible object is
     *     {@link PCCVProposalResponseETT }
     *     
     */
    public PCCVProposalResponseETT getGetQuotCommercialResult() {
        return getQuotCommercialResult;
    }

    /**
     * Sets the value of the getQuotCommercialResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PCCVProposalResponseETT }
     *     
     */
    public void setGetQuotCommercialResult(PCCVProposalResponseETT value) {
        this.getQuotCommercialResult = value;
    }

}