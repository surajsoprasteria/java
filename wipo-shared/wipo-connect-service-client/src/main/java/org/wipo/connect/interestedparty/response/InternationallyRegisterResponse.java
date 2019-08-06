
package org.wipo.connect.interestedparty.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.wipo.connect.common.output.ErrorType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ipResponse">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="registrationAck" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Error" type="{http://output.common.connect.wipo.org}ErrorType" minOccurs="0"/>
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
    "ipResponse",
    "error"
})
@XmlRootElement(name = "InternationallyRegisterResponse")
public class InternationallyRegisterResponse {

    @XmlElement(required = true, nillable = true)
    protected InternationallyRegisterResponse.IpResponse ipResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;

    /**
     * Recupera il valore della proprietàipResponse.
     * 
     * @return
     *     possible object is
     *     {@link InternationallyRegisterResponse.IpResponse }
     *     
     */
    public InternationallyRegisterResponse.IpResponse getIpResponse() {
        return ipResponse;
    }

    /**
     * Imposta il valore della proprietàipResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationallyRegisterResponse.IpResponse }
     *     
     */
    public void setIpResponse(InternationallyRegisterResponse.IpResponse value) {
        this.ipResponse = value;
    }

    /**
     * Recupera il valore della proprietàerror.
     * 
     * @return
     *     possible object is
     *     {@link ErrorType }
     *     
     */
    public ErrorType getError() {
        return error;
    }

    /**
     * Imposta il valore della proprietàerror.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrorType }
     *     
     */
    public void setError(ErrorType value) {
        this.error = value;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="registrationAck" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "registrationAck"
    })
    public static class IpResponse {

        @XmlElement(required = true)
        protected String registrationAck;

        /**
         * Recupera il valore della proprietàregistrationAck.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRegistrationAck() {
            return registrationAck;
        }

        /**
         * Imposta il valore della proprietàregistrationAck.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRegistrationAck(String value) {
            this.registrationAck = value;
        }

    }

}
