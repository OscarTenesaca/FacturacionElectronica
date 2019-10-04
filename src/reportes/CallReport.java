package reportes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import control.ConexionBD;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CallReport {

	private Connection con;
	/**
	 * 
	 * @param reportName pasa el parametro de una clase
	 * @param parametros la segundo parametro de una clase
	 * @return una variable
	 */
	public JasperPrint generateReport(String reportName, HashMap parametros) throws SQLException {
        try {
            if (con == null) {
                try {
                    con = ConexionBD.getConnection();
  
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            JasperPrint jasperPrint = null;
            if (parametros == null) {
            	parametros = new HashMap();
            }
            try {            	
            	String path = "" + reportName + ".jasper";
                jasperPrint = JasperFillManager.fillReport(path, parametros, con);
            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally{
        	ConexionBD.close(con);
        	
        }
    }
	
}
