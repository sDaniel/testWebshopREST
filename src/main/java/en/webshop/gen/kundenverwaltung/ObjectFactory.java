//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.13 at 04:54:22 PM MEZ 
//


package en.webshop.gen.kundenverwaltung;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the en.webshop.gen.kundenverwaltung package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Kunden_QNAME = new QName("http://hska.de/kundenverwaltung", "kunden");
    private final static QName _Bank_QNAME = new QName("http://hska.de/kundenverwaltung", "bank");
    private final static QName _Kunde_QNAME = new QName("http://hska.de/kundenverwaltung", "kunde");
    private final static QName _Adresse_QNAME = new QName("http://hska.de/kundenverwaltung", "adresse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: en.webshop.gen.kundenverwaltung
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Bank }
     * 
     */
    public Bank createBank() {
        return new Bank();
    }

    /**
     * Create an instance of {@link Kunde.Bestellungen }
     * 
     */
    public Kunde.Bestellungen createKundeBestellungen() {
        return new Kunde.Bestellungen();
    }

    /**
     * Create an instance of {@link KundeList }
     * 
     */
    public KundeList createKundeList() {
        return new KundeList();
    }

    /**
     * Create an instance of {@link Adresse }
     * 
     */
    public Adresse createAdresse() {
        return new Adresse();
    }

    /**
     * Create an instance of {@link Kunde }
     * 
     */
    public Kunde createKunde() {
        return new Kunde();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KundeList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hska.de/kundenverwaltung", name = "kunden")
    public JAXBElement<KundeList> createKunden(KundeList value) {
        return new JAXBElement<KundeList>(_Kunden_QNAME, KundeList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bank }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hska.de/kundenverwaltung", name = "bank")
    public JAXBElement<Bank> createBank(Bank value) {
        return new JAXBElement<Bank>(_Bank_QNAME, Bank.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Kunde }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hska.de/kundenverwaltung", name = "kunde")
    public JAXBElement<Kunde> createKunde(Kunde value) {
        return new JAXBElement<Kunde>(_Kunde_QNAME, Kunde.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Adresse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hska.de/kundenverwaltung", name = "adresse")
    public JAXBElement<Adresse> createAdresse(Adresse value) {
        return new JAXBElement<Adresse>(_Adresse_QNAME, Adresse.class, null, value);
    }

}
