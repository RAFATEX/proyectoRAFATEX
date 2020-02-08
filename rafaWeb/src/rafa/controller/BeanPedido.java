package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.Pedido;
import rafa.model.manager.ManagerPedido;

@Named
@SessionScoped

public class BeanPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPedido managerPedido;
	private List<Pedido> ListaPedido;
	private Pedido pedido;
	//private int idCliente;
	private int idAgencia;
	private boolean panelColapsado;
	private Integer idCliente;
	private String apellidoCliente;
	private String cedulaCliente;
    private String celular;
    private String contrasenia;
	private String correoCliente;
	private String direccionCliente;
	private String nombreCliente;
	private String telefono;
	//private Number rol;
	//private Rol rolselec;
	@PostConstruct
	public void inicializar() {
		ListaPedido=managerPedido.findAllPedido();
		pedido=new Pedido();
		panelColapsado=true;
	}
	public void actionListenerInsertarPedido() {
		try {
			
			managerPedido.insertarPedido(pedido,idCliente,idAgencia);
			ListaPedido=managerPedido.findAllPedido();
			pedido=new Pedido();
			JSFUtil.crearMensajeINFO("Empleado Ingresado");;
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public List<Pedido> getListaPedido() {
		return ListaPedido;
	}
	public void setListaPedido(List<Pedido> listaPedido) {
		ListaPedido = listaPedido;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getIdAgencia() {
		return idAgencia;
	}
	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
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
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
