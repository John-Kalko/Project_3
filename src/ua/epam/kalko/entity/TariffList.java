/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.entity;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Container for Tariff object; Imlemented as TreeMap with Comparator.
 *
 * @author Калько Евгений
 */
public class TariffList {

    /* Container */
    private Map<String, Tariff> listOfTariffs;

    /**
     * Initializes container object.
     */
    public TariffList() {
        this.listOfTariffs = new TreeMap<>(new Sorter());
    }

    /**
     * Returnes Tariff object by its key [Tariff_name + Operator]; Returnes null
     * if no matches.
     *
     * @param key String.
     * @return Tariff.
     */
    public Tariff getList(String key) {
        if (this.listOfTariffs.containsKey(key)) {
            return this.listOfTariffs.get(key);
        }
        return null;
    }

    /**
     * Returnes all existing keys existing in container.
     *
     * @return Set<String>.
     */
    public Set<String> getKeySet() {
        return this.listOfTariffs.keySet();
    }

    /**
     * Adds Tariff to the container.
     *
     * @param tariff Tariff.
     */
    public void setTariff(Tariff tariff) {
        String tariffName = tariff.getName();
        String tariffOperator = tariff.getOperators().toString();
        this.listOfTariffs.put((tariffOperator + "." + tariffName), tariff);
    }

    /**
     * Sorting object.
     */
    private static class Sorter implements Comparator {

        /* Initializes object */
        public Sorter() {
        }

        /**
         * Can be called only by TariffList TreeMap object during the adding
         * Tariff object.
         *
         * @param o1
         * @param o2
         * @return int.
         */
        @Override
        public int compare(Object o1, Object o2) {
            if (o1 == 0 || o2 == 0) {
                return 1;
            }
            //Compares by two strings: tariff_name and operator_name
            String s1 = String.valueOf(o1);
            String s2 = String.valueOf(o2);
            int a1 = s1.indexOf(".", 0);
            int a2 = s1.indexOf(".", 0);
            String f11 = s1.substring(0, a1);
            String f12 = s1.substring(a1 + 1, s1.length());
            String f21 = s2.substring(0, a2);
            String f22 = s2.substring(a2 + 1, s2.length());

            if (f11.equals(f21)) {//If operator names are equals
                return f12.compareToIgnoreCase(f22);
            } else {
                return f11.compareToIgnoreCase(f21);
            }
        }
    }
}
