
package com.bajajAllianz;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoMotGenParamUserArray complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoMotGenParamUserArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WeoMotGenParamUser" type="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoMotGenParamUser" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoMotGenParamUserArray", propOrder = {
    "weoMotGenParamUser"
})
public class WeoMotGenParamUserArray {

    @XmlElement(name = "WeoMotGenParamUser", nillable = true)
    protected List<WeoMotGenParamUser> weoMotGenParamUser;

    /**
     * Gets the value of the weoMotGenParamUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weoMotGenParamUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeoMotGenParamUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeoMotGenParamUser }
     * 
     * 
     */
    public List<WeoMotGenParamUser> getWeoMotGenParamUser() {
        if (weoMotGenParamUser == null) {
            weoMotGenParamUser = new ArrayList<WeoMotGenParamUser>();
        }      
        return this.weoMotGenParamUser;
    }

	public void setWeoMotGenParamUser(List<WeoMotGenParamUser> weoMotGenParamUser) {
		this.weoMotGenParamUser = weoMotGenParamUser;
	}

}
