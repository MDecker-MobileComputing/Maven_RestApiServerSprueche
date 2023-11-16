package de.eldecker.dhbw.servlets;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Dieses Servlet beantwortet alle Anfragen auf Pfade, die nicht explizit
 * von der REST-API unterstützt werden.
 */
public class FallbackServlet extends HttpServlet {

    private static final long serialVersionUID = -80748564301575529L;

    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setStatus(SC_OK);
        
        final String nachricht = String.format("Fehler: Unerwarteter Pfad \"%s\" aufgerufen.", 
                                               request.getPathInfo());
                        
        System.out.println(nachricht);
        
        response.getWriter().println(nachricht);
    }
    
}
