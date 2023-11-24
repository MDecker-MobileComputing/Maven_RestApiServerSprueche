package de.eldecker.dhbw.servlets;

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
    public void doGet( HttpServletRequest request, HttpServletResponse response )
             throws ServletException, IOException {

        SpruchRecord ergebnisRecord = urlParameter2Spruch(request);

        response.setContentType("application/json");

        int httpStatusCode = ergebnisRecord.erfolg() ? SC_OK : SC_BAD_REQUEST;
        response.setStatus(httpStatusCode);

        String ergebnisJson = _jacksonObjectMapper.writeValueAsString(ergebnisRecord);
        response.getWriter().println(ergebnisJson);
    }

    /**
     * Die Methode versucht, anhand der URL-Parameter aus {@code request}
     * den Spruch zu bestimmten.
     *
     * @param request Request-Objekt, um URL-Parameter zu extrahieren
     * @return Objekt mit Spruch; hat {@code erfolg=true} wenn der Spruch bestimmt werden
     *         konnte, sonst {@code erfolg=false}; im letzteren Fall enthält das Attribut
     *         "text" statt dem Spruch eine kurze Fehlerbeschreibung.
     */
    private SpruchRecord urlParameter2Spruch(HttpServletRequest request) {

        Optional<KategorieEnum> kategorieOptional = getKategorie(request);
        if (kategorieOptional.isEmpty()) {

            return new SpruchRecord("Keine oder keine gültige Kategorie spezifiziert.", false);
        }

        Optional<Integer> nummerOptional = getNummer(request);
        if (nummerOptional.isEmpty()) {

            return new SpruchRecord("Keine oder keine gültige Spruchnummer spezifiziert.", false);

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

                return new SpruchRecord("Kein Spruch der gewünschten Kategorie gefunden.", false);

            } else {

                return new SpruchRecord(spruch, true);
            }
        }
    }


    /**
     * URL-Parameter für Nummer des Spruchs auswerten.
     *
     * @param request Request-Objekt mit URL-Parametern
     *
     * @return Optional ist leer, wenn der URL-Parameter mit der Nummer nicht gesetzt war oder
     *         keine zulässige Nummer (kleiner-gleich 0 oder String, der nicht nach int geparst
     *         werden kann) enthält.
     *         Wenn die Nummer -1 ist, dann soll ein zufälliger Spruch zurückgeliefert werden.
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

            System.out.println("Nummer \"" + paramNummerTrimmed + "\" konnte nicht als int-Wert geparst werden.");
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

        String paramValueNormalized = paramKategorie.trim().toUpperCase();

        try {

            KategorieEnum enumValue = KategorieEnum.valueOf(paramValueNormalized);
            return Optional.of(enumValue);
        }
        catch (IllegalArgumentException ex) {

            return Optional.empty();
        }
    }

}
