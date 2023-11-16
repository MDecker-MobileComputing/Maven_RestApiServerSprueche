package de.eldecker.dhbw.daten;

/**
 * Datenbank mit Sprüchen aus verschiedenen Kategorien.
 * Es kann höchstens ein Objekt dieser Klasse geben (Singleton!)
 */
public class SpruecheDB {

    private static SpruecheDB _singletonInstanz = null;

    /**
     * Getter für Singleton-Instanz.
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
        
        return 1;
    }
    
    public String getSpruch(KategorieEnum kategorie, int index) {
        
        return "";
    }
    
}
