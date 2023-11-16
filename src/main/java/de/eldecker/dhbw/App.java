package de.eldecker.dhbw;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import de.eldecker.dhbw.servlets.FallbackServlet;
import de.eldecker.dhbw.servlets.KategorienServlet;
import de.eldecker.dhbw.servlets.SpruecheServlet;

/**
 * Einstiegsklasse des Programms, startet Webserver, der die
 * REST-API bereitstellt.
 */
public class App {
    
    /** Portnummer, unter der Jetty die REST-API anbietet. */
    public static final int PORT_NUMMER = 8080;
    
    /**
     * Einstiegsmethod, startet Webserver.
     * 
     * @param args Wird nicht ausgewertet
     * @throws Exception Fehler beim Start von Webserver aufgetreten
     */
    public static void main(String[] args) throws Exception {
    
        Server server = new Server(PORT_NUMMER);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        // Servlets für die einzelnen Pfade registrieren
        handler.addServletWithMapping(FallbackServlet.class  , "/*");
        handler.addServletWithMapping(SpruecheServlet.class  , "/sprueche" );
        handler.addServletWithMapping(KategorienServlet.class, "/kategorien");
        
        System.out.println("Webserver wird auf Port " + PORT_NUMMER + " gestartet.");
        server.start(); // throws Exception
        server.join();  // throws Exception
    }
}
