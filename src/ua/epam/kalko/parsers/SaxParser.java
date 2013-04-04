/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.parsers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.epam.kalko.entity.TariffList;
import ua.epam.kalko.parsers.Parser.Operators;
import ua.epam.kalko.parsers.Parser.Tarrification;

/**
 * SAX parser for valid XML files (Tariff.xsd).
 *
 * @author Kalko I.
 */
public class SaxParser extends Parser {

    /**
     * Parses XML file and returnes true if successful.
     *
     * @param list TariffList.
     * @return true if succeded.
     */
    @Override
    public boolean execute(TariffList list) {
        SaxHandler handler = new SaxHandler(this, list);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(Parser.pathToXML, handler);
            return true;
        } catch (SAXException ex) {
            Logger.getLogger(SaxParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaxParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaxParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

/**
 * Handler for SAX parser.
 *
 * @author Kalko I.
 */
class SaxHandler extends DefaultHandler {

    //Flags
    private boolean isPayroll;
    private boolean isSmsPrice;
    private boolean isDiscountNumbers;
    private boolean isTarrification;
    private boolean isTariffPrice;
    private SaxParser parser;
    private TariffList list;

    /**
     * Initializes handler.
     *
     * @param parser
     * @param list
     */
    public SaxHandler(SaxParser parser, TariffList list) {
        this.parser = parser;
        this.list = list;
    }

    /**
     * Calls when the start of tag reached.
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        if (qName.equals("ns1:tariff_name")) {
            this.parser.name = atts.getValue(0);
        } else if (qName.equals("ns1:operator1")) {
            this.parser.operators = Operators.MTS;
        } else if (qName.equals("ns1:operator2")) {
            this.parser.operators = Operators.KYIVSAR;
        } else if (qName.equals("ns1:operator3")) {
            this.parser.operators = Operators.LIFE;
        } else if (qName.equals("ns1:payroll")) {
            this.isPayroll = true;
        } else if (qName.equals("ns1:call_prices")) {
            this.parser.callPriceInsideNetwork = Double.parseDouble(atts.getValue(0));
            this.parser.callPriceOutsideNetwork = Double.parseDouble(atts.getValue(1));
            this.parser.callPriceToAnotherNetwork = Double.parseDouble(atts.getValue(2));
        } else if (qName.equals("ns1:sms_price")) {
            this.isSmsPrice = true;
        } else if (qName.equals("ns1:discount_numbers")) {
            this.isDiscountNumbers = true;
        } else if (qName.equals("ns1:tarrifiaction_type")) {
            this.isTarrification = true;
        } else if (qName.equals("ns1:tariff_price")) {
            this.isTariffPrice = true;
        }
    }

    /**
     * Tag contents.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.isPayroll) {
            this.parser.payroll = Double.parseDouble(String.valueOf(ch, start, length).trim());
            this.isPayroll = false;
        } else if (this.isSmsPrice) {
            this.parser.smsPrice = Double.parseDouble(String.valueOf(ch, start, length).trim());
            this.isSmsPrice = false;
        } else if (this.isDiscountNumbers) {
            this.parser.discountNumbers = Integer.parseInt(String.valueOf(ch, start, length).trim());
            this.isDiscountNumbers = false;
        } else if (this.isTariffPrice) {
            this.parser.tariffPrice = Double.parseDouble(String.valueOf(ch, start, length).trim());
            this.isTariffPrice = false;
        } else if (this.isTarrification) {
            setTariffication(String.valueOf(ch, start, length).trim());
        }
    }

    /**
     * Calls when the end of tag reached.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("ns1:tariff_name")) {
            this.parser.initializer(this.list);
        }
    }

    /**
     * Selects tariff type.
     *
     * @param trim String.
     */
    private void setTariffication(String trim) {
        if (trim.equals("from_first_second")) {
            this.parser.tarrification = Tarrification.FROM_FIRST_SECOND;
        } else if (trim.equals("from_first_minute")) {
            this.parser.tarrification = Tarrification.FROM_FIRST_MINUTE;
        } else if (trim.equals("from_fifth_second")) {
            this.parser.tarrification = Tarrification.FROM_FIFTH_SECOND;
        }
    }
}