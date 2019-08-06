
package org.wipo.connect.interestedparty.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IpNamesType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="IpNamesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nameType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IpNamesType", propOrder = {
    "name",
    "firstName",
    "nameType",
    "mainId"
})
public class IpNamesType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String nameType;
    @XmlElement(required = true)
    protected String mainId;

    /**
     * Recupera il valore della proprietàname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietàname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietàfirstName.
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
     * Imposta il valore della proprietàfirstName.
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
     * Recupera il valore della proprietànameType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameType() {
        return nameType;
    }

    /**
     * Imposta il valore della proprietànameType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    /**
     * Recupera il valore della proprietàmainId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * Imposta il valore della proprietàmainId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainId(String value) {
        this.mainId = value;
    }

}
