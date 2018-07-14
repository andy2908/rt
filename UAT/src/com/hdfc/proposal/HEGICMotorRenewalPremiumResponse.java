
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
 *         &lt;element name="HEGICMotorRenewal_PremiumResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hegicMotorRenewalPremiumResult"
})
@XmlRootElement(name = "HEGICMotorRenewal_PremiumResponse")
public class HEGICMotorRenewalPremiumResponse {

    @XmlElement(name = "HEGICMotorRenewal_PremiumResult")
    protected String hegicMotorRenewalPremiumResult;

    /**
     * Gets the value of the hegicMotorRenewalPremiumResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEGICMotorRenewalPremiumResult() {
        return hegicMotorRenewalPremiumResult;
    }

    /**
     * Sets the value of the hegicMotorRenewalPremiumResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEGICMotorRenewalPremiumResult(String value) {
        this.hegicMotorRenewalPremiumResult = value;
    }

}
