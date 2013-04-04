/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.serializators;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Main class for all XML serializators.
 * @author Kalko I.
 */
public abstract class Serializator {

    /**
     * Each non abstract child must implement it.
     * @return 
     */
    public abstract boolean excute();

    /**
     * Transorms source XML file to result .* file according to xsl.
     * @param source
     * @param result
     * @param xsl
     * @return true if successful.
     */
    protected boolean transform(String source, String result, String xsl) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer trans = factory.newTransformer(new StreamSource(xsl));
            trans.transform(new StreamSource(source), new StreamResult(result));
            return true;
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Serializator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Serializator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
