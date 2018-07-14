
package com.shreeRamGIC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="objPolicyApprovalETT" type="{http://tempuri.org/}PolicyApprovalETT" minOccurs="0"/>
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
    "objPolicyApprovalETT"
})
@XmlRootElement(name = "PolicyApprove")
public class PolicyApprove {

    protected PolicyApprovalETT objPolicyApprovalETT;

    /**
     * Gets the value of the objPolicyApprovalETT property.
     * 
     * @return
     *     possible object is
     *     {@link PolicyApprovalETT }
     *     
     */
    public PolicyApprovalETT getObjPolicyApprovalETT() {
        return objPolicyApprovalETT;
    }

    /**
     * Sets the value of the objPolicyApprovalETT property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolicyApprovalETT }
     *     
     */
    public void setObjPolicyApprovalETT(PolicyApprovalETT value) {
        this.objPolicyApprovalETT = value;
    }

}
