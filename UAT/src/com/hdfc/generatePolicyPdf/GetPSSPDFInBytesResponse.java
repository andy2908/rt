
package com.hdfc.generatePolicyPdf;

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
 *         &lt;element name="GetPSSPDFInBytesResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
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
    "getPSSPDFInBytesResult"
})
@XmlRootElement(name = "GetPSSPDFInBytesResponse")
public class GetPSSPDFInBytesResponse {

    @XmlElement(name = "GetPSSPDFInBytesResult")
    protected byte[] getPSSPDFInBytesResult;

    /**
     * Gets the value of the getPSSPDFInBytesResult property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetPSSPDFInBytesResult() {
        return getPSSPDFInBytesResult;
    }

    /**
     * Sets the value of the getPSSPDFInBytesResult property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetPSSPDFInBytesResult(byte[] value) {
        this.getPSSPDFInBytesResult = value;
    }

}
