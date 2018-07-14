
package com.bajajAllianz;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoMotPremiumSummaryUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoMotPremiumSummaryUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoMotPremiumSummaryBase">
 *       &lt;sequence>
 *         &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="paramDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paramRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="net" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="act" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="paramType" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoMotPremiumSummaryUser", propOrder = {
    "od",
    "paramDesc",
    "paramRef",
    "net",
    "act",
    "paramType"
})
public class WeoMotPremiumSummaryUser
    extends WeoMotPremiumSummaryBase
{

    @XmlElement(required = true, nillable = true)
    protected BigDecimal od;
    @XmlElement(required = true, nillable = true)
    protected String paramDesc;
    @XmlElement(required = true, nillable = true)
    protected String paramRef;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal net;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal act;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal paramType;

    /**
     * Gets the value of the od property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOd() {
        return od;
    }

    /**
     * Sets the value of the od property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOd(BigDecimal value) {
        this.od = value;
    }

    /**
     * Gets the value of the paramDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamDesc() {
        return paramDesc;
    }

    /**
     * Sets the value of the paramDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamDesc(String value) {
        this.paramDesc = value;
    }

    /**
     * Gets the value of the paramRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamRef() {
        return paramRef;
    }

    /**
     * Sets the value of the paramRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamRef(String value) {
        this.paramRef = value;
    }

    /**
     * Gets the value of the net property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNet() {
        return net;
    }

    /**
     * Sets the value of the net property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNet(BigDecimal value) {
        this.net = value;
    }

    /**
     * Gets the value of the act property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAct() {
        return act;
    }

    /**
     * Sets the value of the act property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAct(BigDecimal value) {
        this.act = value;
    }

    /**
     * Gets the value of the paramType property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getParamType() {
        return paramType;
    }

    /**
     * Sets the value of the paramType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setParamType(BigDecimal value) {
        this.paramType = value;
    }

}
