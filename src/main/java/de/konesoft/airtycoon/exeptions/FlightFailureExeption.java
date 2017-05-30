package de.konesoft.airtycoon.exeptions;

/**
 * Klasse für mögliche Probleme bei einem Flug
 *
 * @author mastercs
 */
public class FlightFailureExeption extends Exception {

    @Override
    public String getMessage() {
        return "Fehler";
    }

}
