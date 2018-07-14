
package com.bajajAllianz;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoMotGenParamUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoMotGenParamUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoMotGenParamBase">
 *       &lt;sequence>
 *         &lt;element name="paramDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paramRef" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoMotGenParamUser", propOrder = {
    "paramDesc",
    "paramRef"
})
public class WeoMotGenParamUser
    extends WeoMotGenParamBase
{

    @XmlElement(required = true, nillable = true)
    protected String paramDesc;
    @XmlElement(required = true, nillable = true)
    protected String paramRef;

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

}
