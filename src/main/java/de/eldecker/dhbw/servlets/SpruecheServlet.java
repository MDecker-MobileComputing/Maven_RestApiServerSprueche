package de.eldecker.dhbw.servlets;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpruecheServlet extends HttpServlet {

	private static final long serialVersionUID = -7056944262239900479L;

	@Override
    protected void doGet(HttpServletRequest request, 
    		             HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setStatus(SC_OK);
        response.getWriter().println("Sprüche ...");
    }
	
}
