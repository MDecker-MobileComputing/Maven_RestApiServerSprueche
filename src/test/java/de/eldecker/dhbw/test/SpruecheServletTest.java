package de.eldecker.dhbw.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.eldecker.dhbw.servlets.SpruecheServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpruecheServletTest {

    /** CUT: Class/Code unter Test. */ 
    private SpruecheServlet _cut = new SpruecheServlet(); 
    
    @Test
    void keineUrlParameter() throws Exception {
        
        HttpServletRequest  requestMock  = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        
        Mockito.when(responseMock.getWriter()).thenReturn(printWriter);
        
        // *** Call method under Test ***
        _cut.doGet(requestMock, responseMock);         
                        
        Mockito.verify(responseMock, times(1)).setStatus(400); // Bad Request
        Mockito.verify(responseMock, times(1)).setContentType("application/json");
        
        String payloadResponseStr = stringWriter.toString();
        JSONObject jsonObj = new JSONObject(payloadResponseStr);
        assertFalse( jsonObj.getBoolean("erfolg") );
        assertFalse( jsonObj.getString("text").isBlank());        
    }
    
    @Test
    void happyPath() throws Exception {
        
        HttpServletRequest requestMock  = Mockito.mock(HttpServletRequest.class);
        Mockito.when(requestMock.getParameter("nummer")).thenReturn("1");
        Mockito.when(requestMock.getParameter("kategorie")).thenReturn("INFO");
        
        HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);        
        Mockito.when(responseMock.getWriter()).thenReturn(printWriter);
        
        // *** Call method under Test ***
        _cut.doGet(requestMock, responseMock);         
                        
        Mockito.verify(responseMock, times(1)).setStatus(200); // OK
        Mockito.verify(responseMock, times(1)).setContentType("application/json");
        
        String payloadResponseStr = stringWriter.toString();
        JSONObject jsonObj = new JSONObject(payloadResponseStr);
        assertTrue( jsonObj.getBoolean("erfolg") );
        assertEquals( "Kein Backup? Kein Mitleid!", jsonObj.get("text"));       
    }
    
}
