/*
 * Kalko I. 0.1 14.12.12
 */
package ua.epam.kalko.parsers;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 * Simple xml document validator, which just returns true if document validation
 * process succeeded and false if not.
 *
 * @author Kalko Ievgen
 */
public class DocumentValidator {

    /* Path  to SXD file */
    private String XSD_PATH = "src" + File.separator + "XMLFiles" + File.separator
            + "Tariff.xsd";
    /* Namespace for validation */
    private String validationURI = "http://www.w3.org/2001/XMLSchema";
    /* Indicates error(s) */
    static int errorCounter = 0;

    /**
     * Provides XML file validations with the Tariff.XSD.
     *
     * @return true if valid.
     */
    public boolean validate() {
        SchemaFactory factory = SchemaFactory.newInstance(this.validationURI);
        try {
            Schema schema = factory.newSchema();
            Validator validator = schema.newValidator();
            Source source = new StreamSource(Parser.pathToXML);
   
            validator.validate(source);
            return true;
        } catch (SAXException ex) {
            System.err.println("Validation error in document " + Parser.pathToXML);
            Logger.getLogger(DocumentValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Returnes XSD path.
     *
     * @return XSD path.
     */
    public String getXSD_PATH() {
        return XSD_PATH;
    }

    /**
     * Returnes validation namespace.
     *
     * @return String.
     */
    public String getValidationURI() {
        return validationURI;
    }
}
