package de.eldecker.dhbw.servlets;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.eldecker.dhbw.daten.SpruecheDB;
import de.eldecker.dhbw.servlets.model.KategorieRecord;
import de.eldecker.dhbw.daten.KategorieEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Dieses Servlet beantwortet Anfragen nach der Liste der Kategorien
 * von Sprüchen.
 */
public class KategorienServlet extends HttpServlet {

    private static final long serialVersionUID = 3617076680380675368L;

    /** Objekt zur Serialisierung eines Java-Objekts nach JSON; wird im Konstruktor erzeugt. */
    private ObjectMapper _jacksonObjectMapper = null;

    /** Singleton-Instanz von Spruch-DB. */
    private SpruecheDB _sprucheDB = SpruecheDB.getSingleton();

    /**
     * Default-Konstruktor, erzeugt "Object Mapper" (Jackson) zur Umwandlung von
     * Java-Objekt nach JSON.
     */
    public KategorienServlet() {

        _jacksonObjectMapper = new ObjectMapper();
        _jacksonObjectMapper.enable(INDENT_OUTPUT); // "Pretty Printing" einschalten
    }

    /**
     * Methode wird für HTTP-GET Request aufgerufen.
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        KategorieEnum[] kategorienArray = KategorieEnum.values();

        List<KategorieRecord> ergebnisListe = new ArrayList<>(kategorienArray.length);
        KategorieRecord katRecord = null;
        for (KategorieEnum kategorie : kategorienArray) {

            katRecord = new KategorieRecord( kategorie.name(),
                                             kategorie.getBeschreibung(),
                                             _sprucheDB.getAnzahlSprueche(kategorie) );
            ergebnisListe.add(katRecord);
        }

        String ergebnisJson = _jacksonObjectMapper.writeValueAsString(ergebnisListe);

        response.setContentType("application/json");
        response.setStatus(SC_OK);
        response.getWriter().println(ergebnisJson);
    }

}
