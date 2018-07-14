
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
 *         &lt;element name="HEGICMotorRenewal_DynamicResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "hegicMotorRenewalDynamicResult"
})
@XmlRootElement(name = "HEGICMotorRenewal_DynamicResponse")
public class HEGICMotorRenewalDynamicResponse {

    @XmlElement(name = "HEGICMotorRenewal_DynamicResult")
    protected String hegicMotorRenewalDynamicResult;

    /**
     * Gets the value of the hegicMotorRenewalDynamicResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHEGICMotorRenewalDynamicResult() {
        return hegicMotorRenewalDynamicResult;
    }

    /**
     * Sets the value of the hegicMotorRenewalDynamicResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHEGICMotorRenewalDynamicResult(String value) {
        this.hegicMotorRenewalDynamicResult = value;
    }

}
