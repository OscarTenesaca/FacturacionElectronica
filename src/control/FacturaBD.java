package control;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.ModFacCabecera;
import modelo.ModProducto;
import modelo.ModProveedor;


public class FacturaBD {

	

	
	
	

	


	public int NextCodFac() {
	
			
		Connection c = null;
			String sql="SELECT  count(*)"
					+" FROM fac_cabecera";		
			try {
				   c=ConexionBD.getConnection();
					PreparedStatement rs=c.prepareStatement(sql);
			   
			    ResultSet ps=rs.executeQuery();
			  
			    while(ps.next())
			    {
			
			  int valor=ps.getInt(1);
			 
			  System.out.println(valor+"este es el valor actual");
			 return valor+1;
				  
			    }
			} catch (Exception e) {
			   // e.printStackTrace();
			} finally {
				ConexionBD.close(c);
			}
			JOptionPane.showMessageDialog(null, "no existe dato");	
			return 0;
		}
	
	
	
	
	
	
	
public void Insertar(ModFacCabecera factura) {
		
		Connection c=null;
        
		 String query = "INSERT INTO fac_cabecera (Num_Factura, Id_empleado, Id_Cliente,  Descuento,  Sub_Total,Total_Descuento, Iva, Total, Forma_Pago, Num_Caja) values (?,?,?,?,?,?,?,?,?,?)";
	    
		
		
	       
	       try {
	    	   
	    	   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(query);
				
				rs.setString	(1, factura.getNum_factura());
				rs.setInt    	(2, factura.getId_empleado());
	            rs.setInt   	(3, factura.getId_cliente());
	            rs.setInt   	(4, factura.getDescuento());
	            rs.setDouble   	(5, factura.getSubTotal());
	            rs.setDouble	(6, factura.getTotalDescuento());
	            rs.setDouble   	(7, factura.getIva());
	            rs.setDouble    (8, factura.getTotal());
	            rs.setInt		(9, factura.getForma_pago());
	            rs.setInt		(10, factura.getNum_caja());
				
				
		            
				
				
	            // Indicamos que comience la actualizaci�n de la tabla en nuestra base de datos
	            rs.executeUpdate();
	        	JOptionPane.showMessageDialog(null, "Operacion realizada correctamente");
	            // Cerramos las conexiones, en orden inverso a su apertura
	    

	}catch(Exception e){
		e.printStackTrace();
		
	}finally{
		ConexionBD.close(c);
	}	    
    
	       

	
}
public void update(ModFacCabecera factura) {
	Connection c=null;

	String query = "update  fac_cabecera  set  cedula=?,Id_Cliente=?, Fecha_Emision=?, Descuento=?,  Sub_Total=?,idTipoDocumento=?,Total=?  where idPersona=? ";

	try {

		c=ConexionBD.getConnection();
		PreparedStatement rs=c.prepareStatement(query);
	System.out.println("llego aqui");
		/*rs.setString    (1, factura.getId_empleado());
        rs.setString    (2, factura.getNombre());
        rs.setString    (3, factura.getApellido());
        rs.setString   	(4, factura.getDescuento());
        rs.setString   	(5, factura.getSub_Total());
        rs.setInt    	(6, factura.getTipoDocumento());
        rs.setString	(7, factura.getTotal());
    	rs.setInt       (8, factura.getNum_Factura());
		*/
    	System.out.println("llego aqui*2");
		

		// Indicamos que comience la actualizaci�n de la tabla en nuestra base de datos
		rs.executeUpdate();
		System.out.println("llego aqui*3");
		JOptionPane.showMessageDialog(null, "Operaci�n realizada correctamente");
		// Cerramos las conexiones, en orden inverso a su apertura


	}catch(Exception e){
		//e.printStackTrace();

	}finally{
		ConexionBD.close(c);
	}	    


	


}

public ModFacCabecera SelectId_empleado(String cedula) {
	
	Connection c = null;
		String sql="SELECT  * "
				+" FROM fac_cabecera"
				+" WHERE cedula = ?";
	
		try {
			   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(sql);
		    rs.setString(1, cedula);
		    ResultSet ps=rs.executeQuery();
		  
		    while(ps.next())
		    {
		    	
		  ModFacCabecera mp= new ModFacCabecera();
		  /*  	
		  mp.setNum_Factura(ps.getInt("Num_Factura"));
		  mp.setId_empleado(ps.getString("Id_empleado"));
		  mp.setNombre(ps.getString("Id_Cliente"));
		  mp.setApellido(ps.getString("Fecha_Emision"));
		  //System.out.println(ps.getString("Sub_TotalDos"));
		  mp.setDescuento(ps.getString("Descuento"));
		 // System.out.println(ps.getString("Sub_TotalDos"+"pasooo"));
		  mp.setSub_Total(ps.getString("Sub_Total"));
		  mp.setTipoDocumento(ps.getInt("IdtipoDocumento"));
		  mp.setTotal(ps.getString("Total"));
		 */
		 return mp;
			  
		    }
		} catch (Exception e) {
		   // e.printStackTrace();
		} finally {
			ConexionBD.close(c);
		}
		
		JOptionPane.showMessageDialog(null, "no existe dato");	
		return null;

}

public List<ModFacCabecera> listar(){

	
		List<ModFacCabecera> listProveedor= new ArrayList<ModFacCabecera>();             
		String sql="SELECT  *  FROM fac_cabecera";
				
				
		System.out.println(sql);
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
		    PreparedStatement ps= con.prepareStatement(sql);
		   
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		       ModFacCabecera mp=new ModFacCabecera();
		       
		   /*    
		       mp.setNum_Factura(rs.getInt("Num_Factura"));
				  mp.setId_empleado(rs.getString("Id_empleado"));
				  mp.setNombre(rs.getString("Id_Cliente"));
				  mp.setApellido(rs.getString("Fecha_Emision"));
				  //System.out.println(ps.getString("Sub_TotalDos"));
				  mp.setDescuento(rs.getString("Descuento"));
				 // System.out.println(ps.getString("Sub_TotalDos"+"pasooo"));
				  mp.setSub_Total(rs.getString("Sub_Total"));
				  mp.setTipoDocumento(rs.getInt("IdtipoDocumento"));
				  mp.setTotal(rs.getString("Total"));
		      
		     */  
		        
		        listProveedor.add(mp);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return listProveedor;
	}



public List<ModFacCabecera> ListarSelectcedula(String nombre) {

	
	List<ModFacCabecera> listCliente= new ArrayList<ModFacCabecera>();   
	Connection con = null;
		String sql="SELECT  * "
				+" FROM fac_cabecera"
				+" WHERE Id_Cliente  LIKE ?";
	
	
		try {
			   con=ConexionBD.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
		    ps.setString(1, "%"+nombre+"%");
		    System.out.println("descri"+nombre);
		    ResultSet rs=ps.executeQuery();
		  
		    while(rs.next())
		    {
		    	   ModFacCabecera mp=new ModFacCabecera();
			       
			       /*
		    	   mp.setNum_Factura(rs.getInt("Num_Factura"));
					  mp.setId_empleado(rs.getString("Id_empleado"));
					  mp.setNombre(rs.getString("Id_Cliente"));
					  mp.setApellido(rs.getString("Fecha_Emision"));
					  //System.out.println(ps.getString("Sub_TotalDos"));
					  mp.setDescuento(rs.getString("Descuento"));
					 // System.out.println(ps.getString("Sub_TotalDos"+"pasooo"));
					  mp.setSub_Total(rs.getString("Sub_Total"));
					  mp.setTipoDocumento(rs.getInt("IdtipoDocumento"));
					  mp.setTotal(rs.getString("Total"));
		  
		  */
		  listCliente.add(mp);
		
		  
		 
			  
		    }
		    return listCliente;
		} catch (Exception e) {
			return null;
		   // e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		
	
}

}




		


	
	
	
	
	
	





