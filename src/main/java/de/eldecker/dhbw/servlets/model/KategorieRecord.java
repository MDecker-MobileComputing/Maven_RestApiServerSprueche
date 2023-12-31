package de.eldecker.dhbw.servlets.model;

/**
 * Ein Record beschreibt eine Spruch-Kategorie (inkl. Anzahl der Sprüche).
 * Eine Liste der Record-Klasse wird vom REST-Endpoint {@code /kategorien} 
 * zurückgegeben.
 * 
 * @param techName       Technischer Name (ID) der Kategorie
 * @param beschreibung   Kurzer Beschreibungstext
 * @param anzahlSprueche Aktuelle Anzahl der Sprüche in dieser Kategorie
 */
public record KategorieRecord( String techName, 
                               String beschreibung,
                               int anzahlSprueche ) {
}
