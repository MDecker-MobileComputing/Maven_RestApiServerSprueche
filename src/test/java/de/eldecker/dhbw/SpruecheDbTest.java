package de.eldecker.dhbw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static de.eldecker.dhbw.daten.KategorieEnum.INFO;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import de.eldecker.dhbw.daten.SpruecheDB;

/**
 * Tests für die Klasse {@link SpruecheDB}
 */
public class SpruecheDbTest {

    /** CUT: Class/Code Under Test; hier: ein Singleton */
    private SpruecheDB _cut = SpruecheDB.getSingleton();
    
    @ParameterizedTest
    @ValueSource(ints = { MIN_VALUE, -1, MAX_VALUE })
    void getInfoSpruchByNonExistingIndex(int index) {
        
        String resultStr = _cut.getSpruchByIndex(INFO, index);        
        assertEquals( "", resultStr, "Kein leerer String für nicht-existierenden Spruch-Index" );
    }
    
}
