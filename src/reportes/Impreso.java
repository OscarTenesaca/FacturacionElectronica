package reportes;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author david
 */
public class Impreso {

    private final static Logger LOGGER = Logger.getLogger("mx.hash.impresionpdf.Impresor");

//
    public void imprimir(String nFac) throws PrinterException, IOException {
    	
    	String nombrepdf="FAC"+nFac+".pdf";
    	
        // Indicamos el nombre del archivo Pdf que deseamos imprimir
        PDDocument document = PDDocument.load(new File("/Users/oscartenesaca/eclipse-workspace/FacturaElectronica/ReporteFacturasPDF/"+nombrepdf));

        PrinterJob job = PrinterJob.getPrinterJob();

        LOGGER.log(Level.INFO, "Mostrando el dialogo de impresion");
        if (job.printDialog() == true) {            
            job.setPageable(new PDFPageable(document));

            LOGGER.log(Level.INFO, "Imprimiendo documento");
            job.print();
        }
    }
}