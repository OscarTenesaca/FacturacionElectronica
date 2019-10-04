package control;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.ModCliente;
import modelo.ModProducto;
import modelo.ModProveedor;


public class ClienteBD {

	

	
	
	

	


	public int NextCodProv() {
	
			
		Connection c = null;
			String sql="SELECT  count(*)"
					+" FROM persona";		
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
			return 1;
		}
	
public void Insertar(ModCliente cliente) {
		
		
		Connection c=null;
        
		 String query = "INSERT INTO persona (IdPersona, Cedula,nombres, apellidos, Direccion,  Telefono,IdtipoDocumento,email) values (?, ?,?, ?, ?, ?,?,?)";
	    
		
		
	       
	       try {
	    	   
	    	   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(query);
				rs.setInt       (1, cliente.getIdPersona());
				rs.setString    (2, cliente.getCedula());
	            rs.setString    (3, cliente.getNombre());
	            rs.setString    (4, cliente.getApellido());
	            rs.setString   	(5, cliente.getDireccion());
	            rs.setString   	(6, cliente.getTelefono());
	            rs.setInt    	(7, cliente.getTipoDocumento());
	            rs.setString	(8, cliente.getEmail());
				
				
		            
				
				
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
public void update(ModCliente cliente) {
	Connection c=null;

	String query = "update  persona  set  cedula=?,nombres=?, apellidos=?, direccion=?,  telefono=?,idTipoDocumento=?,email=?  where idPersona=? ";

	try {

		c=ConexionBD.getConnection();
		PreparedStatement rs=c.prepareStatement(query);
	System.out.println("llego aqui");
		rs.setString    (1, cliente.getCedula());
        rs.setString    (2, cliente.getNombre());
        rs.setString    (3, cliente.getApellido());
        rs.setString   	(4, cliente.getDireccion());
        rs.setString   	(5, cliente.getTelefono());
        rs.setInt    	(6, cliente.getTipoDocumento());
        rs.setString	(7, cliente.getEmail());
    	rs.setInt       (8, cliente.getIdPersona());
		
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

public ModCliente SelectCedula(String cedula) {
	
	Connection c = null;
		String sql="SELECT  * "
				+" FROM persona"
				+" WHERE cedula = ?";
	
		try {
			   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(sql);
		    rs.setString(1, cedula);
		    ResultSet ps=rs.executeQuery();
		  
		    while(ps.next())
		    {
		    	
		  ModCliente mp= new ModCliente();
		    	
		  mp.setIdPersona(ps.getInt("IdPersona"));
		  mp.setCedula(ps.getString("Cedula"));
		  mp.setNombre(ps.getString("nombres"));
		  mp.setApellido(ps.getString("apellidos"));
		  //System.out.println(ps.getString("TelefonoDos"));
		  mp.setDireccion(ps.getString("Direccion"));
		 // System.out.println(ps.getString("TelefonoDos"+"pasooo"));
		  mp.setTelefono(ps.getString("Telefono"));
		  mp.setTipoDocumento(ps.getInt("IdtipoDocumento"));
		  mp.setEmail(ps.getString("email"));
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

public List<ModCliente> listar(){

	
		List<ModCliente> listProveedor= new ArrayList<ModCliente>();             
		String sql="SELECT  *  FROM persona";
				
				
		System.out.println(sql);
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
		    PreparedStatement ps= con.prepareStatement(sql);
		   
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		       ModCliente mp=new ModCliente();
		       
		       
		       mp.setIdPersona(rs.getInt("IdPersona"));
				  mp.setCedula(rs.getString("Cedula"));
				  mp.setNombre(rs.getString("nombres"));
				  mp.setApellido(rs.getString("apellidos"));
				  //System.out.println(ps.getString("TelefonoDos"));
				  mp.setDireccion(rs.getString("Direccion"));
				 // System.out.println(ps.getString("TelefonoDos"+"pasooo"));
				  mp.setTelefono(rs.getString("Telefono"));
				  mp.setTipoDocumento(rs.getInt("IdtipoDocumento"));
				  mp.setEmail(rs.getString("email"));
		      
		       
		        
		        listProveedor.add(mp);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return listProveedor;
	}



public List<ModCliente> ListarSelectcedula(String nombre) {

	
	List<ModCliente> listCliente= new ArrayList<ModCliente>();   
	Connection con = null;
		String sql="SELECT  * "
				+" FROM persona"
				+" WHERE nombres  LIKE ?";
	
	
		try {
			   con=ConexionBD.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
		    ps.setString(1, "%"+nombre+"%");
		    System.out.println("descri"+nombre);
		    ResultSet rs=ps.executeQuery();
		  
		    while(rs.next())
		    {
		    	   ModCliente mp=new ModCliente();
			       
			       
		    	   mp.setIdPersona(rs.getInt("IdPersona"));
					  mp.setCedula(rs.getString("Cedula"));
					  mp.setNombre(rs.getString("nombres"));
					  mp.setApellido(rs.getString("apellidos"));
					  //System.out.println(ps.getString("TelefonoDos"));
					  mp.setDireccion(rs.getString("Direccion"));
					 // System.out.println(ps.getString("TelefonoDos"+"pasooo"));
					  mp.setTelefono(rs.getString("Telefono"));
					  mp.setTipoDocumento(rs.getInt("IdtipoDocumento"));
					  mp.setEmail(rs.getString("email"));
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




		


	
	
	
	
	
	





