/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import elements.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private List<Airport> airports;
    private List<Airplane> airplanes;
    private List<Player> players;

    public xml_reader() {
        load_airports();
        load_airplanes();
        load_players();
    }

    private void load_airports() {
        String name;
        float lat;
        float lng;
        byte costindex;
        airports = new ArrayList<>();

        try {
            File stocks = new File("src/core/airports.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("airport");

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    name = getValue("name", element);
                    lat = Float.parseFloat(getValue("lat", element));
                    lng = Float.parseFloat(getValue("lng", element));
                    costindex = Byte.parseByte(getValue("costindex", element));
                    airports.add(new Airport((byte) i, name, lat, lng, costindex));
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void load_airplanes() {
        String manufacturer;
        String type;
        String textinfo;
        short speed;
        int price;
        short max_range;
        short max_pax;
        short max_fuel;
        airplanes = new ArrayList<>();

        try {
            File stocks = new File("src/core/aircrafts.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("aircraft");

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    manufacturer = getValue("manufacturer", element);
                    type = getValue("type", element);
                    textinfo = getValue("textinfo", element);
                    speed = Short.parseShort(getValue("speed", element));
                    price = Integer.parseInt(getValue("price", element));
                    max_range = Short.parseShort(getValue("max_range", element));
                    max_pax = Short.parseShort(getValue("max_pax", element));
                    max_fuel = Short.parseShort(getValue("max_fuel", element));
                    airplanes.add(new Airplane((byte) i, manufacturer, type, textinfo, speed, max_range, max_pax, max_fuel, price, airports.get(0)));
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void load_players() {

        String name;
        int money;
        players = new ArrayList<>();

        try {
            File stocks = new File("src/core/players.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("player");
            for (int i = 0; i < nodes.getLength(); i++) {
                List<Airplane> playerAirplanes = new ArrayList<>();
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    name = getValue("name", element);
                    money = Integer.parseInt(getValue("money", element));
                    int fleetIndex = Integer.parseInt(getValue("fleet", element));
                    playerAirplanes.add(airplanes.get(fleetIndex));
                    players.add(new Player((byte) i, name, new Bank((byte) i, money), playerAirplanes));

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

    public List<Player> getPlayers() {
        return players;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public List<Airport> getAirports() {
        return airports;
    }

}
