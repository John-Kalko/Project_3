/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.serializators;

import java.io.File;

/**
 * Creates HTML file from XML file according to the SXL file.
 *
 * @author Kalko I.
 */
public class ToHtmlSerializator extends Serializator {
    //Source file.

    private static final String source = "src" + File.separator + "XMLFiles"
            + File.separator + "Tariff.xml";
    //Result file.
    private static final String target = "src" + File.separator + "XMLFiles"
            + File.separator + "NewTariff.html";
    //XSL file
    private static final String xsl = "src" + File.separator + "XMLFiles"
            + File.separator + "XmlToHtml.xsl";

    /**
     * Creates HTML file from the valid XML according to the XSL file.
     *
     * @return true if successful.
     */
    @Override
    public boolean excute() {
        return super.transform(source, target, xsl);
    }
}
