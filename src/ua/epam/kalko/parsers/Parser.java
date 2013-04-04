/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.parsers;

import java.io.File;
import ua.epam.kalko.entity.Tariff;
import ua.epam.kalko.entity.TariffList;

/**
 * Main class of all parsers.
 *
 * @author Kalko I.
 */
public abstract class Parser {

    /* Stores path to the sourse XML file */
    public static final String pathToXML = "src" + File.separator + "XMLFiles"
            + File.separator + "Tariff.xml";

    /**
     * Tariff initialization constants.
     */
    public static enum Operators {

        MTS, KYIVSAR, LIFE
    };

    /**
     * Tariff initialization constants.
     */
    public static enum Tarrification {

        FROM_FIRST_MINUTE, FROM_FIRST_SECOND, FROM_FIFTH_SECOND
    };
    //Used for Tariff object initialization
    private TariffList list;
    protected String name;
    protected Operators operators;
    protected double payroll;
    protected double callPriceInsideNetwork;
    protected double callPriceToAnotherNetwork;
    protected double callPriceOutsideNetwork;
    protected double smsPrice;
    protected int discountNumbers;
    protected Tarrification tarrification;
    protected double tariffPrice;

    /**
     * Subclasses must implement its own version of this method.
     *
     * @param target TariffList
     * @return true if successful.
     */
    public abstract boolean execute(TariffList target);

    /**
     * Clear all initialization data.
     */
    protected void clearParameters() {
        operators = null;
        payroll = 0;
        callPriceInsideNetwork = 0;
        callPriceToAnotherNetwork = 0;
        callPriceOutsideNetwork = 0;
        smsPrice = 0;
        discountNumbers = 0;
        tarrification = null;
        tariffPrice = 0;
    }

    /**
     * Initializes Tariff and adds it to the TariffList.
     *
     * @param target TariffList.
     */
    protected void initializer(TariffList target) {
        initializeTariffList(target);
        this.list.setTariff(new Tariff(name, operators, payroll,
                callPriceInsideNetwork, callPriceToAnotherNetwork,
                callPriceOutsideNetwork, smsPrice, discountNumbers,
                tarrification, tariffPrice));
        clearParameters();
    }

    /**
     * Checks TariffList for existing.
     *
     * @param target
     */
    private void initializeTariffList(TariffList target) {
        if (target != null) {
            this.list = target;
            return;
        }
        throw new NullPointerException(
                "Empty TariffList was thrown to " + this.getClass().getName());
    }
}
