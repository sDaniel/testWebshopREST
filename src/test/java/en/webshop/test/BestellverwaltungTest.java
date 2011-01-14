//package en.webshop.test;
//
//import static en.webshop.util.Constants.BASE_URL;
//import static en.webshop.util.Constants.KUNDEN_URL;
//import static en.webshop.util.Constants.ZAHLUNGSART_URL;
//import static javax.ws.rs.core.Response.Status.CREATED;
//import static javax.ws.rs.core.Response.Status.NOT_FOUND;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import static en.webshop.util.Constants.PRODUKT_URL;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.ws.rs.core.Response.Status;
//import javax.xml.bind.JAXBException;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.UsernamePasswordCredentials;
//import org.apache.commons.httpclient.auth.AuthScope;
//import org.jboss.arquillian.api.Deployment;
//import org.jboss.arquillian.api.Run;
//import org.jboss.arquillian.api.RunModeType;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.resteasy.client.ClientResponse;
//import org.jboss.resteasy.client.ClientResponseFailure;
//import org.jboss.resteasy.client.ProxyFactory;
//import org.jboss.resteasy.client.core.BaseClientResponse;
//import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
//import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import en.webshop.gen.bestellverwaltung.Bestellposition;
//import en.webshop.gen.bestellverwaltung.Bestellung;
//import en.webshop.gen.bestellverwaltung.Bestellung.Bestellpositionen;
//import en.webshop.gen.bestellverwaltung.BestellungList;
//import en.webshop.gen.bestellverwaltung.Lieferung;
//import en.webshop.gen.bestellverwaltung.Zahlungsart;
//import en.webshop.gen.kundenverwaltung.Kunde;
//import en.webshop.proxy.BestellverwaltungProxy;
//import en.webshop.proxy.KundenverwaltungProxy;
//import en.webshop.util.RegisterResteasy;
//import en.webshop.test.util.ArchiveUtil;
//import en.webshop.test.util.DbReload;
//import static javax.ws.rs.core.Response.Status.NOT_FOUND;
//@RunWith(Arquillian.class)
//@Run(RunModeType.AS_CLIENT)
//public class BestellverwaltungTest {
//
//
//	private static final Logger LOGGER = LoggerFactory
//			.getLogger(BestellverwaltungTest.class);
//
//	private static final Long BESTELLUNG_ID_VORHANDEN = Long.valueOf(1);
//	private static final Long BESTELLUNG_ID_NICHTVORHANDEN= Long.valueOf(9999999);
//
//	
//	private static final Long LIEFERUNG_ID_VORHANDEN = Long.valueOf(4);
//
//
//	private static final String USERNAME = "sr5@mail.de";
//	private static final String PASSWORD = "pwSchumacher";
//
//	private static final Long LIEFERUNG_ID_NICHT_VORHANDEN = Long.valueOf(201);
//	private static final Long ZAHLUNGSART_ID_VORHANDEN = Long.valueOf(2);
//	private static final Long ZAHLUNGSART_ID_NICHT_VORHANDEN = Long.valueOf(91);
//	
//	
//
//	private static final String USERNAME_ADMIN = "ma1@mail.de";
//	private static final String PASSWORD_ADMIN = "pwMaier";
//	private static final String PASSWORD_FALSCH = "falsch";
//
//	private static BestellverwaltungProxy bvProxy;
//	private static KundenverwaltungProxy kvProxy;
//	private static BestellverwaltungProxy bvProxyUsername;
//	private static BestellverwaltungProxy bvProxyAdmin;
//	private static BestellverwaltungProxy bvProxyFalschesPassword;
//
//	/**
//	 */
//	@Deployment
//	public static EnterpriseArchive createTestArchive() {
//		return ArchiveUtil.getTestArchive();
//	}
//
//	/**
//	 */
//	@BeforeClass
//	public static void setup() {
//		RegisterResteasy.register();
//
//		HttpClient client = new HttpClient();
//		bvProxy = ProxyFactory.create(BestellverwaltungProxy.class, BASE_URL,
//				new ApacheHttpClientExecutor(client));
//
//		client = new HttpClient();
//
//
//		// Mit Apache Commons HttpClient 3.1
//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
//				USERNAME_ADMIN, PASSWORD_ADMIN);
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		bvProxyAdmin = ProxyFactory.create(BestellverwaltungProxy.class,
//				BASE_URL, new ApacheHttpClientExecutor(client));
//
//		client = new HttpClient();
//		kvProxy = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL,
//				new ApacheHttpClientExecutor(client));
//
//		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
//		client = new HttpClient();
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		bvProxyUsername = ProxyFactory.create(BestellverwaltungProxy.class,
//				BASE_URL, new ApacheHttpClientExecutor(client));
//
//		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD_FALSCH);
//		client = new HttpClient();
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		bvProxyFalschesPassword = ProxyFactory.create(
//				BestellverwaltungProxy.class, BASE_URL,
//				new ApacheHttpClientExecutor(client));
//
//		try {
//			DbReload.reload();
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Fehler in DbReload");
//		}
//	}
//
//	/**
//	 */
//	@Test
//	public void findBestellungByKundeId() throws IOException, JAXBException {
//		LOGGER.debug("BEGINN findBestellungByKundeId");
//
//		final Long kundeId = Long.valueOf(101);
//
//		// GET-Request
//		final BestellungList bestellungen = bvProxyAdmin.findBestellungenByKundeCriteria(kundeId);
//		
//		LOGGER.debug("ENDE findBestellungByKundeId");
//	}
//	/**
//	 */
//	@Test
//	public void findBestellungById() throws IOException, JAXBException {
//		LOGGER.debug("BEGINN findBestellungById");
//
//		final Long bestellungId = BESTELLUNG_ID_VORHANDEN;
//
//		// GET-Request
//		final Bestellung bestellung = bvProxyAdmin.findBestellung(bestellungId);
//		assertThat(bestellung.getBestellId(), is(bestellungId.longValue()));
//
//		// Ueberpruefung des referenzierten Kunden: GET-Request aufbauen
//		// Id aus der URI raussuchen und ueber die Methode findKunde den
//		// zugeh�rigen Kunden dazu
//		final String kundeUrlStr = bestellung.getKunde();
//		int startPos = kundeUrlStr.lastIndexOf("/") + 1;
//		String idStr = kundeUrlStr.substring(startPos);
//		final Long kundeId = Long.valueOf(idStr);
//		final Kunde kunde = kvProxy.findKunde(kundeId);
//
//		// Ergebnis ueberpruefen
//		// ueberpruefen ob die Bestellungen des Kunden die gleiche id haben wie
//		// die bestellungId
//		final List<String> bestellungen = kunde.getBestellungen()
//				.getBestellung();
//		boolean found = false;
//		for (String urlStr : bestellungen) {
//			startPos = urlStr.lastIndexOf("/") + 1;
//			idStr = urlStr.substring(startPos);
//			final Long id = Long.valueOf(idStr);
//			if (id.longValue() == bestellungId.longValue()) {
//				found = true;
//				break;
//			}
//		}
//		assertTrue(found);
//
//		LOGGER.debug("ENDE findBestellungById");
//	}
//	@Test
//	public void findBestellungByIdNichtVorhanden() throws IOException, JAXBException {
//		LOGGER.debug("BEGINN findBestellungByIdNichtVorhanden");
//
//		final Long bestellungId = BESTELLUNG_ID_NICHTVORHANDEN;
//
//		// GET-Request
//		try {
//			bvProxyAdmin.findBestellung(bestellungId);
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
//				assertThat(fehlermeldung.equalsIgnoreCase("Keine Bestellung gefunden mit der ID " + bestellungId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		}
//		
//
//		LOGGER.debug("ENDE findBestellungByIdNichtVorhanden");
//	}
//
//	/**
//	 */
//	@Test
//	public void createBestellung() throws JAXBException, IOException {
//		LOGGER.debug("BEGINN createBestellsdfung");
//
//		final Long zahlungsartId = Long.valueOf(1);
//		final Long kundeId = Long.valueOf(103);
//		final Long artikelId1 = Long.valueOf(2);
//		final Long artikelId2 = Long.valueOf(3);
//
//		// Neues, client-seitiges Bestellungsobjekt
//		Bestellung bestellung = new Bestellung();
//		bestellung.setKunde(KUNDEN_URL + "/" + kundeId);
//		
//		// Zahlungsart zufuegen
//		bestellung.setZahlungsart(ZAHLUNGSART_URL + "/" + zahlungsartId);
//		bestellung.setZeit("2000-01-31");
//	    LOGGER.debug("hajsd");
//		// Bestellpositionen mit Artikel hinzufuegen
//		final Bestellpositionen bestellpositionen = new Bestellpositionen();
//		bestellung.setBestellpositionen(bestellpositionen);
//
//		final List<Bestellposition> bestellpositionenList = bestellpositionen
//				.getBestellposition();
//		Bestellposition bestellposition = new Bestellposition();
//		bestellposition.setProdukt(PRODUKT_URL + "/" + artikelId1);
//		bestellposition.setMenge((short) 1);
//		bestellpositionenList.add(bestellposition);
//
//		bestellposition = new Bestellposition();
//		bestellposition.setProdukt(PRODUKT_URL + "/" + artikelId2);
//		bestellposition.setMenge((short) 2);
//		bestellpositionenList.add(bestellposition);
//	
//		// POST-Request
//		final BaseClientResponse<String> response = bvProxyUsername.createBestellung(bestellung);
//		
//		// Status ueberpruefen
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI der neuen Bestellung extrahieren
//		final String uriNeueBestellung = response.getLocation().getHref();
//		response.releaseConnection();
//
//		LOGGER.trace(uriNeueBestellung);
//		final int startPos = uriNeueBestellung.lastIndexOf("/");
//		final String idStr = uriNeueBestellung.substring(startPos + 1);
//		LOGGER.trace(idStr);
//		final Long id = Long.valueOf(idStr);
//
//		assertTrue(id.longValue() > 0);
//
//		LOGGER.debug("ENDE createBestellung");
//	}
//
//	/**
//	 */
//
//	@Test
//	public void findLieferungByLieferungsId() {
//		LOGGER.debug("BEGINN findLieferungByLieferungsId");
//
//		final Long lieferungsId = LIEFERUNG_ID_VORHANDEN;
//
//		// GET-Request
//		final Lieferung lieferung = bvProxyAdmin.findLieferung(lieferungsId);
//
//		// Ergebnis ueberpruefen
//		assertThat(lieferung.getLieferungsId(), is(lieferungsId.longValue()));
//
//		LOGGER.debug("ENDE findLieferungByLieferungsId");
//	}
//
//	
//	@Test
//	public void findLieferungByLieferungsIdNichtVorhanden() {
//		LOGGER.debug("BEGINN findLieferungByLieferungsIdNichtVorhanden");
//		
//		final Long lieferungsId = LIEFERUNG_ID_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			bvProxyAdmin.findLieferung(lieferungsId);
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
//				assertThat(fehlermeldung.equalsIgnoreCase("Keine Lieferung gefunden mit der ID " + lieferungsId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		}
//		
//		LOGGER.debug("ENDE findLieferungByLieferungsIdNichtVorhanden");
//	}
//
//	
//
//	
//	@Test
//	public void findZahlungsartByZahlungsartId() {
//		LOGGER.debug("BEGINN findZahlungsartByZahlungsartId");
//		
//		final Long zahlungsartId = ZAHLUNGSART_ID_VORHANDEN;
//		
//		// GET-Request
//		final Zahlungsart zahlungsart = bvProxyAdmin.findZahlungsart(zahlungsartId);
//
//		// Ergebnis ueberpruefen
//		assertThat(zahlungsart.getZahlungsartId(), is(zahlungsartId.longValue()));
//
//		LOGGER.debug("ENDE findZahlungsartByZahlungsartId");
//	}
//	
//	@Test
//	public void findZahlungsartByZahlungsartIdNichtVorhanden() {
//		LOGGER.debug("BEGINN findZahlungsartByZahlungsartIdNichtVorhanden");
//		
//		final Long zahlungsartId =ZAHLUNGSART_ID_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			bvProxyAdmin.findZahlungsart(zahlungsartId);
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
//				assertThat(fehlermeldung.equalsIgnoreCase("Keine Zahlungsart gefunden mit der ID " + zahlungsartId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		}
//		
//		LOGGER.debug("ENDE findZahlungsartByZahlungsartIdNichtVorhanden");
//	}
//
//
//}
