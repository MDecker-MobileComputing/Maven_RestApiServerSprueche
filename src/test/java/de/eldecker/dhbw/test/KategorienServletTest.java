package de.eldecker.dhbw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import de.eldecker.dhbw.daten.KategorieEnum;
import de.eldecker.dhbw.servlets.KategorienServlet;
import de.eldecker.dhbw.test.util.MeinHttpServletResponseTestDouble;

public class KategorienServletTest {

    /** CUT: Class/code under test. */
    private KategorienServlet _cut = new KategorienServlet(); 
    
    @Test
    void happyPath() throws Exception {
        
        MeinHttpServletResponseTestDouble responseTestDouble = new MeinHttpServletResponseTestDouble(); 
        
        
        // *** Call method under test ***
        _cut.doGet(null, responseTestDouble);
        
        
        assertEquals( "application/json", responseTestDouble.getContentType());
        assertEquals( 200, responseTestDouble.getStatus());
        
        String resultString = responseTestDouble.getStringWrittenToPrintWriter();
        
        JSONArray jsonArray = new JSONArray(resultString);
        int jsonArrayLenght = jsonArray.length();
        assertEquals(KategorieEnum.values().length, jsonArrayLenght); 
        
        for (int i = 0; i< jsonArrayLenght; i++) {
            
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            assertFalse( jsonObj.getString("techName").isBlank() );
            assertFalse( jsonObj.getString("beschreibung").isBlank() );
            assertTrue( jsonObj.getInt("anzahlSprueche")>= 0 );
        }
    }
    
}
