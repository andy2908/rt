
package com.bajajAllianz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoB2cCustDetailsUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoB2cCustDetailsUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoB2cCustDetailsBase">
 *       &lt;sequence>
 *         &lt;element name="telephone1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telephone2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="institutionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="profession" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="existingYn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="delivaryOption" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobileAlerts" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loggedIn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partTempId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="availableTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pincode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polAddLine1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polAddLine3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polAddLine2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addLine5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polAddLine5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="polPincode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailAlerts" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoB2cCustDetailsUser", propOrder = {
    "telephone1",
    "dateOfBirth",
    "telephone2",
    "status1",
    "institutionName",
    "profession",
    "existingYn",
    "addLine3",
    "surname",
    "addLine2",
    "partId",
    "addLine1",
    "password",
    "title",
    "delivaryOption",
    "firstName",
    "middleName",
    "mobileAlerts",
    "loggedIn",
    "partTempId",
    "availableTime",
    "pincode",
    "polAddLine1",
    "polAddLine3",
    "polAddLine2",
    "addLine5",
    "polAddLine5",
    "polPincode",
    "cpType",
    "email",
    "status3",
    "status2",
    "emailAlerts",
    "mobile"
})
public class WeoB2CCustDetailsUser
    extends WeoB2CCustDetailsBase
{

    @XmlElement(required = true, nillable = true)
    protected String telephone1;
    @XmlElement(required = true, nillable = true)
    protected String dateOfBirth;
    @XmlElement(required = true, nillable = true)
    protected String telephone2;
    @XmlElement(required = true, nillable = true)
    protected String status1;
    @XmlElement(required = true, nillable = true)
    protected String institutionName;
    @XmlElement(required = true, nillable = true)
    protected String profession;
    @XmlElement(required = true, nillable = true)
    protected String existingYn;
    @XmlElement(required = true, nillable = true)
    protected String addLine3;
    @XmlElement(required = true, nillable = true)
    protected String surname;
    @XmlElement(required = true, nillable = true)
    protected String addLine2;
    @XmlElement(required = true, nillable = true)
    protected String partId;
    @XmlElement(required = true, nillable = true)
    protected String addLine1;
    @XmlElement(required = true, nillable = true)
    protected String password;
    @XmlElement(required = true, nillable = true)
    protected String title;
    @XmlElement(required = true, nillable = true)
    protected String delivaryOption;
    @XmlElement(required = true, nillable = true)
    protected String firstName;
    @XmlElement(required = true, nillable = true)
    protected String middleName;
    @XmlElement(required = true, nillable = true)
    protected String mobileAlerts;
    @XmlElement(required = true, nillable = true)
    protected String loggedIn;
    @XmlElement(required = true, nillable = true)
    protected String partTempId;
    @XmlElement(required = true, nillable = true)
    protected String availableTime;
    @XmlElement(required = true, nillable = true)
    protected String pincode;
    @XmlElement(required = true, nillable = true)
    protected String polAddLine1;
    @XmlElement(required = true, nillable = true)
    protected String polAddLine3;
    @XmlElement(required = true, nillable = true)
    protected String polAddLine2;
    @XmlElement(required = true, nillable = true)
    protected String addLine5;
    @XmlElement(required = true, nillable = true)
    protected String polAddLine5;
    @XmlElement(required = true, nillable = true)
    protected String polPincode;
    @XmlElement(required = true, nillable = true)
    protected String cpType;
    @XmlElement(required = true, nillable = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String status3;
    @XmlElement(required = true, nillable = true)
    protected String status2;
    @XmlElement(required = true, nillable = true)
    protected String emailAlerts;
    @XmlElement(required = true, nillable = true)
    protected String mobile;

    /**
     * Gets the value of the telephone1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone1() {
        return telephone1;
    }

    /**
     * Sets the value of the telephone1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone1(String value) {
        this.telephone1 = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the telephone2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone2() {
        return telephone2;
    }

    /**
     * Sets the value of the telephone2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone2(String value) {
        this.telephone2 = value;
    }

    /**
     * Gets the value of the status1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus1() {
        return status1;
    }

    /**
     * Sets the value of the status1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus1(String value) {
        this.status1 = value;
    }

    /**
     * Gets the value of the institutionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * Sets the value of the institutionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitutionName(String value) {
        this.institutionName = value;
    }

    /**
     * Gets the value of the profession property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Sets the value of the profession property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfession(String value) {
        this.profession = value;
    }

    /**
     * Gets the value of the existingYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExistingYn() {
        return existingYn;
    }

    /**
     * Sets the value of the existingYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExistingYn(String value) {
        this.existingYn = value;
    }

    /**
     * Gets the value of the addLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLine3() {
        return addLine3;
    }

    /**
     * Sets the value of the addLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLine3(String value) {
        this.addLine3 = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the addLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLine2() {
        return addLine2;
    }

    /**
     * Sets the value of the addLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLine2(String value) {
        this.addLine2 = value;
    }

    /**
     * Gets the value of the partId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartId() {
        return partId;
    }

    /**
     * Sets the value of the partId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartId(String value) {
        this.partId = value;
    }

    /**
     * Gets the value of the addLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLine1() {
        return addLine1;
    }

    /**
     * Sets the value of the addLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLine1(String value) {
        this.addLine1 = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the delivaryOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelivaryOption() {
        return delivaryOption;
    }

    /**
     * Sets the value of the delivaryOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelivaryOption(String value) {
        this.delivaryOption = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the mobileAlerts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileAlerts() {
        return mobileAlerts;
    }

    /**
     * Sets the value of the mobileAlerts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileAlerts(String value) {
        this.mobileAlerts = value;
    }

    /**
     * Gets the value of the loggedIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the value of the loggedIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedIn(String value) {
        this.loggedIn = value;
    }

    /**
     * Gets the value of the partTempId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartTempId() {
        return partTempId;
    }

    /**
     * Sets the value of the partTempId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartTempId(String value) {
        this.partTempId = value;
    }

    /**
     * Gets the value of the availableTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailableTime() {
        return availableTime;
    }

    /**
     * Sets the value of the availableTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailableTime(String value) {
        this.availableTime = value;
    }

    /**
     * Gets the value of the pincode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets the value of the pincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPincode(String value) {
        this.pincode = value;
    }

    /**
     * Gets the value of the polAddLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolAddLine1() {
        return polAddLine1;
    }

    /**
     * Sets the value of the polAddLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolAddLine1(String value) {
        this.polAddLine1 = value;
    }

    /**
     * Gets the value of the polAddLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolAddLine3() {
        return polAddLine3;
    }

    /**
     * Sets the value of the polAddLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolAddLine3(String value) {
        this.polAddLine3 = value;
    }

    /**
     * Gets the value of the polAddLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolAddLine2() {
        return polAddLine2;
    }

    /**
     * Sets the value of the polAddLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolAddLine2(String value) {
        this.polAddLine2 = value;
    }

    /**
     * Gets the value of the addLine5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddLine5() {
        return addLine5;
    }

    /**
     * Sets the value of the addLine5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddLine5(String value) {
        this.addLine5 = value;
    }

    /**
     * Gets the value of the polAddLine5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolAddLine5() {
        return polAddLine5;
    }

    /**
     * Sets the value of the polAddLine5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolAddLine5(String value) {
        this.polAddLine5 = value;
    }

    /**
     * Gets the value of the polPincode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolPincode() {
        return polPincode;
    }

    /**
     * Sets the value of the polPincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolPincode(String value) {
        this.polPincode = value;
    }

    /**
     * Gets the value of the cpType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpType() {
        return cpType;
    }

    /**
     * Sets the value of the cpType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpType(String value) {
        this.cpType = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the status3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus3() {
        return status3;
    }

    /**
     * Sets the value of the status3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus3(String value) {
        this.status3 = value;
    }

    /**
     * Gets the value of the status2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus2() {
        return status2;
    }

    /**
     * Sets the value of the status2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus2(String value) {
        this.status2 = value;
    }

    /**
     * Gets the value of the emailAlerts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAlerts() {
        return emailAlerts;
    }

    /**
     * Sets the value of the emailAlerts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAlerts(String value) {
        this.emailAlerts = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

}
