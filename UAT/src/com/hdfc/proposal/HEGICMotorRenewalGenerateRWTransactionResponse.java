
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
 *         &lt;element name="HEGICMotorRenewal_GenerateRWTransactionResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hegicMotorRenewalGenerateRWTransactionResult"
})
@XmlRootElement(name = "HEGICMotorRenewal_GenerateRWTransactionResponse")
public class HEGICMotorRenewalGenerateRWTransactionResponse {

    @XmlElement(name = "HEGICMotorRenewal_GenerateRWTransactionResult")
    protected String hegicMotorRenewalGenerateRWTransactionResult;

    /**
     * Gets the value of the hegicMotorRenewalGenerateRWTransactionResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEGICMotorRenewalGenerateRWTransactionResult() {
        return hegicMotorRenewalGenerateRWTransactionResult;
    }

    /**
     * Sets the value of the hegicMotorRenewalGenerateRWTransactionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEGICMotorRenewalGenerateRWTransactionResult(String value) {
        this.hegicMotorRenewalGenerateRWTransactionResult = value;
    }

}
