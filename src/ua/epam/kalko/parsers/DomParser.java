/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.parsers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import ua.epam.kalko.entity.TariffList;

/**
 * DOM Parser for valid XML (Tariff.XSD).
 *
 * @author Kalko I.
 */
public class DomParser extends Parser {

    //Used for operator's type selection
    private int operatorIndex;
    private Pattern pattern;
    private Matcher matcher;

    /**
     * Initializes object.
     */
    public DomParser() {
        this.pattern = Pattern.compile("MTS|Kyivstar|Life");
    }

    /**
     * Parses XML file from Parser.XMLPath and returnes true if successful.
     *
     * @param target TariffList.
     * @return
     */
    @Override
    public boolean execute(TariffList target) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder doc = factory.newDocumentBuilder();
            Document document = doc.parse(Parser.pathToXML);
            Element el = document.getDocumentElement();
            initializeTariffList(target, el);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
        } catch (IOException ex) {
        }
        return false;
    }

    /* Parses XML file */
    private void initializeTariffList(TariffList target, Element el) throws SAXException, IOException {
        //Number of Tariff objects
        int totalSize = el.getElementsByTagName("ns1:tariff_name").getLength();

        for (int i = 0; i < totalSize; i++) {
            super.name = el.getElementsByTagName("ns1:tariff_name").item(i).
                    getAttributes().item(0).getTextContent().trim();
            super.operators = setOperator(el);
            super.payroll = Double.parseDouble(el.getElementsByTagName(
                    "ns1:payroll").item(i).getTextContent().trim());
            NamedNodeMap prices = el.getElementsByTagName("ns1:call_prices").
                    item(i).getAttributes();
            super.callPriceInsideNetwork = Double.parseDouble(prices.getNamedItem(
                    "inside_network").getNodeValue().trim());
            super.callPriceOutsideNetwork = Double.parseDouble(prices.getNamedItem(
                    "outside_network").getNodeValue().trim());
            super.callPriceToAnotherNetwork = Double.parseDouble(prices.getNamedItem(
                    "another_network").getNodeValue().trim());
            super.smsPrice = Double.parseDouble(el.getElementsByTagName(
                    "ns1:sms_price").item(i).getTextContent().trim());
            super.discountNumbers = Integer.parseInt(el.getElementsByTagName(
                    "ns1:discount_numbers").item(i).getTextContent().trim());
            super.tarrification = getTarrification(el, i);
            super.tariffPrice = Double.parseDouble(el.getElementsByTagName(
                    "ns1:tariff_price").item(i).getTextContent().trim());

            //Initializes Tariff object and adds it to the TariffList
            this.initializer(target);
        }
    }

    /* Selects operator type */
    private Operators setOperator(Element el) {
        this.matcher = this.pattern.matcher(el.getTextContent());
        if (matcher.find(this.operatorIndex)) {
            if (matcher.group().equals("MTS")) {
                this.operatorIndex = matcher.end();
                return Operators.MTS;
            } else if (matcher.group().equals("Kyivstar")) {
                this.operatorIndex = matcher.end();
                return Operators.KYIVSAR;
            } else if (matcher.group().equals("Life")) {
                this.operatorIndex = matcher.end();
                return Operators.LIFE;
            }
        } else {
            System.out.println("No matches");
        }
        return null;
    }

    /* Selects tariff type */
    private Tarrification getTarrification(Element el, int i) {
        String choise = el.getElementsByTagName("ns1:tarrifiaction_type").item(i).getTextContent().trim();
        if (choise.equals("from_first_second")) {
            return Tarrification.FROM_FIRST_SECOND;
        } else if (choise.equals("from_fifth_second")) {
            return Tarrification.FROM_FIFTH_SECOND;
        } else if (choise.equals("from_first_minute")) {
            return Tarrification.FROM_FIRST_MINUTE;
        }
        return null;
    }
}
