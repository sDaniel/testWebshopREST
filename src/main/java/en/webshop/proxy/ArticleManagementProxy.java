package en.webshop.proxy;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.client.core.BaseClientResponse;

import en.webshop.gen.bestellverwaltung.Bestellung;
import en.webshop.gen.bestellverwaltung.Lieferung;
import en.webshop.gen.kundenverwaltung.Kunde;
import en.webshop.gen.produktverwaltung.Hersteller;
import en.webshop.gen.produktverwaltung.Produkt;
import en.webshop.gen.produktverwaltung.ProduktList;
import en.webshop.gen.produktverwaltung.Produktbewertung;
import en.webshop.gen.produktverwaltung.Produktkategorie;



@Path("/articleManagement")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON})
@Consumes
public interface ArticleManagementProxy {
	
	@GET
	@Path("/test")
	public String test();
	
//	@GET
//	@Path("/produktbewertung/{id:[0-9]+}")
//	public Produktbewertung findProduktbewertung(@PathParam("id") Long bewertungsId);
//	
//	@GET
//	@Path("/produkte/{id:[0-9]+}")
//	public Produkt findProdukt(@PathParam("id") Long id);
//	
//	@Path("/produktbewertungen")
//	@POST
//	@Consumes({APPLICATION_XML, TEXT_XML})
//	@Produces
//	public BaseClientResponse<String> createProduktbewertung(Produktbewertung produktbewertung);
//	
//	@Path("/produktbewertungen/{id:[0-9]+}")
//	@DELETE
//	@Produces
//	public BaseClientResponse<String> deleteProduktbewertung(@PathParam("id") long Id);
//	
//	@GET
//	@Path("/produkte")
//	public ProduktList findProdukte(@QueryParam("produktname") @DefaultValue("") String produktname);
//
//	@GET
//	@Path("/produkte/{id:[0-9]+}/bestellungen")
//	public Bestellung findBestellungByProduktId(@PathParam("id") Long id);
//
//	@Path("/produkte")
//	@POST
//	@Consumes({APPLICATION_XML, TEXT_XML})
//	@Produces
//	public BaseClientResponse<String> createProdukt(Produkt produkt);
//	
//	@Path("/produkte")
//	@POST
//	@Consumes({ APPLICATION_XML, TEXT_XML })
//	//@Produces
//	Response createProdukt(Produkt produkt, @Context HttpHeaders headers, @Context UriInfo uriInfo);
//	        
//	
//	@Path("/produkte/form")
//	@POST
//	@Consumes(APPLICATION_FORM_URLENCODED)
//	@Produces
//	public BaseClientResponse<String> createProdukt(@FormParam("produktname") String produktname,
//            @FormParam("produktkategorie") String produktkategoriename,
//            @FormParam("hersteller") String herstellername,
//            @FormParam("farbe") String farbe,
//            @FormParam("breite") Double breite,
//            @FormParam("laenge") Double laenge,
//            @FormParam("gewicht") Double gewicht,
//            @FormParam("bestand") Integer bestand,
//            @FormParam("preis") Double preis,
//            @FormParam("produktbeschreibung")String produktbeschreibung);
//
//	@Path("/produkte")
//	@PUT
//	@Consumes({APPLICATION_XML, TEXT_XML})
//	public Produkt updateProdukt(Produkt produkt);
//	
//	@Path("/produkte/{id:[0-9]+}")
//	@DELETE
//	@Produces
//	public BaseClientResponse<String> deleteProdukt(@PathParam("id") long produktId);
	
	
	
}
