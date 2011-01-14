package en.webshop.proxy;


import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.client.core.BaseClientResponse;

import en.webshop.gen.bestellverwaltung.Bestellung;
import en.webshop.gen.bestellverwaltung.BestellungList;
import en.webshop.gen.bestellverwaltung.Lieferung;
import en.webshop.gen.bestellverwaltung.Zahlungsart;
import en.webshop.gen.kundenverwaltung.Kunde;


@Path("/bestellverwaltung")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON})
@Consumes
public interface BestellverwaltungProxy {
	@GET
	@Path("/bestellungen/{id:[0-9]+}")
	public Bestellung findBestellung(@PathParam("id") Long id);
	
	@GET
	@Path("/test")
	String test();
	
	@GET
	@Path("/kunden/{kundeId:[0-9]+}/bestellungen")
	BestellungList findBestellungenByKundeCriteria(@PathParam("kundeId") Long id);
	
	@POST
	@Consumes({APPLICATION_XML, TEXT_XML})
	@Path("/bestellungen")
	public BaseClientResponse<String> createBestellung(Bestellung bestellung);
	
	@GET
	@Path("/lieferungen/{id:[0-9]+}")
	public Lieferung findLieferung(@PathParam("id") Long lieferungsId);
	
	@GET
	@Path("/zahlungsart/{id:[0-9]+}")
	public Zahlungsart findZahlungsart(@PathParam("id") Long zahlungsartId);
	
}

