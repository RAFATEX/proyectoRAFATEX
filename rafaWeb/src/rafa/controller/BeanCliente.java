package rafa.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;

import rafa.model.entities.Cliente;
import rafa.model.manager.ManagerCliente;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCliente implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerCliente mCliente;
	
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private List<Cliente> listaporClientes;
	private Cliente clienteSeleccionado;
	private Cliente clienteLogin;
	
	private Integer idCliente;
	private String apellidoCliente;
	private String cedulaCliente;
	private String celular;
	private String contrasenia; 
	private String correoCliente;
	private String direccionCliente;
	private String nombreCliente;
	private String telefono;

	@PostConstruct
	public void Inicializar()
	{
		cliente = new Cliente();
		listaClientes = mCliente.FindAllCLientes();
	}
	
	public String actionIngresarCliente()
	{
		try {
			mCliente.IngresarCLiente(cliente);
			listaClientes = mCliente.FindAllCLientes();
			cliente = new Cliente();
			JSFUtil.crearMensajeINFO("Se ha registrado exitosamente");
		}catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		return "InicioSesion";
	}
	
	public void actionEliminarCliente(Integer id) {
		mCliente.EliminarCliente(id);
		listaClientes = mCliente.FindAllCLientes();
		JSFUtil.crearMensajeINFO("Cliente eliminado");
	}
	
	public void actionSeleccionarCliente(Cliente cliente) {
		clienteSeleccionado=cliente;
	}
	
	public void actionActualizarCliente() {
		try {
			mCliente.ActualizarCliente(clienteSeleccionado);
			listaClientes=mCliente.FindAllCLientes();
			JSFUtil.crearMensajeINFO("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionLogin() {
		listaClientes = mCliente.FindAllCLientes();
		for (Cliente c:listaClientes) 
		{
			if(c.getCedulaCliente().equals(this.cedulaCliente) && c.getContrasenia().equals(this.contrasenia))
			{
				clienteLogin=c;
			}
		}
		return "catalogo";
	}
	
	public void consultarDatosCliente(){
		for (Cliente c:listaClientes) {
			if (c.getCedulaCliente().equals(this.cedulaCliente)) {
				cliente= new Cliente();
				listaClientes = mCliente.FindAllCLientes();
			}
		}
	}
	
	//------------------- GETTERS AND SETTERS ----------------------------------
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public List<Cliente> getListaporClientes() {
		return listaporClientes;
	}

	public void setListaporClientes(List<Cliente> listaporClientes) {
		this.listaporClientes = listaporClientes;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}
	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Cliente getClienteLogin() {
		return clienteLogin;
	}

	public void setClienteLogin(Cliente clienteLogin) {
		this.clienteLogin = clienteLogin;
	}
}
