
package org.wipo.connect.interestedparty.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AffiliationDomainType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AffiliationDomainType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ipiRightTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creationClassCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ipiRoleCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isExcluded" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffiliationDomainType", propOrder = {
    "ipiRightTypeCode",
    "creationClassCode",
    "ipiRoleCode",
    "isExcluded"
})
public class AffiliationDomainType {

    @XmlElement(required = true)
    protected String ipiRightTypeCode;
    @XmlElement(required = true)
    protected String creationClassCode;
    @XmlElement(required = true)
    protected String ipiRoleCode;
    protected boolean isExcluded;

    /**
     * Recupera il valore della proprietàipiRightTypeCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpiRightTypeCode() {
        return ipiRightTypeCode;
    }

    /**
     * Imposta il valore della proprietàipiRightTypeCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpiRightTypeCode(String value) {
        this.ipiRightTypeCode = value;
    }

    /**
     * Recupera il valore della proprietàcreationClassCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationClassCode() {
        return creationClassCode;
    }

    /**
     * Imposta il valore della proprietàcreationClassCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationClassCode(String value) {
        this.creationClassCode = value;
    }

    /**
     * Recupera il valore della proprietàipiRoleCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpiRoleCode() {
        return ipiRoleCode;
    }

    /**
     * Imposta il valore della proprietàipiRoleCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpiRoleCode(String value) {
        this.ipiRoleCode = value;
    }

    /**
     * Recupera il valore della proprietàisExcluded.
     * 
     */
    public boolean isIsExcluded() {
        return isExcluded;
    }

    /**
     * Imposta il valore della proprietàisExcluded.
     * 
     */
    public void setIsExcluded(boolean value) {
        this.isExcluded = value;
    }

}
