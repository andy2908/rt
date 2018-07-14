
package com.bajajAllianz;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoSigMotExtraCoversUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoSigMotExtraCoversUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoSigMotExtraCoversBase">
 *       &lt;sequence>
 *         &lt;element name="sideCarValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="covernoteNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noOfTrailers" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="subImdcode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="covernoteDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cngValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fibreGlassValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="voluntaryExcess" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalTrailerValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="extraField1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sumInsuredTotalNamedPa" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="geogExtn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noOfPersonsLlo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="noOfEmployeesLle" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="extraField2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extraField3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sumInsuredPa" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="noOfPersonsPa" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoSigMotExtraCoversUser", propOrder = {
    "sideCarValue",
    "covernoteNo",
    "noOfTrailers",
    "subImdcode",
    "covernoteDate",
    "cngValue",
    "fibreGlassValue",
    "voluntaryExcess",
    "totalTrailerValue",
    "extraField1",
    "sumInsuredTotalNamedPa",
    "geogExtn",
    "noOfPersonsLlo",
    "noOfEmployeesLle",
    "extraField2",
    "extraField3",
    "sumInsuredPa",
    "noOfPersonsPa"
})
public class WeoSigMotExtraCoversUser
    extends WeoSigMotExtraCoversBase
{

    @XmlElement(required = true, nillable = true)
    protected BigDecimal sideCarValue;
    @XmlElement(required = true, nillable = true)
    protected String covernoteNo;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal noOfTrailers;
    @XmlElement(required = true, nillable = true)
    protected String subImdcode;
    @XmlElement(required = true, nillable = true)
    protected String covernoteDate;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal cngValue;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal fibreGlassValue;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal voluntaryExcess;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal totalTrailerValue;
    @XmlElement(required = true, nillable = true)
    protected String extraField1;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal sumInsuredTotalNamedPa;
    @XmlElement(required = true, nillable = true)
    protected String geogExtn;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal noOfPersonsLlo;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal noOfEmployeesLle;
    @XmlElement(required = true, nillable = true)
    protected String extraField2;
    @XmlElement(required = true, nillable = true)
    protected String extraField3;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal sumInsuredPa;
    @XmlElement(required = true, nillable = true)
    protected BigDecimal noOfPersonsPa;

    /**
     * Gets the value of the sideCarValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSideCarValue() {
        return sideCarValue;
    }

    /**
     * Sets the value of the sideCarValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSideCarValue(BigDecimal value) {
        this.sideCarValue = value;
    }

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
     * Gets the value of the noOfTrailers property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNoOfTrailers() {
        return noOfTrailers;
    }

    /**
     * Sets the value of the noOfTrailers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNoOfTrailers(BigDecimal value) {
        this.noOfTrailers = value;
    }

    /**
     * Gets the value of the subImdcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubImdcode() {
        return subImdcode;
    }

    /**
     * Sets the value of the subImdcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubImdcode(String value) {
        this.subImdcode = value;
    }

    /**
     * Gets the value of the covernoteDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCovernoteDate() {
        return covernoteDate;
    }

    /**
     * Sets the value of the covernoteDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCovernoteDate(String value) {
        this.covernoteDate = value;
    }

    /**
     * Gets the value of the cngValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCngValue() {
        return cngValue;
    }

    /**
     * Sets the value of the cngValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCngValue(BigDecimal value) {
        this.cngValue = value;
    }

    /**
     * Gets the value of the fibreGlassValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFibreGlassValue() {
        return fibreGlassValue;
    }

    /**
     * Sets the value of the fibreGlassValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFibreGlassValue(BigDecimal value) {
        this.fibreGlassValue = value;
    }

    /**
     * Gets the value of the voluntaryExcess property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVoluntaryExcess() {
        return voluntaryExcess;
    }

    /**
     * Sets the value of the voluntaryExcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVoluntaryExcess(BigDecimal value) {
        this.voluntaryExcess = value;
    }

    /**
     * Gets the value of the totalTrailerValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalTrailerValue() {
        return totalTrailerValue;
    }

    /**
     * Sets the value of the totalTrailerValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalTrailerValue(BigDecimal value) {
        this.totalTrailerValue = value;
    }

    /**
     * Gets the value of the extraField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraField1() {
        return extraField1;
    }

    /**
     * Sets the value of the extraField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraField1(String value) {
        this.extraField1 = value;
    }

    /**
     * Gets the value of the sumInsuredTotalNamedPa property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumInsuredTotalNamedPa() {
        return sumInsuredTotalNamedPa;
    }

    /**
     * Sets the value of the sumInsuredTotalNamedPa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumInsuredTotalNamedPa(BigDecimal value) {
        this.sumInsuredTotalNamedPa = value;
    }

    /**
     * Gets the value of the geogExtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeogExtn() {
        return geogExtn;
    }

    /**
     * Sets the value of the geogExtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeogExtn(String value) {
        this.geogExtn = value;
    }

    /**
     * Gets the value of the noOfPersonsLlo property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNoOfPersonsLlo() {
        return noOfPersonsLlo;
    }

    /**
     * Sets the value of the noOfPersonsLlo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNoOfPersonsLlo(BigDecimal value) {
        this.noOfPersonsLlo = value;
    }

    /**
     * Gets the value of the noOfEmployeesLle property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNoOfEmployeesLle() {
        return noOfEmployeesLle;
    }

    /**
     * Sets the value of the noOfEmployeesLle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNoOfEmployeesLle(BigDecimal value) {
        this.noOfEmployeesLle = value;
    }

    /**
     * Gets the value of the extraField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraField2() {
        return extraField2;
    }

    /**
     * Sets the value of the extraField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraField2(String value) {
        this.extraField2 = value;
    }

    /**
     * Gets the value of the extraField3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraField3() {
        return extraField3;
    }

    /**
     * Sets the value of the extraField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraField3(String value) {
        this.extraField3 = value;
    }

    /**
     * Gets the value of the sumInsuredPa property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSumInsuredPa() {
        return sumInsuredPa;
    }

    /**
     * Sets the value of the sumInsuredPa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSumInsuredPa(BigDecimal value) {
        this.sumInsuredPa = value;
    }

    /**
     * Gets the value of the noOfPersonsPa property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNoOfPersonsPa() {
        return noOfPersonsPa;
    }

    /**
     * Sets the value of the noOfPersonsPa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNoOfPersonsPa(BigDecimal value) {
        this.noOfPersonsPa = value;
    }

}
