
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
 *         &lt;element name="GetBreakinInspectionStatusResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBreakinInspectionStatusResult"
})
@XmlRootElement(name = "GetBreakinInspectionStatusResponse")
public class GetBreakinInspectionStatusResponse {

    @XmlElement(name = "GetBreakinInspectionStatusResult")
    protected String getBreakinInspectionStatusResult;

    /**
     * Gets the value of the getBreakinInspectionStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBreakinInspectionStatusResult() {
        return getBreakinInspectionStatusResult;
    }

    /**
     * Sets the value of the getBreakinInspectionStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBreakinInspectionStatusResult(String value) {
        this.getBreakinInspectionStatusResult = value;
    }

}
