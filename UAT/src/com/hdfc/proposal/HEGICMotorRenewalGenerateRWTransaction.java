
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
 *         &lt;element name="RWXMLDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "rwxmlDetails"
})
@XmlRootElement(name = "HEGICMotorRenewal_GenerateRWTransaction")
public class HEGICMotorRenewalGenerateRWTransaction {

    @XmlElement(name = "RWXMLDetails")
    protected String rwxmlDetails;

    /**
     * Gets the value of the rwxmlDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRWXMLDetails() {
        return rwxmlDetails;
    }

    /**
     * Sets the value of the rwxmlDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRWXMLDetails(String value) {
        this.rwxmlDetails = value;
    }

}
