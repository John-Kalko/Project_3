/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko;

import java.util.Iterator;
import ua.epam.kalko.entity.TariffList;
import ua.epam.kalko.parsers.DocumentValidator;
import ua.epam.kalko.parsers.DomParser;
import ua.epam.kalko.parsers.Parser;
import ua.epam.kalko.parsers.SaxParser;
import ua.epam.kalko.parsers.StaxParser;
import ua.epam.kalko.serializators.Serializator;
import ua.epam.kalko.serializators.ToHtmlSerializator;

/**
 * Test driver, which also indicates changes to the entity (Tariff) object.
 *
 * @author Kalko I.
 */
public class Driver {

    public static void main(String[] args) {
        //Initializations
        TariffList list = new TariffList();
        Parser parser1 = new SaxParser();
        Parser parser2 = new DomParser();
        Parser parser3 = new StaxParser();
        Serializator ser1 = new ToHtmlSerializator();
        Serializator ser2 = new ToHtmlSerializator();
        DocumentValidator val = new DocumentValidator();

        //If XML document is valid then parse and serialize them
        if (val.validate()) {
            parser1.execute(list);
            System.out.println("SAX Parser: ");
            testInitialization(list);//Shows entity object fields
            list = new TariffList();
            parser2.execute(list);
            System.out.println("Entity resetted. DOM Parser: ");
            testInitialization(list);
            list = new TariffList();
            parser3.execute(list);
            System.out.println("Entity resetted. StAX Parser: ");
            testInitialization(list);
            //Serializatiors
            if (ser1.excute()) {//To another XML
                System.out.println("XML with another root element has been succesfully created");
            } else {
                System.out.println("Document serialization error, exiting");
            }
            if (ser2.excute()) {//To HTML
                System.out.println("HTML has been succesfully created");
            } else {
                System.out.println("Document serialization error, exiting");
            }
        } else {
            System.out.println("Document validation error, exiting");
        }
    }

    /**
     * Shows entity object fields
     *
     * @param list current Tariff objects container
     */
    private static void testInitialization(TariffList list) {
        String curKey;
        Iterator iter;
        iter = list.getKeySet().iterator();
        while (iter.hasNext()) {
            curKey = (String) iter.next();
            System.out.println("Name:" + list.getList(curKey).getName() + " "
                    + "Operator: " + list.getList(curKey).getOperators() + " "
                    + "Payroll: " + list.getList(curKey).getPayroll() + " "
                    + "Inside: " + list.getList(curKey).getCallPriceInsideNetwork() + " "
                    + "Outside: " + list.getList(curKey).getCallPriceOutsideNetwork() + " "
                    + "Another: " + list.getList(curKey).getCallPriceToAnotherNetwork() + " "
                    + "Sms: " + list.getList(curKey).getSmsPrice() + " "
                    + "Discount: " + list.getList(curKey).getDiscountNumbers() + " "
                    + "Tarif: " + list.getList(curKey).getTarrification() + " "
                    + "Tariff price: " + list.getList(curKey).getTariffPrice());
        }
    }
}
