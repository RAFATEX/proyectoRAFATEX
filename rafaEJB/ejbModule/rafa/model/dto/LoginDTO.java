package rafa.model.dto;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;

/**
 * Session Bean implementation class LoginDTO
 */
@Stateless
@LocalBean
public class LoginDTO {
	private String cedulaCliente;
	private String apellidos;
	private String clave;
	private String direccion;
	private String nombres;
	
	
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
}
