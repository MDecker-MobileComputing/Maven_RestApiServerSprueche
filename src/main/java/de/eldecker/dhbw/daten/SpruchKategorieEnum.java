package de.eldecker.dhbw.daten;

/**
 * Aufzählungstyp für die verschiedenen Kategorien (Themenbereiche) 
 * von Sprüchen.
 */
public enum SpruchKategorieEnum {

	INFO("Informatik"),	
	WISS("Wissenschaft");
	
	
	/** Kurzer Beschreibungstext für Spruchkategorie, z.B. "Informatik". */
	private String _beschreibung;
	
	/**
	 * Konstruktor zur Erzeugung eines neues Enum-Elements, das eine
	 * Spruch-Kategorie (Thema, z.B. "Informatik") beschreibt.
	 * 
	 * @param beschreibung Kurzer Beschreibungstext für Spruchkategorie
	 */
	private SpruchKategorieEnum(String beschreibung) {
		
		_beschreibung = beschreibung;
	}
	
	/**
	 * Getter für Beschreibungstext der Kategorie.
	 * 
	 * @return Kurzer Beschreibungstext für Spruchkategorie, z.B. "Informatik"
	 */
	public String getBeschreibung() {
		
		return _beschreibung;
	}
	
}
