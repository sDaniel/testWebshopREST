package en.webshop.proxy;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

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
import java.util.Date;

import org.jboss.resteasy.client.core.BaseClientResponse;

import en.webshop.gen.bestellverwaltung.Bestellung;
import en.webshop.gen.kundenverwaltung.Kunde;
import en.webshop.gen.kundenverwaltung.KundeList;

//Teeeeest
@Path("/kundenverwaltung")
@Produces({APPLICATION_XML, TEXT_XML, APPLICATION_JSON})
@Consumes
public interface KundenverwaltungProxy {
	@GET
	@Path("/kunden/{id:[0-9]+}")
	public Kunde findKunde(@PathParam("id") Long id);

	@GET
	@Path("/kunden")
	public KundeList findKunden(@QueryParam("nachname") @DefaultValue("") String nachname);

	@GET
	@Path("/kunden/{id:[0-9]+}/bestellungen")
	public Bestellung findBestellungByKundeId(@PathParam("id") Long id);

	@Path("/kunden")
	@POST
	@Consumes({APPLICATION_XML, TEXT_XML})
	@Produces
	public BaseClientResponse<String> createKunde(Kunde kunde);
	
	@Path("/kunden/form")
	@POST
	@Consumes(APPLICATION_FORM_URLENCODED)
	@Produces
	public BaseClientResponse<String> createKunde(@FormParam("nachname") String nachname,
			                                            @FormParam("vorname") String vorname,
			                                            @FormParam("email") String email,
			                                            @FormParam("geburtsdatum") String geburtsdatum,
			                                            @FormParam("plz") String plz,
			                                            @FormParam("ort") String ort,
			                                            @FormParam("strasse") String strasse,
			                                            @FormParam("hausnummer") int hausnr);

	@Path("/kunden")
	@PUT
	@Consumes({APPLICATION_XML, TEXT_XML})
	public Kunde updateKunde(Kunde kunde);
	
	@Path("/kunden/{id:[0-9]+}")
	@DELETE
	@Produces
	public BaseClientResponse<String> deleteKunde(@PathParam("id") long kundeId);
}
