
package com.bajajAllianz;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoTyacPayRowWsUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoTyacPayRowWsUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoTyacPayRowWsBase">
 *       &lt;sequence>
 *         &lt;element name="payMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="receiptNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="collectionNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="collectionAmt" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoTyacPayRowWsUser", propOrder = {
    "payMode",
    "receiptNo",
    "payAmt",
    "collectionNo",
    "collectionAmt"
})
public class WeoTyacPayRowWsUser
    extends WeoTyacPayRowWsBase
{

    @XmlElement(required = true, nillable = true)
    protected String payMode;
    @XmlElement(required = true, nillable = true)
    protected String receiptNo;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal payAmt;
    @XmlElement(required = true, nillable = true)
    protected String collectionNo;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal collectionAmt;

    /**
     * Gets the value of the payMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMode() {
        return payMode;
    }

    /**
     * Sets the value of the payMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMode(String value) {
        this.payMode = value;
    }

    /**
     * Gets the value of the receiptNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * Sets the value of the receiptNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptNo(String value) {
        this.receiptNo = value;
    }

    /**
     * Gets the value of the payAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayAmt() {
        return payAmt;
    }

    /**
     * Sets the value of the payAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayAmt(BigDecimal value) {
        this.payAmt = value;
    }

    /**
     * Gets the value of the collectionNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionNo() {
        return collectionNo;
    }

    /**
     * Sets the value of the collectionNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionNo(String value) {
        this.collectionNo = value;
    }

    /**
     * Gets the value of the collectionAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCollectionAmt() {
        return collectionAmt;
    }

    /**
     * Sets the value of the collectionAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCollectionAmt(BigDecimal value) {
        this.collectionAmt = value;
    }

}
