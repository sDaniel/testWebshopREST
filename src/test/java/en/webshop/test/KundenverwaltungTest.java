//package en.webshop.test;
//
//
//import static en.webshop.util.Constants.BASE_URL;
//import static javax.ws.rs.core.Response.Status.CONFLICT;
//import static javax.ws.rs.core.Response.Status.CREATED;
//import static javax.ws.rs.core.Response.Status.FORBIDDEN;
//import static javax.ws.rs.core.Response.Status.NOT_FOUND;
//import static javax.ws.rs.core.Response.Status.NO_CONTENT;
//import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.List;
//
//import javax.ws.rs.core.Response.Status;
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
//import java.util.Date;
//
//import en.webshop.gen.kundenverwaltung.Adresse;
//import en.webshop.gen.kundenverwaltung.Bank;
//import en.webshop.gen.kundenverwaltung.Kunde;
//import en.webshop.gen.kundenverwaltung.KundeList;
//import en.webshop.gen.kundenverwaltung.Kunde;
//import en.webshop.proxy.KundenverwaltungProxy;
//import en.webshop.util.RegisterResteasy;
//import en.webshop.test.util.ArchiveUtil;
//import en.webshop.test.util.DbReload;
//
//
//@RunWith(Arquillian.class)
//@Run(RunModeType.AS_CLIENT)
//public class KundenverwaltungTest {
//	private static final Logger LOGGER = LoggerFactory.getLogger(KundenverwaltungTest.class);
//
//	private static final Long KUNDE_ID_VORHANDEN = Long.valueOf(101);
//	private static final Long KUNDE_ID_NICHT_VORHANDEN = Long.valueOf(1000);
//	private static final Long KUNDE_ID_UPDATE = Long.valueOf(102);
//	private static final Long KUNDE_ID_DELETE = Long.valueOf(103);
//	//private static final Long KUNDE_ID_DELETE_MIT_BESTELLUNGEN = Long.valueOf(1);
//	private static final Long KUNDE_ID_DELETE_FORBIDDEN = Long.valueOf(303);
//	private static final String NACHNAME_VORHANDEN = "Mueller";
//	private static final String NACHNAME_NICHT_VORHANDEN = "Falschername";
//	private static final String NEUER_NACHNAME = "Nachnameneu";
//	private static final String NEUER_NACHNAME_FORM = "Nachnameform";
//	private static final String NEUER_VORNAME = "Vornameform";
//	private static final String NEUE_PLZ = "76155";
//	private static final String NEUER_ORT = "Stuttgart";
//	private static final String NEUE_STRASSE = "Testweg";
//	private static final int NEUE_HAUSNR = 1;
//	
//	private final static Date NEUES_GEBURTSDATUM = new GregorianCalendar(
//			1988, 2, 30).getTime();
//	
//	private static final String USERNAME = "ms2@mail.de";
//	private static final String PASSWORD = "pwMueller";
//	private static final String USERNAME_ADMIN = "ma1@mail.de";
//	private static final String PASSWORD_ADMIN = "pwMaier";
//	private static final String PASSWORD_FALSCH = "falsch";
//	
//	private static KundenverwaltungProxy kvProxy;
//	private static KundenverwaltungProxy kvProxyAdmin;
//	private static KundenverwaltungProxy kvProxyUsername;
//	private static KundenverwaltungProxy kvProxyFalschesPassword;
//	
//	/**
//	 */
//	@Deployment
//	public static EnterpriseArchive createTestArchive() {
//		return ArchiveUtil.getTestArchive();
//	}
//	
//	@BeforeClass
//	public static void setup() {
//		RegisterResteasy.register();
//
//		HttpClient client = new HttpClient();
//		kvProxy = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
//		
//		// Mit Apache Commons HttpClient 3.1
//		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(USERNAME_ADMIN, PASSWORD_ADMIN);
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		kvProxyAdmin = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
//
////		// Mit Apache HttpComponents Client 4.x
////		DefaultHttpClient client = new DefaultHttpClient();
////		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(USERNAME_ADMIN, PASSWORD_ADMIN);
////		client = new DefaultHttpClient();
////		client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials); 
////		kvProxyAdmin = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL, new ApacheHttpClient4Executor(client));
//
//		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
//		client = new HttpClient();
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		kvProxyUsername = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
//
//		credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD_FALSCH);
//		client = new HttpClient();
//		client.getState().setCredentials(AuthScope.ANY, credentials);
//		kvProxyFalschesPassword = ProxyFactory.create(KundenverwaltungProxy.class, BASE_URL, new ApacheHttpClientExecutor(client));
//		
//		try {
//			DbReload.reload();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			fail("Fehler in DbReload");
//		}
//	}
//
//	/**
//	 */
//	@Test
//	public void findKundeById() {
//		LOGGER.debug("BEGINN findKundeById");
//		
//		final Long kundeId = KUNDE_ID_VORHANDEN;
//		
//		// GET-Request
//		final Kunde kunde = kvProxy.findKunde(kundeId);
//
//		// Ergebnis ueberpruefen
//		assertThat(kunde.getKundeId(), is(kundeId.longValue()));
//		
//		LOGGER.debug("ENDE findKundeById");
//	}
//
//	/**
//	 */
//	@Test
//	public void findKundeByIdNichtVorhanden() {
//		LOGGER.debug("BEGINN findKundeByIdNichtVorhanden");
//		
//		final Long kundeId = KUNDE_ID_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			kvProxy.findKunde(kundeId);
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
//				assertThat(fehlermeldung.equalsIgnoreCase("Kein Kunde gefunden mit der ID " + kundeId), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		
//			LOGGER.debug("ENDE findKundeByIdNichtVorhanden");
//			return;
//		}
//		
//		fail();
//	}
//
//	/**
//	 */
//	@Test
//	public void findKundenByNachnameVorhanden() {
//		LOGGER.debug("BEGINN findKundenByNachnameVorhanden");
//		
//		final String nachname = NACHNAME_VORHANDEN;
//
//		// GET-Request
//		final KundeList kundeList = kvProxy.findKunden(nachname);
//
//		// Ergebnis ueberpruefen
//		final List<Kunde> kundeListIntern = kundeList.getKunde();
//		for (Kunde kunde: kundeListIntern) {
//			assertThat(kunde.getNachname(), is(nachname));
//		}
//
//		LOGGER.debug("ENDE findKundenByNachnameVorhanden");
//	}
//
//	
//	/**
//	 */
//	@Test
//	public void findKundenByNachnameNichtVorhanden() {
//		LOGGER.debug("BEGINN findKundenByNachnameNichtVorhanden");
//		
//		final String nachname = NACHNAME_NICHT_VORHANDEN;
//		
//		// GET-Request
//		try {
//			kvProxy.findKunden(nachname);
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
//				assertThat(fehlermeldung.equalsIgnoreCase("Kein Kunde gefunden mit Nachname " + nachname), is(true));
//			}
//			finally {
//				if (response != null)
//					response.releaseConnection();
//			}
//		
//				
//			LOGGER.debug("ENDE findKundenByNachnameNichtVorhanden");
//			return;
//		}
//		
//		fail();
//	}
//
//	/**
//	 */
//	@Test
//	public void createKunde() {
//		LOGGER.debug("BEGINN createKunde");
//		
//		final Kunde neuerKunde = new Kunde();
//		neuerKunde.setNachname(NEUER_NACHNAME);
//		neuerKunde.setVorname(NEUER_VORNAME);
//		neuerKunde.setEmail(NEUER_NACHNAME + "@test.de");
//
//		neuerKunde.setGeburtsdatum("2010-02-01");
//		final Adresse adresse = new Adresse();
//		adresse.setPlz(NEUE_PLZ);
//		adresse.setOrt(NEUER_ORT);
//		adresse.setStrasse(NEUE_STRASSE);
//		adresse.setHausnummer (NEUE_HAUSNR);
//		neuerKunde.setAdresse(adresse);
//		neuerKunde.setPasswort("hallo");
//		Bank bank = new Bank();
//		bank.setBlz(234234);
//		bank.setKontonummer(234234);
//		bank.setKreditinstitut("Bank");
//		neuerKunde.setBank(bank);
//
//		final BaseClientResponse<String> response = kvProxyAdmin.createKunde(neuerKunde);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI des neuen Kunden extrahieren und ueberpruefen
//		final String uriNeuerKunde = response.getLocation().getHref();
//		
//		response.releaseConnection();
//		
//		final int startPos = uriNeuerKunde.lastIndexOf("/");
//		final String idStr = uriNeuerKunde.substring(startPos+1);
//		final Long id = Long.valueOf(idStr);
//		assertTrue(id.longValue() > 0);
//		
//		LOGGER.debug("ENDE createKunde");
//	}
//	
//	/**
//	 */
//	@Test
//	public void createKundeForm() {
//		LOGGER.debug("BEGINN createKundeForm");
//		
//		final String nachname = NEUER_NACHNAME_FORM;
//		final String vorname = NEUER_VORNAME;
//		final String email = NEUER_NACHNAME_FORM + "@test.de";
//		final String geburtsdatum = "2010-10-02";
//		final String plz = NEUE_PLZ;
//		final String ort = NEUER_ORT;
//		final String strasse = NEUE_STRASSE;
//		final int hausnr = NEUE_HAUSNR;
//
//		final BaseClientResponse<String> response = kvProxyAdmin.createKunde(nachname, vorname, email, geburtsdatum, plz, ort, strasse, hausnr);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CREATED));
//
//		// URI des neuen Kunden extrahieren und ueberpruefen
//		final String uriNeuerKunde = response.getLocation().getHref();
//		
//		response.releaseConnection();
//		
//		final int startPos = uriNeuerKunde.lastIndexOf("/");
//		final String idStr = uriNeuerKunde.substring(startPos+1);
//		final Long id = Long.valueOf(idStr);
//		assertTrue(id.longValue() > 0);
//		
//		LOGGER.debug("ENDE createKundeForm");
//	}
//
//	/**
//	 */
//	@Test
//	public void falschesPasswort() {
//		LOGGER.debug("BEGINN falschesPasswort");
//
//		// POST-Request
//		final BaseClientResponse<String> response = kvProxyFalschesPassword.createKunde( null, null, null, null, null, null, null, 0);
//		final Status status = response.getResponseStatus();
//		response.releaseConnection();
//		
//		assertThat(status, is(UNAUTHORIZED));
//		LOGGER.debug("ENDE falschesPasswort");
//
//	}
//
//	/**
//	 */
//	@Test
//	public void updateKunde() {
//		LOGGER.debug("BEGINN updateKunde");
//		final Long kundeId = KUNDE_ID_UPDATE;
//		
//		// GET-Request
//		Kunde kunde = kvProxy.findKunde(kundeId);
//		assertThat(kunde.getKundeId(), is(kundeId.longValue()));
//		
//		// Update des Nachnamens
//		final String neuerNachname = kunde.getNachname() + "x";
//		kunde.setNachname(neuerNachname);
//		kunde.getAdresse().setStrasse("Testweg");
//		
//		// PUT-Request durchfuehren
//		kunde = kvProxyAdmin.updateKunde(kunde);
//
//		// Ergebnis ueberpruefen
//		assertThat(kunde.getKundeId(), is(kundeId.longValue()));
//		assertThat(kunde.getNachname(), is(neuerNachname));
//		
//		LOGGER.debug("ENDE updateKunde");
//	}
//	
//
//	/**
//	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void deleteKunde() {
//		LOGGER.debug("BEGINN deleteKunde");
//		final Long kundeId = KUNDE_ID_DELETE;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = kvProxyAdmin.deleteKunde(kundeId);
//		Status status = response.getResponseStatus();
//		assertThat(status, is(NO_CONTENT));
//
//		// GET-Request, um das Ergebnis zu ueberpruefen
//		try {
//			kvProxyAdmin.findKunde(kundeId);
//		}
//		catch (ClientResponseFailure e) {
//			final ClientResponse<String> clientResponse = e.getResponse();
//			status = clientResponse.getResponseStatus();
//			clientResponse.releaseConnection();
//			
//			assertThat(status, is(NOT_FOUND));
//
//			LOGGER.debug("ENDE deleteKunde");
//			return;
//		}
//		finally {
//			response.releaseConnection();
//		}
//		
//		fail();
//	}
//
//	
//	/**
//	 * @Test
//	public void deleteKundeMitBestellung() {
//		LOGGER.debug("BEGINN deleteKundeMitBestellung");
//		final Long kundeId = KUNDE_ID_DELETE_MIT_BESTELLUNGEN;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = kvProxyAdmin.deleteKunde(kundeId);
//		final Status status = response.getResponseStatus();
//		assertThat(status, is(CONFLICT));
//
//		// Fehlermeldung extrahieren und ueberpruefen
//		final String fehlermeldung = response.getEntity();
//		
//		response.releaseConnection();
//		
//		assertThat(fehlermeldung.startsWith("Kunde mit ID=" + kundeId + " kann nicht geloescht werden:"), is(true));
//		assertThat(fehlermeldung.endsWith("Bestellung(en)"), is(true));
//		
//		LOGGER.debug("ENDE deleteKundeMitBestellung");
//	}
//
//	 */
//	
//	/**
//	 */
//	@Test
//	public void deleteKundeForbidden() {
//		LOGGER.debug("BEGINN deleteKundeForbidden");
//		final Long kundeId = KUNDE_ID_DELETE_FORBIDDEN;
//		
//		// DELETE-Request
//		final BaseClientResponse<String> response = kvProxyUsername.deleteKunde(kundeId);
//		final Status status = response.getResponseStatus();
//		response.releaseConnection();
//		assertThat(status, is(FORBIDDEN));
//		
//		LOGGER.debug("ENDE deleteKundeForbidden");
//	}
//}
