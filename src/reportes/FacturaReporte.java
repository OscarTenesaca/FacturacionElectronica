package reportes;


import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.pdfbox.pdmodel.PDDocument;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

public class FacturaReporte extends JFrame{
	
	

	private JRViewer viewer;
	
	//private JTextField txtEdadMinima;
	
	public FacturaReporte(String nFac){
		
		try{
			getContentPane().remove(viewer);
		}catch(Exception e){}
		
		
		HashMap parametros = new HashMap();
		parametros.put("Num_Factura", nFac);
		System.out.println(nFac);
		
		CallReport cr = new CallReport();
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = cr.generateReport("/Users/oscartenesaca/eclipse-workspace/FacturaElectronica/ReportesPDF/Factura", parametros);
			
			////
			
			JRPdfExporter exp = new JRPdfExporter();
			exp.setExporterInput(new SimpleExporterInput(jasperPrint));
			
			exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteFacturasPDF/FAC"+nFac+".pdf"));
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			exp.setConfiguration(conf);
			exp.exportReport();
			
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		viewer = new JRViewer(jasperPrint);
		getContentPane().add(viewer, BorderLayout.CENTER);
		


	}
	
	
	
	
	

	
	
}
