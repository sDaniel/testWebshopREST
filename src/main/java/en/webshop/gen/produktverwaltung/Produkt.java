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
 * <p>Java class for produkt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="produkt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="produktkategorie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hersteller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="produktname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="produktbeschreibung" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="farbe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preis" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="breite" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="laenge" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="gewicht" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="bestand" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="produktbewertungen" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="produktbewertung" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "produkt", propOrder = {
    "produktkategorie",
    "hersteller",
    "produktname",
    "produktbeschreibung",
    "farbe",
    "preis",
    "breite",
    "laenge",
    "gewicht",
    "bestand",
    "produktbewertungen"
})
public class Produkt {

    protected String produktkategorie;
    protected String hersteller;
    @XmlElement(required = true)
    protected String produktname;
    protected String produktbeschreibung;
    protected String farbe;
    protected double preis;
    protected double breite;
    protected double laenge;
    protected double gewicht;
    protected int bestand;
    protected Produkt.Produktbewertungen produktbewertungen;
    @XmlAttribute(name = "id", required = true)
    protected long id;

    /**
     * Gets the value of the produktkategorie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduktkategorie() {
        return produktkategorie;
    }

    /**
     * Sets the value of the produktkategorie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduktkategorie(String value) {
        this.produktkategorie = value;
    }

    /**
     * Gets the value of the hersteller property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHersteller() {
        return hersteller;
    }

    /**
     * Sets the value of the hersteller property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHersteller(String value) {
        this.hersteller = value;
    }

    /**
     * Gets the value of the produktname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduktname() {
        return produktname;
    }

    /**
     * Sets the value of the produktname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduktname(String value) {
        this.produktname = value;
    }

    /**
     * Gets the value of the produktbeschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduktbeschreibung() {
        return produktbeschreibung;
    }

    /**
     * Sets the value of the produktbeschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduktbeschreibung(String value) {
        this.produktbeschreibung = value;
    }

    /**
     * Gets the value of the farbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFarbe() {
        return farbe;
    }

    /**
     * Sets the value of the farbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFarbe(String value) {
        this.farbe = value;
    }

    /**
     * Gets the value of the preis property.
     * 
     */
    public double getPreis() {
        return preis;
    }

    /**
     * Sets the value of the preis property.
     * 
     */
    public void setPreis(double value) {
        this.preis = value;
    }

    /**
     * Gets the value of the breite property.
     * 
     */
    public double getBreite() {
        return breite;
    }

    /**
     * Sets the value of the breite property.
     * 
     */
    public void setBreite(double value) {
        this.breite = value;
    }

    /**
     * Gets the value of the laenge property.
     * 
     */
    public double getLaenge() {
        return laenge;
    }

    /**
     * Sets the value of the laenge property.
     * 
     */
    public void setLaenge(double value) {
        this.laenge = value;
    }

    /**
     * Gets the value of the gewicht property.
     * 
     */
    public double getGewicht() {
        return gewicht;
    }

    /**
     * Sets the value of the gewicht property.
     * 
     */
    public void setGewicht(double value) {
        this.gewicht = value;
    }

    /**
     * Gets the value of the bestand property.
     * 
     */
    public int getBestand() {
        return bestand;
    }

    /**
     * Sets the value of the bestand property.
     * 
     */
    public void setBestand(int value) {
        this.bestand = value;
    }

    /**
     * Gets the value of the produktbewertungen property.
     * 
     * @return
     *     possible object is
     *     {@link Produkt.Produktbewertungen }
     *     
     */
    public Produkt.Produktbewertungen getProduktbewertungen() {
        return produktbewertungen;
    }

    /**
     * Sets the value of the produktbewertungen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Produkt.Produktbewertungen }
     *     
     */
    public void setProduktbewertungen(Produkt.Produktbewertungen value) {
        this.produktbewertungen = value;
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
     *         &lt;element name="produktbewertung" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "produktbewertung"
    })
    public static class Produktbewertungen {

        protected List<String> produktbewertung;

        /**
         * Gets the value of the produktbewertung property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the produktbewertung property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProduktbewertung().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getProduktbewertung() {
            if (produktbewertung == null) {
                produktbewertung = new ArrayList<String>();
            }
            return this.produktbewertung;
        }

    }

}
