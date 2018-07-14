
package com.shreeRamGIC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PCCVProposalResponseETT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PCCVProposalResponseETT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="POL_SYS_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PROPOSAL_NO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VehicleIDV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ERROR_DESC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ERROR_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CoverDtlList" type="{http://tempuri.org/}ArrayOfCoverDtl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PCCVProposalResponseETT", propOrder = {
    "polsysid",
    "proposalno",
    "vehicleIDV",
    "errordesc",
    "errorcode",
    "coverDtlList"
})
public class PCCVProposalResponseETT {

    @XmlElement(name = "POL_SYS_ID")
    protected String polsysid;
    @XmlElement(name = "PROPOSAL_NO")
    protected String proposalno;
    @XmlElement(name = "VehicleIDV")
    protected String vehicleIDV;
    @XmlElement(name = "ERROR_DESC")
    protected String errordesc;
    @XmlElement(name = "ERROR_CODE")
    protected String errorcode;
    @XmlElement(name = "CoverDtlList")
    protected ArrayOfCoverDtl coverDtlList;

    /**
     * Gets the value of the polsysid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOLSYSID() {
        return polsysid;
    }

    /**
     * Sets the value of the polsysid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOLSYSID(String value) {
        this.polsysid = value;
    }

    /**
     * Gets the value of the proposalno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROPOSALNO() {
        return proposalno;
    }

    /**
     * Sets the value of the proposalno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROPOSALNO(String value) {
        this.proposalno = value;
    }

    /**
     * Gets the value of the vehicleIDV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleIDV() {
        return vehicleIDV;
    }

    /**
     * Sets the value of the vehicleIDV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleIDV(String value) {
        this.vehicleIDV = value;
    }

    /**
     * Gets the value of the errordesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRORDESC() {
        return errordesc;
    }

    /**
     * Sets the value of the errordesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRORDESC(String value) {
        this.errordesc = value;
    }

    /**
     * Gets the value of the errorcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERRORCODE() {
        return errorcode;
    }

    /**
     * Sets the value of the errorcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERRORCODE(String value) {
        this.errorcode = value;
    }

    /**
     * Gets the value of the coverDtlList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCoverDtl }
     *     
     */
    public ArrayOfCoverDtl getCoverDtlList() {
        return coverDtlList;
    }

    /**
     * Sets the value of the coverDtlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCoverDtl }
     *     
     */
    public void setCoverDtlList(ArrayOfCoverDtl value) {
        this.coverDtlList = value;
    }

}
