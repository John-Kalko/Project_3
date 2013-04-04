/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.parsers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import ua.epam.kalko.entity.TariffList;

/**
 * StAX Parser for valid XML file (Tariff.SXD).
 *
 * @author Kalko I.
 */
public class StaxParser extends Parser {

    //Parser constants
    enum ParserEnum {

        TARIFF, TARIFF_NAME, OPERATOR1, OPERATOR2, OPERATOR3, PAYROLL,
        CALL_PRICES, SMS_PRICE, ADDITIONAL_PRICES, TARRIFIACTION_TYPE,
        DISCOUNT_NUMBERS, TARIFF_PRICE
    };
    private TariffList list;

    /**
     * Parses XML file and returnes true if successful.
     *
     * @param target TariffList.
     * @return true if succeded.
     */
    @Override
    public boolean execute(TariffList target) {
        this.list = target;
        try {
            InputStream is = null;
            try {
                is = new FileInputStream(Parser.pathToXML);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            process(reader);
            return true;
        } catch (XMLStreamException ex) {
            Logger.getLogger(StaxParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Parses XML file.
     *
     * @param reader
     * @throws XMLStreamException
     */
    private void process(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int type;

            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    chooseElement(reader.getLocalName(), reader);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (ParserEnum.valueOf(reader.getLocalName().toString().
                            toUpperCase()) == ParserEnum.TARIFF_NAME) {
                        super.initializer(list);
                    }
                    break;
            }
        }
    }

    /**
     * Continue parsing XML file.
     *
     * @param localName
     * @param reader
     * @throws XMLStreamException
     */
    private void chooseElement(String localName, XMLStreamReader reader)
            throws XMLStreamException {
        switch (ParserEnum.valueOf(localName.toUpperCase())) {
            case TARIFF:
                break;
            case ADDITIONAL_PRICES:
                break;
            case TARIFF_NAME:
                super.name = reader.getAttributeValue(0).trim();
                break;
            case OPERATOR1:
                super.operators = Operators.MTS;
                break;
            case OPERATOR2:
                super.operators = Operators.KYIVSAR;
                break;
            case OPERATOR3:
                super.operators = Operators.LIFE;
                break;
            case PAYROLL:
                super.payroll = Double.parseDouble(reader.getElementText());
                break;
            case CALL_PRICES:
                super.callPriceInsideNetwork = Double.parseDouble(reader.
                        getAttributeValue(0));
                super.callPriceToAnotherNetwork = Double.parseDouble(reader.
                        getAttributeValue(1));
                super.callPriceOutsideNetwork = Double.parseDouble(reader.
                        getAttributeValue(2));
                break;
            case SMS_PRICE:
                super.smsPrice = Double.parseDouble(reader.getElementText());
                break;
            case DISCOUNT_NUMBERS:
                super.discountNumbers = Integer.parseInt(reader.getElementText());
                break;
            case TARRIFIACTION_TYPE:
                String type = reader.getElementText().toString();
                if (type.equals("from_first_second")) {
                    super.tarrification = Tarrification.FROM_FIRST_SECOND;
                } else if (type.equals("from_first_minute")) {
                    super.tarrification = Tarrification.FROM_FIRST_MINUTE;
                } else if (type.equals("from_fifth_second")) {
                    super.tarrification = Tarrification.FROM_FIFTH_SECOND;
                }
                break;
            case TARIFF_PRICE:
                super.tariffPrice = Double.parseDouble(reader.getElementText());
                break;
            default:
                throw new XMLStreamException(""
                        + "Unrecognised tag found while parsing: "
                        + reader.getLocalName());
        }
    }
}
