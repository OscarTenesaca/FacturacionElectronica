package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.ModEmpleado;


public class EmpleadoBD {

public boolean SelectUser(String user,String pass) {
	
	Connection c = null;
		String sql="SELECT  * "
				+" FROM empleado"
				+" WHERE User = ? && Password=? ";
	
		try {
			   c=ConexionBD.getConnection();
				PreparedStatement ps=c.prepareStatement(sql);
		    ps.setString(1, user);
		    ps.setString(2, pass);
		    ResultSet rs=ps.executeQuery();
		  
		    while(rs.next())
		    {
		    	
		  
	        return true;
			  
		    }
		} catch (Exception e) {
		   // e.printStackTrace();
		} finally {
			ConexionBD.close(c);
		}
		JOptionPane.showMessageDialog(null, "no existe dato");	
		return false;

}

	

	public int NextCodProv() {
	
			
		Connection c = null;
			String sql="SELECT  count(*)"
					+" FROM empleado";		
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

	public void Insertar(ModEmpleado empleado) {
		Connection c=null;
        
		 String query = "INSERT INTO empleado (IdEmpleado, Cedula,nombre, apellido, User,  Password,Cargo) values (?, ?,?,?,?,?,?)";
	    
		
		
	       
	       try {
	    	   
	    	   c=ConexionBD.getConnection();
				PreparedStatement rs=c.prepareStatement(query);
				rs.setInt       (1, empleado.getId());
				rs.setString    (2, empleado.getCedula());
	            rs.setString    (3, empleado.getNombre());
	            rs.setString    (4, empleado.getApellido());
	            rs.setString   	(5, empleado.getUser());
	            rs.setString   	(6, empleado.getPassword());
	            
	            rs.setString   	(7, empleado.getCargo());
	            
				
				
		            
				
				
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

	
	

public List<ModEmpleado> listar(){

	
		List<ModEmpleado> listEmpleado= new ArrayList<ModEmpleado>();             
		String sql="SELECT  *  FROM empleado";
				
				
		System.out.println(sql);
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
		    PreparedStatement ps= con.prepareStatement(sql);
		   
		    ResultSet rs=ps.executeQuery();
		    while(rs.next())
		    {
		       ModEmpleado mp=new ModEmpleado();
		       
		       mp.setId(rs.getInt("idEmpleado"));
		       mp.setCedula(rs.getString("Cedula"));
		       mp.setNombre(rs.getString("Nombre"));
		       mp.setApellido(rs.getString("Apellido"));
		       mp.setUser(rs.getString("User"));
		       mp.setPassword(rs.getString("Password"));
		       mp.setCargo(rs.getString("Cargo"));
		       
		     
		      
		       
		        
		        listEmpleado.add(mp);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		return listEmpleado;
	}



public List<ModEmpleado> ListarSelectNombre(String nombre) {

	
	List<ModEmpleado> listEmpleado= new ArrayList<ModEmpleado>();   
	Connection con = null;
		String sql="SELECT  * "
				+" FROM empleado"
				+" WHERE nombre  LIKE ?";
	
	
		try {
			   con=ConexionBD.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
		    ps.setString(1, "%"+nombre+"%");
		    System.out.println("descri"+nombre);
		    ResultSet rs=ps.executeQuery();
		  
		    while(rs.next())
		    {
		    	   ModEmpleado mp=new ModEmpleado();
			       
		    	   mp.setId(rs.getInt("idEmpleado"));
			       mp.setCedula(rs.getString("Cedula"));
			       mp.setNombre(rs.getString("Nombre"));
			       mp.setApellido(rs.getString("Apellido"));
			       mp.setUser(rs.getString("User"));
			       mp.setPassword(rs.getString("Password"));
			       mp.setCargo(rs.getString("Cargo"));
			       
		  listEmpleado.add(mp);
		
		  
		 
			  
		    }
		    return listEmpleado;
		} catch (Exception e) {
			return null;
		   // e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}
		
	
}
public ModEmpleado SelectCedula(String cedula) {
	
	Connection c = null;
		String sql="SELECT  * "
				+" FROM empleado"
				+" WHERE cedula = ?";
	
		try {
			   c=ConexionBD.getConnection();
				PreparedStatement ps=c.prepareStatement(sql);
		    ps.setString(1, cedula);
		    ResultSet rs=ps.executeQuery();
		  
		    while(rs.next())
		    {
		    	
		  ModEmpleado mp= new ModEmpleado();
		  mp.setId(rs.getInt("idEmpleado"));
	       mp.setCedula(rs.getString("Cedula"));
	       mp.setNombre(rs.getString("Nombre"));
	       mp.setApellido(rs.getString("Apellido"));
	       mp.setUser(rs.getString("User"));
	       mp.setPassword(rs.getString("Password"));
	       mp.setCargo(rs.getString("Cargo"));
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

public void update(ModEmpleado empleado) {
	Connection c=null;

	String query = "update  empleado  set  Cedula=?,nombre=?, apellido=?, User=?,  Password=?,Cargo=? where IdEmpleado=? ";

	try {

		c=ConexionBD.getConnection();
		PreparedStatement rs=c.prepareStatement(query);
	System.out.println("llego aqui");
		
		rs.setString    (1, empleado.getCedula());
        rs.setString    (2, empleado.getNombre());
        rs.setString    (3, empleado.getApellido());
        
        rs.setString   	(4, empleado.getUser());
        rs.setString   	(5, empleado.getPassword());
        rs.setString	(6, empleado.getCargo());
    	rs.setInt       (7, empleado.getId());
		
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


	
}
