
package com.hdfc.proposal;

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
 *         &lt;element name="HEGICMotorRenewal_ExtractResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hegicMotorRenewalExtractResult"
})
@XmlRootElement(name = "HEGICMotorRenewal_ExtractResponse")
public class HEGICMotorRenewalExtractResponse {

    @XmlElement(name = "HEGICMotorRenewal_ExtractResult")
    protected String hegicMotorRenewalExtractResult;

    /**
     * Gets the value of the hegicMotorRenewalExtractResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEGICMotorRenewalExtractResult() {
        return hegicMotorRenewalExtractResult;
    }

    /**
     * Sets the value of the hegicMotorRenewalExtractResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEGICMotorRenewalExtractResult(String value) {
        this.hegicMotorRenewalExtractResult = value;
    }

}
