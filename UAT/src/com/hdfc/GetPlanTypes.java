
package com.hdfc;

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
 *         &lt;element name="AgentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VehicleModelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RTOLocationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PolicyStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstRegistrationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "agentCode",
    "businessType",
    "vehicleModelCode",
    "rtoLocationCode",
    "policyStartDate",
    "firstRegistrationDate"
})
@XmlRootElement(name = "GetPlanTypes")
public class GetPlanTypes {

    @XmlElement(name = "AgentCode")
    protected String agentCode;
    @XmlElement(name = "BusinessType")
    protected String businessType;
    @XmlElement(name = "VehicleModelCode")
    protected String vehicleModelCode;
    @XmlElement(name = "RTOLocationCode")
    protected String rtoLocationCode;
    @XmlElement(name = "PolicyStartDate")
    protected String policyStartDate;
    @XmlElement(name = "FirstRegistrationDate")
    protected String firstRegistrationDate;

    /**
     * Gets the value of the agentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * Sets the value of the agentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentCode(String value) {
        this.agentCode = value;
    }

    /**
     * Gets the value of the businessType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Sets the value of the businessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessType(String value) {
        this.businessType = value;
    }

    /**
     * Gets the value of the vehicleModelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    /**
     * Sets the value of the vehicleModelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleModelCode(String value) {
        this.vehicleModelCode = value;
    }

    /**
     * Gets the value of the rtoLocationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRTOLocationCode() {
        return rtoLocationCode;
    }

    /**
     * Sets the value of the rtoLocationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRTOLocationCode(String value) {
        this.rtoLocationCode = value;
    }

    /**
     * Gets the value of the policyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyStartDate() {
        return policyStartDate;
    }

    /**
     * Sets the value of the policyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyStartDate(String value) {
        this.policyStartDate = value;
    }

    /**
     * Gets the value of the firstRegistrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    /**
     * Sets the value of the firstRegistrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstRegistrationDate(String value) {
        this.firstRegistrationDate = value;
    }

}
