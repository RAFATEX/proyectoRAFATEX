package rafa.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;




import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import rafa.model.entities.Cliente;
import rafa.model.manager.ManagerCliente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		listaporClientes = mCliente.FindAllCLientes();
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
		//listaClientes = mCliente.FindAllCLientes();
		listaporClientes = mCliente.FindAllCLientes();
		for (Cliente c:listaporClientes) 
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
	
	public String actionReporte(){
		Map<String,Object> parametros=new HashMap<String,Object>();
		/*parametros.put("p_titulo_principal",p_titulo_principal);
		parametros.put("p_titulo",p_titulo);*/
		FacesContext context=FacesContext.getCurrentInstance();
		ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		String ruta=servletContext.getRealPath("Administrador/clientes.jasper");
		System.out.println(ruta);
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=clientes.pdf");
		response.setContentType("application/pdf");
		try {
		Class.forName("org.postgresql.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rafatexdb","stalin", "postgres");
		JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		context.getApplication().getStateManager(). saveView( context);
		System.out.println("reporte generado.");
		context.responseComplete();
		} catch (Exception e) {
		JSFUtil.crearMensajeERROR(e.getMessage());
		e.printStackTrace();
		}
		return "";
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
