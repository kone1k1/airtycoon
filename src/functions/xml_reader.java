/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import elements.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mastercs
 */
public class xml_reader {

    Airport[] airports;
    Airplane[] airplanes;
    Player[] players;
    int number_airplanes;
    int number_airports;
    int number_players;

    public xml_reader() {
        load_airports();
        load_airplanes();
        load_players();
    }

    private void load_airports() {
        byte id;
        String name;
        float lat;
        float lng;
        byte costindex;

        try {
            File stocks = new File("src/air_tycoon/core/airports.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("airport");
            airports = new Airport[nodes.getLength()];
            number_airports = nodes.getLength();
            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    id = Byte.parseByte(getValue("id", element));
                    name = getValue("name", element);
                    lat = Float.parseFloat(getValue("lat", element));
                    lng = Float.parseFloat(getValue("lng", element));
                    costindex = Byte.parseByte(getValue("costindex", element));
                    airports[i] = new Airport(id, name, lat, lng, costindex);
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void load_airplanes() {
        byte id;
        String manufacturer;
        String type;
        String textinfo;
        short speed;
        int price;
        short max_range;
        short max_pax;
        short max_fuel;

        try {
            File stocks = new File("src/air_tycoon/core/aircrafts.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("aircraft");
            airplanes = new Airplane[nodes.getLength()];
            number_airplanes = nodes.getLength();
            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    id = Byte.parseByte(getValue("id", element));
                    manufacturer = getValue("manufacturer", element);
                    type = getValue("type", element);
                    textinfo = getValue("textinfo", element);
                    speed = Short.parseShort(getValue("speed", element));
                    price = Integer.parseInt(getValue("price", element));
                    max_range = Short.parseShort(getValue("max_range", element));
                    max_pax = Short.parseShort(getValue("max_pax", element));
                    max_fuel = Short.parseShort(getValue("max_fuel", element));
                    airplanes[i] = new Airplane(id, manufacturer, type, textinfo, speed, max_range, max_pax, max_fuel, price, airports[0]);
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void load_players() {
        byte id;
        String name;
        int money;

        try {
            File stocks = new File("src/air_tycoon/core/players.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("player");
            players = new Player[nodes.getLength()];
            number_players = nodes.getLength();
            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    id = Byte.parseByte(getValue("id", element));
                    name = getValue("name", element);
                    money = Integer.parseInt(getValue("money", element));
                    players[i] = new Player(id, name, new Bank(id, money), airplanes);

                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    public int getNumber_airplanes() {
        return number_airplanes;
    }

    public int getNumber_airports() {
        return number_airports;
    }

    public int getNumber_players() {
        return number_players;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Airplane[] getAirplanes() {
        return airplanes;
    }

    public Airport[] getAirports() {
        return airports;
    }

}
