package de.eldecker.dhbw.servlets.model;

/**
 * Ein Objekt dieser (Record-)Klasse beinhaltet das Ergebnis
 * für die Abfrage eines Spruchs. Die Abfrage kann auch
 * fehlgeschlagen sein.
 * 
 * @param text Spruch ({@code erfolg=true}) oder Fehlermeldung ({@code erfolg=false})
 * @param erfolg {@code true} gdw. der Spruch gefunden wurde
 */
public record SpruchRecord( String text, 
                            boolean erfolg ) {

}
