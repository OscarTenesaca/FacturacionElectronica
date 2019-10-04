package modelo;



public class ModCliente {
	
int idPersona,TipoDocumento;	
String Cedula,Email,Nombre,Apellido,Direccion,Telefono;
public int getIdPersona() {
	return idPersona;
}
public void setIdPersona(int idPersona) {
	this.idPersona = idPersona;
}
public int getTipoDocumento() {
	return TipoDocumento;
}
public void setTipoDocumento(int tipoDocumento) {
	TipoDocumento = tipoDocumento;
}
public String getCedula() {
	return Cedula;
}
public void setCedula(String cedula) {
	Cedula = cedula;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getApellido() {
	return Apellido;
}
public void setApellido(String apellido) {
	Apellido = apellido;
}
public String getDireccion() {
	return Direccion;
}
public void setDireccion(String direccion) {
	Direccion = direccion;
}
public String getTelefono() {
	return Telefono;
}
public void setTelefono(String telefono) {
	Telefono = telefono;
}
@Override
public String toString() {
	return "ModCliente [idPersona=" + idPersona + ", TipoDocumento=" + TipoDocumento + ", Cedula=" + Cedula + ", Email="
			+ Email + ", Nombre=" + Nombre + ", Apellido=" + Apellido + ", Direccion=" + Direccion + ", Telefono="
			+ Telefono + "]";
}
}