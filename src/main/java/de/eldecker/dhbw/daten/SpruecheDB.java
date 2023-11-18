package de.eldecker.dhbw.daten;

import static de.eldecker.dhbw.daten.KategorieEnum.INFO;
import static de.eldecker.dhbw.daten.KategorieEnum.WISS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Datenbank mit Sprüchen aus verschiedenen Kategorien.
 * Es kann höchstens ein Objekt dieser Klasse geben (Singleton!)
 */
public class SpruecheDB {

    /** Referenz auf Singleton-Instanz dieser Klasse. */
    private static SpruecheDB _singletonInstanz = null;

    /**
     * Map mit allen Sprüchen: jede Kategorie wird auf eine Liste
     * der Sprüche dieser Kategorie abgebildet. Die Map wird im
     * Konstruktor gefüllt.
     */
    private Map<KategorieEnum,List<String>> _kategorieZuListeMap = null;

    /** Zufallsgenerator für Auswahl zufälliger Spruch. */
    private Random _zufallsGenerator = new Random();

    /**
     * Privater Konstruktor (weil Singleton!), baut HashMap mit Sprüchen auf.
     */
    private SpruecheDB() {

        int anzahlKategorien = KategorieEnum.values().length;
        _kategorieZuListeMap = new HashMap<>(anzahlKategorien);

        List<String> infoSpruecheList = new ArrayList<>(20);
        infoSpruecheList.add("Kein Backup? Kein Mitleid!");
        infoSpruecheList.add("Reboot tut immer gut.");
        infoSpruecheList.add("The password must be impossible to remember and never written down.");
        infoSpruecheList.add("Und ist das Coding noch so klein, ein kleiner Bug passt immer rein.");
        infoSpruecheList.add("Science is what we understand well enough to explain to a computer. Art is everything else we do." );
        infoSpruecheList.add("The three golden rules to ensure computer security are: do not own a computer; do not power it on; and do not use it.");
        infoSpruecheList.add("Requirements are like water: They're easier to build on when they're frozen.");
        infoSpruecheList.add("Who needs a social life when you have the internet?");
        infoSpruecheList.add("Spinner gab es schon immer, aber dank Internet finden die sich jetzt auch noch gegenseitig.");
        _kategorieZuListeMap.put(INFO, infoSpruecheList);

        List<String> wissSprucheList = new ArrayList<>(20);
        wissSprucheList.add("If we knew what it was we were doing, it would not be called research, would it?");
        wissSprucheList.add("Ein Mathematiker ist eine Maschine, die Kaffee in Theoreme umwandelt.");
        wissSprucheList.add("Jede Wissenschaft braucht eine andere Wissenschaft, auf die sie herabblicken kann.");
        wissSprucheList.add("Eine gute Forschungsarbeit wirft mehr neue Fragen auf als sie beantwortet.");
        wissSprucheList.add("Ein Buch ist ein Spiegel: wenn ein Affe hineinsieht, so kann kein Apostel herausgucken.");
        _kategorieZuListeMap.put(WISS, wissSprucheList);
    }


    /**
     * Getter für Singleton-Instanz; erzeugt Singleton-Instanz bei Bedarf.
     *
     * @return Singleton-Objekt
     */
    public static SpruecheDB getSingleton() {

        if (_singletonInstanz == null) {

            _singletonInstanz = new SpruecheDB();
        }

        return _singletonInstanz;
    }


    /**
     * Anzahl der Sprüche für {@code kategorie} abfragen.
     *
     * @param kategorie Enum-Objekt, das die Kategorie repräsentiert,
     *                  für die die Anzahl der Sprüche zurückgeliefert
     *                  werden soll.
     *
     * @return Anzahl der Sprüche in der Kategorie, kann 0 sein
     */
    public int getAnzahlSprueche(KategorieEnum kategorie) {

        List<String> spruchListe = _kategorieZuListeMap.get(kategorie);
        if (spruchListe != null) {

            return spruchListe.size();

        } else {

            return 0;
        }
    }


    /**
     * Zufälligen Spruch von {@code kategorie} auswählen.
     *
     * @param kategorie Kategorie des Spruchs
     * @return Spruch oder leerer String, wenn Kategorie nicht gefunden
     *         oder Kategorie leer ist
     */
    public String getSpruchZufall(KategorieEnum kategorie) {

        List<String> spruchListe = _kategorieZuListeMap.get(kategorie);
        if (spruchListe == null) {

            return "";
        }

        int anzSprueche = spruchListe.size();
        if (anzSprueche == 0) {

            return "";
        }

        int zufallsIndex = _zufallsGenerator.nextInt(anzSprueche);

        return spruchListe.get(zufallsIndex);
    }

    /**
     * Spruch anhand von Index abrufen.
     *
     * @param kategorie Kategorie des Spruchs
     * @param index 0-basierter Index des Spruchs
     * @return Spruch oder leerer String, wenn {@code kategorie} nicht gefunden
     *         wurde, oder wenn {@code index} hinter dem letzten Spruch liegt
     */
    public String getSpruchByIndex(KategorieEnum kategorie, int index) {

        List<String> spruchListe = _kategorieZuListeMap.get(kategorie);
        if (spruchListe == null) {

            return "";
        }

        if (index < 0 || index >= spruchListe.size() ) {

            return "";
        }

        return spruchListe.get(index);
    }

}
