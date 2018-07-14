
package com.bajajAllianz;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeoMotAccessoriesUserArray complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeoMotAccessoriesUserArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WeoMotAccessoriesUser" type="{http://com/bajajallianz/motWebPolicy/WebServicePolicy.wsdl/types/}WeoMotAccessoriesUser" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeoMotAccessoriesUserArray", propOrder = {
    "weoMotAccessoriesUser"
})
public class WeoMotAccessoriesUserArray {

    @XmlElement(name = "WeoMotAccessoriesUser", nillable = true)
    protected List<WeoMotAccessoriesUser> weoMotAccessoriesUser;

    /**
     * Gets the value of the weoMotAccessoriesUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weoMotAccessoriesUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeoMotAccessoriesUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeoMotAccessoriesUser }
     * 
     * 
     */
    public List<WeoMotAccessoriesUser> getWeoMotAccessoriesUser() {
        if (weoMotAccessoriesUser == null) {
            weoMotAccessoriesUser = new ArrayList<WeoMotAccessoriesUser>();
        }
        return this.weoMotAccessoriesUser;
    }

	public void setWeoMotAccessoriesUser(List<WeoMotAccessoriesUser> weoMotAccessoriesUser) {
		this.weoMotAccessoriesUser = weoMotAccessoriesUser;
	}

	    
    

}
