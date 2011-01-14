package en.webshop.test;



import static en.webshop.util.Constants.BASE_URL;
import static en.webshop.util.Constants.PROFILE_URL;
import static en.webshop.util.Constants.ARTICLE_URL;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.api.Run;
import org.jboss.arquillian.api.RunModeType;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.BaseClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import en.webshop.gen.kundenverwaltung.Kunde;
import en.webshop.gen.produktverwaltung.Hersteller;
import en.webshop.gen.produktverwaltung.Produkt;
import en.webshop.gen.produktverwaltung.ProduktList;
import en.webshop.gen.produktverwaltung.Produktbewertung;
import en.webshop.gen.produktverwaltung.Produktkategorie;
import en.webshop.proxy.BestellverwaltungProxy;
import en.webshop.proxy.ArticleManagementProxy;
import en.webshop.test.util.ArchiveUtil;
import en.webshop.test.util.DbReload;
import en.webshop.util.RegisterResteasy;


@RunWith(Arquillian.class)
@Run(RunModeType.AS_CLIENT)
public class ArticleManagementTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleManagementTest.class);
	
//	private static final Long PRODUKTBEWERTUNG_ID_VORHANDEN = Long.valueOf(2);
//	private static final Long PRODUKTBEWERTUNG_ID_NICHT_VORHANDEN = Long.valueOf(9000);
//	private static final Long PRODUKTBEWERTUNG_ID_DELETE = Long.valueOf(6);
	private static final String ARTICLE_ARTICLENO_AVAILABLE = "HKS1";
//	private static final Long PRODUKT_ID_NICHT_VORHANDEN = Long.valueOf(1000);
//	private static final Long PRODUKT_ID_UPDATE = Long.valueOf(5);
//	private static final Long PRODUKT_ID_DELETE = Long.valueOf(6);
//	private static final String PRODUKTNAME_VORHANDEN = "Lampe";
//	private static final String PRODUKTNAME_NICHT_VORHANDEN = "Falsch";
//	private static final String NEUE_PRODUKTKATEGORIE = "Elektro";
//	private static final String NEUER_PRODUKTHERSTELLER = "Klein GmbH";
//	private static final String NEUE_PRODUKTFARBE = "violett";
//	private static final Double NEUE_PRODUKTBREITE = 60.0;
//	private static final Double NEUE_PRODUKTLAENGE = 60.0;
//	private static final Double NEUES_PRODUKTGEWICHT = 50.0;
//	private static final Integer NEUER_PRODUKTBESTAND = 25;
//	private static final Double NEUER_PRODUKTPREIS = 65.0;
//	private static final String NEUE_PRODUKTBESCHREIBUNG = "neueBeschreibung";
//	private static final Long   PRODUKT_ID_DELETE_MIT_BESTELLUNGEN = Long.valueOf(2);
//	private static final String NEUER_PRODUKTNAME = "Produktnameneu";
//	private static final String NEUER_PRODUKTNAME_FORM = "Produktnameform";
//	
	private static final String USERNAME = "max@hs-karlsruhe.de";
	private static final String PASSWORD = "pass";
	
	private static final String USERNAME_ADMIN = "admin@hs-karlsruhe.de";
	private static final String PASSWORD_ADMIN = "pass";
	private static final String PASSWORD_WRONG = "wrong";

	private static ArticleManagementProxy amProxy;
	private static ArticleManagementProxy amProxyUsername;
	private static ArticleManagementProxy amProxyAdmin;
	private static ArticleManagementProxy amProxyWrongPassword;
	

	/**
	 */
	@Deployment
	public static EnterpriseArchive createTestArchive() {
		return ArchiveUtil.getTestArchive();
	}
	
	/**
	 */
	@BeforeClass
	public static void setup() {
		RegisterResteasy.register();

		HttpClient client = new HttpClient();
		amProxy = ProxyFactory.create(ArticleManagementProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));

		client = new HttpClient();
		
		// Mit Apache Commons HttpClient 3.1
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(USERNAME_ADMIN, PASSWORD_ADMIN);
		client.getState().setCredentials(AuthScope.ANY, credentials);
		amProxyAdmin = ProxyFactory.create(ArticleManagementProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
		
		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
		client = new HttpClient();
		client.getState().setCredentials(AuthScope.ANY, credentials);
		amProxyUsername = ProxyFactory.create(ArticleManagementProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));

		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD_WRONG);
		client = new HttpClient();
		client.getState().setCredentials(AuthScope.ANY, credentials);
		amProxyWrongPassword = ProxyFactory.create(ArticleManagementProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
		
		
		try {
			DbReload.reload();
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Fehler in DbReload");
		}
	}
	
	
	@Test 
	public void test() {
		LOGGER.debug("BEGINN findProduktById");

		// GET-Request
		final String testString = amProxyAdmin.test();

		// Ergebnis ueberpruefen
		assertThat("Hello World".equals(testString), is(true));

		LOGGER.debug("ENDE findProduktById");
	}
	
//	@Test 
//	public void findProduktById() {
//		LOGGER.debug("BEGINN findProduktById");
//		
//		final String articleNo = ARTICLE_ARTICLENO_AVAILABLE;
//		
//		// GET-Request
//		final Produkt produkt = amProxyAdmin.findProdukt(articleNo);
//
//		// Ergebnis ueberpruefen
//		assertThat(produkt.getId(), is(articleNo.longValue()));
//
//		LOGGER.debug("ENDE findProduktById");
//	}
	

	
//	@Test
//	public void findProduktbewertungByBewertungsId() throws IOException, JAXBException {
//		LOGGER.debug("BEGINN findProduktbewertungByBewertungsId");
//		
//		final Long bewertungsId = PRODUKTBEWERTUNG_ID_VORHANDEN;
//		
//		
//		// GET-Request
//
//		final Produktbewertung produktbewertung = amProxyAdmin.findProduktbewertung(bewertungsId);
//		assertThat(produktbewertung.getBewertungsId(), is(bewertungsId.longValue()));
//		
//		// Ueberpruefung des referenzierten Produkts: GET-Request aufbauen
//		final String produktUrlStr = produktbewertung.getProdukt();
//		int startPos = produktUrlStr.lastIndexOf("/") + 1;
//		String idStr = produktUrlStr.substring(startPos);
//		final Long produktId = Long.valueOf(idStr);
//		final Produkt produkt = amProxyAdmin.findProdukt(produktId);
//
//
//		//Ergebnis ueberpruefen
//		
//		List<String> produktbewertungen=produkt.getProduktbewertungen().getProduktbewertung();
//		boolean found = false;
//		for (String urlStr: produktbewertungen) {
//			startPos = urlStr.lastIndexOf("/") + 1;
//			idStr = urlStr.substring(startPos);
//			final Long id = Long.valueOf(idStr);
//			if (id.longValue() == bewertungsId.longValue()) {
//				found = true;
//				break;
//			}
//		}
//		assertTrue(found);
//
//		LOGGER.debug("ENDE findProduktbewertungByBewertungsId");
//	}
//	
//	@Test
//	public void findProduktbewertungByBewertungsIdNichtVorhanden() {
//		LOGGER.debug("BEGINN findProduktbewertungByBewertungsIdNichtVorhanden");
//		
//		final Long bewertungsId = PRODUKTBEWERTUNG_ID_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			amProxyAdmin.findProduktbewertung(bewertungsId);
//			fail();
//		}
//		catch (ClientResponseFailure e) {
//			ClientResponse<?> response = null;
//			
//			try {
//				response = e.getResponse();
//				// Ergebnis ueberpruefen
//				final Status status = response.getResponseStatus();
//				assertThat(status, is(NOT_FOUND));
//
//				final byte[] fehlermeldungBytes = (byte[]) response.getEntity();
//				final String fehlermeldung = new String(fehlermeldungBytes);
//				assertThat(fehlermeldung.equalsIgnoreCase("Keine Produktbewertung gefunden mit der ID " + bewertungsId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		}
//		
//		LOGGER.debug("ENDE findProduktbewertungByBewertungsIdNichtVorhanden");
//	}
//	
//	@Test
//	public void createProduktbewertung() {
//		LOGGER.debug("BEGINN createProdsdfsdfsdfdsuktbewertung");
//		
//		/*final Date erzeugt = new GregorianCalendar(2007, 9, 30).getTime();
//		Long produktId= Long.valueOf(4);*/
//		final Produktbewertung neueProduktbewertung = new Produktbewertung();
//
//		neueProduktbewertung.setBewertungskommentar("hallo");
//		neueProduktbewertung.setBewertungsskala(1);
//		neueProduktbewertung.setErzeugt("2000-01-31");
//		neueProduktbewertung.setProdukt(ARTICLE_URL + "/" +4);
//
//		
//		final BaseClientResponse<String> response = amProxyAdmin.createProduktbewertung(neueProduktbewertung);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI des neuen Kunden extrahieren und ueberpruefen
//		final String uriNeueProduktbewertung = response.getLocation().getHref();
//		
//		response.releaseConnection();
//		
//		final int startPos = uriNeueProduktbewertung.lastIndexOf("/");
//		final String idStr = uriNeueProduktbewertung.substring(startPos+1);
//		final Long id = Long.valueOf(idStr);
//		assertTrue(id.longValue() > 0);
//		
//		LOGGER.debug("ENDE createProduktbewertung");
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void deleteProduktbewertung() {
//		LOGGER.debug("BEGINN deleteProduktbewertung");
//		final Long bewertungsId = PRODUKTBEWERTUNG_ID_DELETE;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = amProxyAdmin.deleteProduktbewertung(bewertungsId);
//		Status status = response.getResponseStatus();
//		assertThat(status, is(NO_CONTENT));
//
//		// GET-Request, um das Ergebnis zu ueberpruefen
//		try {
//			amProxyAdmin.findProduktbewertung(bewertungsId);
//			fail();
//		}
//		catch (ClientResponseFailure e) {
//			final ClientResponse<String> clientResponse = e.getResponse();
//			status = clientResponse.getResponseStatus();
//			clientResponse.releaseConnection();
//			
//			assertThat(status, is(NOT_FOUND));
//		}
//		finally {
//			response.releaseConnection();
//		}
//
//		LOGGER.debug("ENDE deleteProduktbewertung");
//	}
//
//	
//	
//	
//	
//	
//
//	/**
//	 */
//	@Test
//	public void findProduktByIdNichtVorhanden() {
//		LOGGER.debug("BEGINN findProduktByIdNichtVorhanden");
//		
//		final Long produktId = PRODUKT_ID_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			amProxyAdmin.findProdukt(produktId);
//		}
//		catch (ClientResponseFailure e) {
//			ClientResponse<?> response = null;
//			
//			try {
//				response = e.getResponse();
//				// Ergebnis ueberpruefen
//				final Status status = response.getResponseStatus();
//				assertThat(status, is(NOT_FOUND));
//
//				final byte[] fehlermeldungBytes = (byte[]) response.getEntity();
//				final String fehlermeldung = new String(fehlermeldungBytes);
//				assertThat(fehlermeldung.equalsIgnoreCase("Kein Produkt gefunden mit der ID " + produktId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		
//			LOGGER.debug("ENDE findProduktByIdNichtVorhanden");
//			return;
//		}
//		
//		fail();
//	}
//
//	/**
//	 */
//	@Test
//	public void findProduktByProduktnameVorhanden() {
//		LOGGER.debug("BEGINN findProduktByNachnameVorhanden");
//		
//		final String produktname = PRODUKTNAME_VORHANDEN;
//
//		// GET-Request
//		final ProduktList produktList = amProxyAdmin.findProdukte(produktname);
//
//		// Ergebnis ueberpruefen
//		final List<Produkt> produktListIntern = produktList.getProdukt();
//		for (Produkt produkt: produktListIntern) {
//			assertThat(produkt.getProduktname().contains(produktname), is(true));
//		}
//
//		LOGGER.debug("ENDE findProduktByNameVorhanden");
//	}
//
//	
//	/**
//	 */
//	@Test
//	public void findProduktByProduktnameNichtVorhanden() {
//		LOGGER.debug("BEGINN findProduktByProduktnameNichtVorhanden");
//		
//		final String produktname = PRODUKTNAME_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			amProxyAdmin.findProdukte(produktname);
//		}
//		catch (ClientResponseFailure e) {
//			ClientResponse<?> response = null;
//			
//			try {
//				response = e.getResponse();
//				// Ergebnis ueberpruefen
//				final Status status = response.getResponseStatus();
//				assertThat(status, is(NOT_FOUND));
//
//				final byte[] fehlermeldungBytes = (byte[]) response.getEntity();
//				final String fehlermeldung = new String(fehlermeldungBytes);
//				assertThat(fehlermeldung.equalsIgnoreCase("Kein Produkt gefunden mit dem Namen: " + produktname), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		
//				
//			LOGGER.debug("ENDE findProduktByProduktnameNichtVorhanden");
//			return;
//		}
//		
//		fail();
//	}
//
//	/**
//	 */
//	@Test
//	public void createProdukt() {
//		LOGGER.debug("BEGINN createProdukt");
//		
//		final Produkt neuesProdukt = new Produkt();
//		neuesProdukt.setProduktname(NEUER_PRODUKTNAME);
//		neuesProdukt.setProduktkategorie(NEUE_PRODUKTKATEGORIE);
//		neuesProdukt.setFarbe(NEUE_PRODUKTFARBE);
//		neuesProdukt.setBreite(NEUE_PRODUKTBREITE);
//		neuesProdukt.setLaenge(NEUE_PRODUKTLAENGE);
//		neuesProdukt.setGewicht(NEUES_PRODUKTGEWICHT);
//		neuesProdukt.setBestand(NEUER_PRODUKTBESTAND);
//		neuesProdukt.setPreis(NEUER_PRODUKTPREIS);
//		neuesProdukt.setProduktbeschreibung(NEUE_PRODUKTBESCHREIBUNG);
//		
//		final Hersteller hersteller = new Hersteller();
//		hersteller.setHerstellername(NEUER_PRODUKTHERSTELLER);
//		
//		final Produktkategorie produktkategorie = new Produktkategorie();
//		produktkategorie.setKategoriename(NEUE_PRODUKTKATEGORIE);
//		
//		final Long herstellerId = Long.valueOf(5);
//		final Long kategorieId = Long.valueOf(4);
//		neuesProdukt.setHersteller(ARTICLE_URL+ "/" + herstellerId);
//		neuesProdukt.setProduktkategorie(ARTICLE_URL+ "/" + kategorieId);
//
//		final BaseClientResponse<String> response = amProxyAdmin.createProdukt(neuesProdukt);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI des neuen Kunden extrahieren und ueberpruefen
//		final String uriNeuesProdukt = response.getLocation().getHref();
//		
//		response.releaseConnection();
//		
//		final int startPos = uriNeuesProdukt.lastIndexOf("/");
//		final String idStr = uriNeuesProdukt.substring(startPos+1);
//		final Long id = Long.valueOf(idStr);
//		assertTrue(id.longValue() > 0);
//		
//		LOGGER.debug("ENDE createProdukt");
//	}
//	
//	/**
//	 */
//	@Test
//	public void createProduktForm() {
//		LOGGER.debug("BEGINN createProduktForm");
//		
//		final String produktname = NEUER_PRODUKTNAME_FORM;
//		final String produktkategoriename = NEUE_PRODUKTKATEGORIE;
//		final String herstellername = NEUER_PRODUKTHERSTELLER;
//		final String farbe = NEUE_PRODUKTFARBE ;
//		final Double breite = NEUE_PRODUKTBREITE;
//		final Double laenge = NEUE_PRODUKTLAENGE;
//        final Double gewicht = NEUES_PRODUKTGEWICHT;
//        final Integer bestand = NEUER_PRODUKTBESTAND;
//        final Double preis = NEUER_PRODUKTPREIS;
//        final String produktbeschreibung = NEUE_PRODUKTBESCHREIBUNG;     
//	
//
//		final BaseClientResponse<String> response = amProxyAdmin.createProdukt(produktname, produktkategoriename, herstellername, farbe, breite, laenge, gewicht, bestand,preis,produktbeschreibung);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI des neuen Kunden extrahieren und ueberpruefen
//		final String uriNeuesProdukt = response.getLocation().getHref();
//		
//		response.releaseConnection();
//		
//		final int startPos = uriNeuesProdukt.lastIndexOf("/");
//		final String idStr = uriNeuesProdukt.substring(startPos+1);
//		final Long id = Long.valueOf(idStr);
//		assertTrue(id.longValue() > 0);
//		
//		LOGGER.debug("ENDE createProduktForm");
//	}
//
//	/**
//	 */
//	@Test
//	public void updateProdukt() {
//		LOGGER.debug("BEGINN updateProdukt");
//		final Long produktId = PRODUKT_ID_UPDATE;
//		
//		// GET-Request
//		Produkt produkt = amProxyAdmin.findProdukt(produktId);
//		assertThat(produkt.getId(), is(produktId.longValue()));
//		
//		// Update des Produktnamens
//		final String neuerProduktname = produkt.getProduktname() + "TEST";
//		produkt.setProduktname(neuerProduktname);
//		//produkt.setHersteller(NEUER_PRODUKTHERSTELLER);
//		//produkt.getHersteller().setHerstellername("Testweg");
//		
//		// PUT-Request durchfuehren
//		produkt = amProxyAdmin.updateProdukt(produkt);
//
//		// Ergebnis ueberpruefen
//		assertThat(produkt.getId(), is(produktId.longValue()));
//		assertThat(produkt.getProduktname(), is(neuerProduktname));
//		
//		LOGGER.debug("ENDE updateProdukt");
//	}
//	
//
//	/**
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void deleteProdukt() {
//		LOGGER.debug("BEGINN deleteProdukt");
//		final Long produktId = PRODUKT_ID_DELETE;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = amProxyAdmin.deleteProdukt(produktId);
//		Status status = response.getResponseStatus();
//		assertThat(status, is(NO_CONTENT));
//
//		// GET-Request, um das Ergebnis zu ueberpruefen
//		try {
//			amProxyAdmin.findProdukt(produktId);
//			fail();
//		}
//		catch (ClientResponseFailure e) {
//			final ClientResponse<String> clientResponse = e.getResponse();
//			status = clientResponse.getResponseStatus();
//			clientResponse.releaseConnection();
//			
//			assertThat(status, is(NOT_FOUND));
//
//	
//		}
//		finally {
//			response.releaseConnection();
//		}
//		
//		LOGGER.debug("ENDE deleteProdukt");
//	}
//
//	
//	/**
//	 */
//	@Test
//	public void deleteProduktMitBestellung() {
//		LOGGER.debug("BEGINN deleteProduktMitBestellung");
//		final Long produktId = PRODUKT_ID_DELETE_MIT_BESTELLUNGEN;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = amProxyAdmin.deleteProdukt(produktId);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CONFLICT));
//
//		// Fehlermeldung extrahieren und ueberpruefen
//		final String fehlermeldung = response.getEntity();
//		
//		response.releaseConnection();
//		
//		assertThat(fehlermeldung.startsWith("Produkt mit ID=" + produktId + " kann nicht geloescht werden:"), is(false));
//		assertThat(fehlermeldung.endsWith("Bestellung(en)"), is(false));
//		
//		LOGGER.debug("ENDE deleteProduktMitBestellung");
//	}
//
//	/**
//	 */
	
	
	
	

}
