
package com.bajajAllianz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoBjazMotQuestionaryUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoBjazMotQuestionaryUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoBjazMotQuestionaryBase">
 *       &lt;sequence>
 *         &lt;element name="questionRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contractId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="questionVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoBjazMotQuestionaryUser", propOrder = {
    "questionRef",
    "contractId",
    "questionVal"
})
public class WeoBjazMotQuestionaryUser
    extends WeoBjazMotQuestionaryBase
{

    @XmlElement(required = true, nillable = true)
    protected String questionRef;
    @XmlElement(required = true, nillable = true)
    protected String contractId;
    @XmlElement(required = true, nillable = true)
    protected String questionVal;

    /**
     * Gets the value of the questionRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionRef() {
        return questionRef;
    }

    /**
     * Sets the value of the questionRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionRef(String value) {
        this.questionRef = value;
    }

    /**
     * Gets the value of the contractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractId(String value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the questionVal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuestionVal() {
        return questionVal;
    }

    /**
     * Sets the value of the questionVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuestionVal(String value) {
        this.questionVal = value;
    }

}
