/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.serializators;

import java.io.File;

/**
 * Creates XML file from another XML file with another root element.
 *
 * @author Kalko I.
 */
public class ChangeRootElementSerializator extends Serializator {

    //Source XML.
    private static final String source = "src" + File.separator + "XMLFiles"
            + File.separator + "Tariff.xml";
    //Resulted xml.
    private static final String target = "src" + File.separator + "XMLFiles"
            + File.separator + "NewTariff.xml";
    //XSL file.
    private static final String xsl = "src" + File.separator + "XMLFiles"
            + File.separator + "XmlToXml.xsl";

    /**
     * Creates XML file from another XML file with another root element.
     *
     * @return true if successful.
     */
    @Override
    public boolean excute() {
        return super.transform(source, target, xsl);
    }
}
