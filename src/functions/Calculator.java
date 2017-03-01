/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import elements.Airplane;
import elements.Airport;

/**
 *
 * @author mastercs
 */
public class Calculator {

    private final static double EARTHRADIUS = 6371000;

    public static int calcDistance(Airport a, Airport b) {

        double dLat = Math.toRadians(b.getLat() - a.getLat());
        double dLng = Math.toRadians(b.getLng() - a.getLng());
        double a1 = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat()))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a1), Math.sqrt(1 - a1));
        int dist = (int) (EARTHRADIUS * c);

        return dist / 1000;

    }

    public static short TravelTime(short distance, Airplane plane) {
        short time = (short) (distance / plane.getSpeed());
        time *= 1.03;
        return time;
    }

    /**
     *
     * @param maxPax
     * @param costIndex je höher der Wert desto geringer muss der Preis sein
     * damit noch Personen mitfliegen
     * @param price
     * @return
     */
    public static short passangerAmount(short maxPax, byte costIndex, short price) {
        short calcPassanger = (short) (maxPax - (costIndex * price));
        if (calcPassanger < 0) {
            calcPassanger = 0;
        } else if (calcPassanger > maxPax) {
            calcPassanger = maxPax;
        }
        return calcPassanger;
    }
}
