
package org.wipo.connect.interestedparty.response;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per Duplicates complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Duplicates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sex" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ipiBaseNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="deathDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="birthPlace" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthCountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maritalStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nameList" type="{http://response.interestedParty.connect.wipo.org}IpNamesType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Duplicates", propOrder = {
    "type",
    "sex",
    "ipiBaseNumber",
    "birthDate",
    "deathDate",
    "birthPlace",
    "birthState",
    "birthCountryCode",
    "maritalStatus",
    "nameList"
})
public class Duplicates {

    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String sex;
    @XmlElement(required = true)
    protected String ipiBaseNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deathDate;
    @XmlElement(required = true)
    protected String birthPlace;
    @XmlElement(required = true)
    protected String birthState;
    @XmlElement(required = true)
    protected String birthCountryCode;
    @XmlElement(required = true)
    protected String maritalStatus;
    @XmlElement(required = true)
    protected List<IpNamesType> nameList;

    /**
     * Recupera il valore della proprietàtype.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietàtype.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Recupera il valore della proprietàsex.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * Imposta il valore della proprietàsex.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Recupera il valore della proprietàipiBaseNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpiBaseNumber() {
        return ipiBaseNumber;
    }

    /**
     * Imposta il valore della proprietàipiBaseNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpiBaseNumber(String value) {
        this.ipiBaseNumber = value;
    }

    /**
     * Recupera il valore della proprietàbirthDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Imposta il valore della proprietàbirthDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Recupera il valore della proprietàdeathDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeathDate() {
        return deathDate;
    }

    /**
     * Imposta il valore della proprietàdeathDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeathDate(XMLGregorianCalendar value) {
        this.deathDate = value;
    }

    /**
     * Recupera il valore della proprietàbirthPlace.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Imposta il valore della proprietàbirthPlace.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthPlace(String value) {
        this.birthPlace = value;
    }

    /**
     * Recupera il valore della proprietàbirthState.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthState() {
        return birthState;
    }

    /**
     * Imposta il valore della proprietàbirthState.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthState(String value) {
        this.birthState = value;
    }

    /**
     * Recupera il valore della proprietàbirthCountryCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthCountryCode() {
        return birthCountryCode;
    }

    /**
     * Imposta il valore della proprietàbirthCountryCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthCountryCode(String value) {
        this.birthCountryCode = value;
    }

    /**
     * Recupera il valore della proprietàmaritalStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Imposta il valore della proprietàmaritalStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatus(String value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the nameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IpNamesType }
     * 
     * 
     */
    public List<IpNamesType> getNameList() {
        if (nameList == null) {
            nameList = new ArrayList<IpNamesType>();
        }
        return this.nameList;
    }

}
