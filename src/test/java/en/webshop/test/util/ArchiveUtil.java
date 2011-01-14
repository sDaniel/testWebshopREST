package en.webshop.test.util;


import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ArchiveUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveUtil.class);
	private static final String PROJEKT_NAME = "webshop";
	
	private static final String EAR_DIR = "../" + PROJEKT_NAME + "/EarContent";
	private static final String EJB_CLASSES_DIR = "../" + PROJEKT_NAME + "EJB/build/classes";
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static final EnterpriseArchive EAR = createTestArchive();
	
	
	/**
	 */
	private static EnterpriseArchive createTestArchive() {
		// EAR-Archiv muss test.ear heissen, damit JNDI-Namen richtig aufgeloest werden
		final EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class, "test.ear");
		
		// In das Archiv ein "exploded" Archiv importieren, d.h. ein Directory
		ear.as(ExplodedImporter.class).importDirectory(EAR_DIR);
		// META-INF\application.xml im EAR setzen, um test.war fuer Arquillian zu deklarieren
		ear.setApplicationXML("application.xml");

		// EJB-Modul
		final JavaArchive ejbJar = ShrinkWrap.create(JavaArchive.class, PROJEKT_NAME + "EJB.jar");
		ejbJar.as(ExplodedImporter.class).importDirectory(EJB_CLASSES_DIR);
		// JNDI-Namen fuer RESTEasy beginnen mit "test/"
		ejbJar.addResource("jndi.properties");
		ear.addModule(ejbJar);
		
		// Web-Modul fuer REST
		final WebArchive war = ShrinkWrap.create(WebArchive.class, PROJEKT_NAME + "REST.war");
		war.setWebXML("web.xml");
		ear.addModule(war);
		
		LOGGER.info(NEWLINE
		            + ear.toString(true)
					+ NEWLINE + NEWLINE
					+ ejbJar.toString(true)
					+ NEWLINE + NEWLINE
					+ war.toString(true)
					+ NEWLINE);
		
		return ear;
	}
	
	/**
	 */
	public static EnterpriseArchive getTestArchive() {
		return EAR;
	}
}
