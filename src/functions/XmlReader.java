/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import main.Airplane;
import main.Airport;
import main.Player;
import main.Bank;
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
public class XmlReader {

    private List<Airport> airports;
    private List<Airplane> airplanes;
    private List<Player> players;

    public XmlReader() {
        loadAirports();
        loadAirplanes();
        loadPlayers();
    }

    private void loadAirports() {

        airports = new ArrayList<>();

        try {
            File stocks = new File("src/config/airports.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("airport");

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String name = getValue("name", element);
                    float lat = Float.parseFloat(getValue("lat", element));
                    float lng = Float.parseFloat(getValue("lng", element));
                    byte costindex = Byte.parseByte(getValue("costindex", element));

                    airports.add(new Airport((byte) i, name, lat, lng, costindex));
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void loadAirplanes() {

        airplanes = new ArrayList<>();

        try {
            File stocks = new File("src/config/aircrafts.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("aircraft");

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String manufacturer = getValue("manufacturer", element);
                    String type = getValue("type", element);
                    String textinfo = getValue("textinfo", element);
                    short speed = Short.parseShort(getValue("speed", element));
                    int price = Integer.parseInt(getValue("price", element));
                    short max_range = Short.parseShort(getValue("max_range", element));
                    short max_pax = Short.parseShort(getValue("max_pax", element));
                    short max_fuel = Short.parseShort(getValue("max_fuel", element));
                    airplanes.add(new Airplane((byte) i, manufacturer, type, textinfo, speed, max_range, max_pax, max_fuel, price, airports.get(0)));
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException ex) {
        }

    }

    private void loadPlayers() {

        players = new ArrayList<>();

        try {
            File stocks = new File("src/config/players.xml");
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
                    String name = getValue("name", element);
                    int money = Integer.parseInt(getValue("money", element));
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
