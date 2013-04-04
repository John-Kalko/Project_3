/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.entity;

import ua.epam.kalko.parsers.Parser.Operators;
import ua.epam.kalko.parsers.Parser.Tarrification;

/**
 * Entity class, which is used for storing parsed data from valid XML file.
 *
 * @author Kalko I.
 */
public class Tariff {
    
    //XML fields
    private String name;
    private Operators operators;
    private double payroll;
    private double callPriceInsideNetwork;
    private double callPriceToAnotherNetwork;
    private double callPriceOutsideNetwork;
    private double smsPrice;
    private int discountNumbers;
    private Tarrification tarrification;
    private double tariffPrice;

    /**
     * Initializes Tariff object.
     *
     * @param name
     * @param operator
     * @param payroll
     * @param callPriceInsideNetwork
     * @param callPriceToAnotherNetwork
     * @param callPriceOutsideNetwork
     * @param smsPrice
     * @param discountNumbers
     * @param tarrification
     * @param tariffPrice
     */
    public Tariff(String name, Operators operator, double payroll,
            double callPriceInsideNetwork, double callPriceToAnotherNetwork,
            double callPriceOutsideNetwork, double smsPrice, int discountNumbers,
            Tarrification tarrification, double tariffPrice) {
        this.name = name;
        this.payroll = payroll;
        this.callPriceInsideNetwork = callPriceInsideNetwork;
        this.callPriceToAnotherNetwork = callPriceToAnotherNetwork;
        this.callPriceOutsideNetwork = callPriceOutsideNetwork;
        this.smsPrice = smsPrice;
        this.discountNumbers = discountNumbers;
        this.tariffPrice = tariffPrice;
        this.tarrification = tarrification;
        this.operators = operator;
    }

    /**
     * Returns tariff name
     *
     * @return tariff name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns operator name.
     *
     * @return operator name
     */
    public Operators getOperators() {
        return operators;
    }

    /**
     * Returns mothly payroll.
     *
     * @return monthly payroll
     */
    public double getPayroll() {
        return payroll;
    }

    /**
     * Returns price inside network.
     *
     * @return price
     */
    public double getCallPriceInsideNetwork() {
        return callPriceInsideNetwork;
    }

    /**
     * Returns price to annother network.
     *
     * @return price
     */
    public double getCallPriceToAnotherNetwork() {
        return callPriceToAnotherNetwork;
    }

    /**
     * Returns price outside network.
     *
     * @return price
     */
    public double getCallPriceOutsideNetwork() {
        return callPriceOutsideNetwork;
    }

    /**
     * Returns price for the SMS.
     *
     * @return price
     */
    public double getSmsPrice() {
        return smsPrice;
    }

    /**
     * Returns quontity of discounts number.
     *
     * @return number
     */
    public int getDiscountNumbers() {
        return discountNumbers;
    }

    /**
     * Returns tariffication plan.
     *
     * @return price
     */
    public Tarrification getTarrification() {
        return this.tarrification;
    }

    /**
     * Returns started tariff price.
     *
     * @return price
     */
    public double getTariffPrice() {
        return tariffPrice;
    }
}
