package de.eldecker.dhbw.servlets;

import static de.eldecker.dhbw.daten.KategorieEnum.INFO;
import static de.eldecker.dhbw.daten.KategorieEnum.WISS;
import static de.eldecker.dhbw.daten.KategorieEnum.WIWI;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.eldecker.dhbw.daten.KategorieEnum;
import de.eldecker.dhbw.daten.SpruecheDB;
import de.eldecker.dhbw.servlets.model.SpruchRecord;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet für REST-Endpunkt, der einen Spruch zurückliefert.
 */
public class SpruecheServlet extends HttpServlet {

    private static final long serialVersionUID = -7056944262239900479L;
    
    /** Objekt zur Serialisierung eines Java-Objekts nach JSON; wird im Konstruktor erzeugt. */
    private ObjectMapper _jacksonObjectMapper = null;

    /** Singleton-Instanz von Spruch-DB. */
    private SpruecheDB _spruecheDB = SpruecheDB.getSingleton();
    
    /**
     * Default-Konstruktor, erzeugt "Object Mapper" (Jackson) zur Umwandlung von
     * Java-Objekt nach JSON.
     */
    public SpruecheServlet() {
        
        _jacksonObjectMapper = new ObjectMapper();
        _jacksonObjectMapper.enable(INDENT_OUTPUT); // "Pretty Printing" einschalten
    }
    
    /**
     * Methode verarbeitet HTTP-Request für Abfrage von Spruch.
     */
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {

        SpruchRecord ergebnisRecord = null;
        
        Optional<KategorieEnum> kategorieOptional = getKategorie(request);        
        if (kategorieOptional.isEmpty()) {
            
            ergebnisRecord = new SpruchRecord("Keine oder keine gültige Kategorie spezifiziert", false);
            
        } else {
            
            
            
            Optional<Integer> nummerOptional = getNummer(request);
            if (nummerOptional.isEmpty()) {
                
                ergebnisRecord = new SpruchRecord("Keine oder keine gültige Spruchnummer spezifiziert", false);
                
            } else {
             
                KategorieEnum kategorie = kategorieOptional.get();
                int nummer = nummerOptional.get();
                
                String spruch = "";
                if (nummer == -1) {
                    
                    spruch = _spruecheDB.getSpruchZufall(kategorie);
                            
                } else {
                    
                    spruch = _spruecheDB.getSpruchByIndex(kategorie, nummer-1);
                }
                
                if (spruch.isBlank()) {
                    
                    ergebnisRecord = new SpruchRecord("Kein Spruch der gewünschten Kategorie gefunden.", false);
                    
                } else {
                    
                    ergebnisRecord = new SpruchRecord(spruch, true);
                }                
            }            
        }
        
        
        response.setContentType("application/json");
        
        int httpStatusCode = ergebnisRecord.erfolg() ? SC_OK : SC_BAD_REQUEST;
        response.setStatus(httpStatusCode);
        
        String ergebnisJson = _jacksonObjectMapper.writeValueAsString(ergebnisRecord);        
        response.getWriter().println(ergebnisJson);
    }
    
    /**
     * URL-Parameter für Nummer des Spruchs auswerten.
     * 
     * @param request Request-Objekt mit URL-Parametern
     * 
     * @return Optional ist leer, wenn der URL-Parameter mit der Nummer nicht gesetzt war oder 
     *         keine zulässige Nummer (kleiner-gleich 0 oder String, der nicht nach int geparst werden kann) 
     *         enthält.
     *         Wenn die Nummer -1 ist, dann soll ein zufälliger Spruch zurückgeliefert werden.
     *         
     */
    private Optional<Integer> getNummer(HttpServletRequest request) {

        String paramNummer = request.getParameter("nummer");                
        if (paramNummer == null || paramNummer.isBlank()) {
            
            return Optional.empty();
        }
        
        String paramNummerTrimmed = paramNummer.trim();
        if (paramNummerTrimmed.equalsIgnoreCase("zufall")) {
            
            return Optional.of(-1);
        }
        
        int nummer = 0;
                        
        try {
            
            nummer = Integer.parseInt(paramNummerTrimmed);
            
            if (nummer <= 0) {
                
                System.out.println("Nummer \"" + paramNummerTrimmed + "\" war kleiner-gleich 0.");
                return Optional.empty();
            }
            
            return Optional.of(nummer);
            
        }
        catch (NumberFormatException ex) {
            
            System.out.println("Nummer \"" + paramNummerTrimmed + "\" konnte nicht nach int geparst werden.");
            return Optional.empty();
        }        
    }
    
    
    /**
     * URL-Parameter für Kategorie auswerten.
     * 
     * @param request Request-Objekt mit URL-Parametern
     * 
     * @return Optional ist leer, wenn URL-Parameter mit Kategorie nicht spezifiziert
     *         wurde oder keine gültige Kategorie (z.B. "INFO") beinhaltet.
     */
    private Optional<KategorieEnum> getKategorie(HttpServletRequest request) {
        
        String paramKategorie = request.getParameter("kategorie");                
        if (paramKategorie == null || paramKategorie.isBlank()) {
            
            return Optional.empty();
        }
        
        String paramKategorieTrimmed = paramKategorie.trim().toUpperCase();
        switch(paramKategorieTrimmed) {
        
            case "INFO": 
                return Optional.of(INFO);
                
            case "WISS": 
                return Optional.of(WISS);
                
            case "WIWI": 
                return Optional.of(WIWI);
            
            default:
                System.out.println("Kategorie \"" + paramKategorieTrimmed + "\" nicht erkannt.");
                return Optional.empty();
        }        
    }
    
}
