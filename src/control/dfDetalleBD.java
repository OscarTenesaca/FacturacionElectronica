package control;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Detalle_fac;
import modelo.ModFacCabecera;
import modelo.ModProducto;
import modelo.ModProveedor;


public class dfDetalleBD {

	

	
	
	public void Insertar(Detalle_fac df) {
		
		Connection c=null;
        
		 String query = "INSERT INTO Detalle_Fac (Num_Factura, Id_Producto, cantidad) values (?,?,?)";
	    
		
		
	       
	       try {
	    	   
	    	   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(query);
				
				rs.setString	(1, df.getNumFactura());
				rs.setInt		(2, df.getIdProducto());
				rs.setInt		(3, df.getCantidad());
			
				
		            
				
				
	            // Indicamos que comience la actualizaci�n de la tabla en nuestra base de datos
	            rs.executeUpdate();
	        	
	            System.out.println("guardo detalle");
	            //JOptionPane.showMessageDialog(null, "Operacion realizada correctamente");
	            // Cerramos las conexiones, en orden inverso a su apertura
	    

	}catch(Exception e){
		e.printStackTrace();
		
	}finally{
		ConexionBD.close(c);
	}	    
    
	       

	
}
	}