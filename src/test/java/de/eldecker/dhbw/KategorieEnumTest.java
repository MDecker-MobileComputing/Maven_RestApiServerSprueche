package de.eldecker.dhbw;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import de.eldecker.dhbw.daten.KategorieEnum;
import org.junit.jupiter.api.Test;

/**
 * Tests für den Aufzählungstyp {@link KategorieEnum}.
 */
public class KategorieEnumTest {

    @Test
    void mindestensEineKategorie() {

        KategorieEnum[] kategorieArray = KategorieEnum.values();        
        assertTrue(kategorieArray.length > 0, "Keine einzige Kategorie definiert!");
    }
    
    @Test
    void jedeKategorieHatBeschreibung() {
        
        for (KategorieEnum k: KategorieEnum.values()) {
            
            assertFalse ( k.getBeschreibung().isBlank(), "Kategorie ohne Beschreibungstext gefunden" );
        }        
    }
        
}
