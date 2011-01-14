//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.13 at 04:54:22 PM MEZ 
//


package en.webshop.gen.produktverwaltung;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hersteller complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hersteller">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="herstellername" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="produkte" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="produkt" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hersteller", propOrder = {
    "herstellername",
    "produkte"
})
public class Hersteller {

    @XmlElement(required = true)
    protected String herstellername;
    protected Hersteller.Produkte produkte;
    @XmlAttribute(name = "id", required = true)
    protected long id;

    /**
     * Gets the value of the herstellername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHerstellername() {
        return herstellername;
    }

    /**
     * Sets the value of the herstellername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHerstellername(String value) {
        this.herstellername = value;
    }

    /**
     * Gets the value of the produkte property.
     * 
     * @return
     *     possible object is
     *     {@link Hersteller.Produkte }
     *     
     */
    public Hersteller.Produkte getProdukte() {
        return produkte;
    }

    /**
     * Sets the value of the produkte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hersteller.Produkte }
     *     
     */
    public void setProdukte(Hersteller.Produkte value) {
        this.produkte = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }


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
     *         &lt;element name="produkt" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "produkt"
    })
    public static class Produkte {

        protected List<String> produkt;

        /**
         * Gets the value of the produkt property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the produkt property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProdukt().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getProdukt() {
            if (produkt == null) {
                produkt = new ArrayList<String>();
            }
            return this.produkt;
        }

    }

}