//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.13 at 04:54:22 PM MEZ 
//


package en.webshop.gen.bestellverwaltung;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lieferung complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lieferung">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bestellung" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lieferungsdatum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="lieferungsId" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lieferung", propOrder = {
    "bestellung",
    "lieferungsdatum"
})
public class Lieferung {

    protected String bestellung;
    protected String lieferungsdatum;
    @XmlAttribute(name = "lieferungsId", required = true)
    protected long lieferungsId;

    /**
     * Gets the value of the bestellung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBestellung() {
        return bestellung;
    }

    /**
     * Sets the value of the bestellung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBestellung(String value) {
        this.bestellung = value;
    }

    /**
     * Gets the value of the lieferungsdatum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLieferungsdatum() {
        return lieferungsdatum;
    }

    /**
     * Sets the value of the lieferungsdatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLieferungsdatum(String value) {
        this.lieferungsdatum = value;
    }

    /**
     * Gets the value of the lieferungsId property.
     * 
     */
    public long getLieferungsId() {
        return lieferungsId;
    }

    /**
     * Sets the value of the lieferungsId property.
     * 
     */
    public void setLieferungsId(long value) {
        this.lieferungsId = value;
    }

}
