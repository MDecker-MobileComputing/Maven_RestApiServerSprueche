package de.eldecker.dhbw;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Einstiegsklasse des Programms, startet Webserver.
 */
public class App {
	
	public static final int PORT_NUMMER = 8080;
	
    public static void main(String[] args) throws Exception {
    
        Server server = new Server(PORT_NUMMER);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(SpruecheServlet.class, "/*");
        
        System.out.println("Webserver wird auf Port " + PORT_NUMMER + " gestartet.");
        server.start();
        server.join();                
    }
}
