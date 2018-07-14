
package com.bajajAllianz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoSigOtherDetailsUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoSigOtherDetailsUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoSigOtherDetailsBase">
 *       &lt;sequence>
 *         &lt;element name="covernoteNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extra1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extra2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imdcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extra3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extra4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extra5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="leadNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="runnerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoSigOtherDetailsUser", propOrder = {
    "covernoteNo",
    "cceCode",
    "extra1",
    "extra2",
    "imdcode",
    "extra3",
    "extra4",
    "extra5",
    "leadNo",
    "runnerCode"
})
public class WeoSigOtherDetailsUser
    extends WeoSigOtherDetailsBase
{

    @XmlElement(required = true, nillable = true)
    protected String covernoteNo;
    @XmlElement(required = true, nillable = true)
    protected String cceCode;
    @XmlElement(required = true, nillable = true)
    protected String extra1;
    @XmlElement(required = true, nillable = true)
    protected String extra2;
    @XmlElement(required = true, nillable = true)
    protected String imdcode;
    @XmlElement(required = true, nillable = true)
    protected String extra3;
    @XmlElement(required = true, nillable = true)
    protected String extra4;
    @XmlElement(required = true, nillable = true)
    protected String extra5;
    @XmlElement(required = true, nillable = true)
    protected String leadNo;
    @XmlElement(required = true, nillable = true)
    protected String runnerCode;

    /**
     * Gets the value of the covernoteNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCovernoteNo() {
        return covernoteNo;
    }

    /**
     * Sets the value of the covernoteNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCovernoteNo(String value) {
        this.covernoteNo = value;
    }

    /**
     * Gets the value of the cceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCceCode() {
        return cceCode;
    }

    /**
     * Sets the value of the cceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCceCode(String value) {
        this.cceCode = value;
    }

    /**
     * Gets the value of the extra1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra1() {
        return extra1;
    }

    /**
     * Sets the value of the extra1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra1(String value) {
        this.extra1 = value;
    }

    /**
     * Gets the value of the extra2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra2() {
        return extra2;
    }

    /**
     * Sets the value of the extra2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra2(String value) {
        this.extra2 = value;
    }

    /**
     * Gets the value of the imdcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImdcode() {
        return imdcode;
    }

    /**
     * Sets the value of the imdcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImdcode(String value) {
        this.imdcode = value;
    }

    /**
     * Gets the value of the extra3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra3() {
        return extra3;
    }

    /**
     * Sets the value of the extra3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra3(String value) {
        this.extra3 = value;
    }

    /**
     * Gets the value of the extra4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra4() {
        return extra4;
    }

    /**
     * Sets the value of the extra4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra4(String value) {
        this.extra4 = value;
    }

    /**
     * Gets the value of the extra5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtra5() {
        return extra5;
    }

    /**
     * Sets the value of the extra5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtra5(String value) {
        this.extra5 = value;
    }

    /**
     * Gets the value of the leadNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadNo() {
        return leadNo;
    }

    /**
     * Sets the value of the leadNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadNo(String value) {
        this.leadNo = value;
    }

    /**
     * Gets the value of the runnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRunnerCode() {
        return runnerCode;
    }

    /**
     * Sets the value of the runnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRunnerCode(String value) {
        this.runnerCode = value;
    }

}
