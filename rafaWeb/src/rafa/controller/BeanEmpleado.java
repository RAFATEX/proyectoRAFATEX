package rafa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.entities.Cliente;
import rafa.model.entities.Empleado;
import rafa.model.entities.Rol;
import rafa.model.manager.ManagerEmpleado;

@Named
@SessionScoped
public class BeanEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEmpleado managerEmpleado;
	private List<Empleado> ListaEmpleados;
	private Empleado empleado;
	private boolean panelColapsado;
	private Empleado empleadoSeleccionado;
	private int idRol;
	private int idEmpleado;

	private Empleado empleadoLogin;

	private String apellido;
	private String cedula;
	private String celular;
	private String contrasenia;
	private String correo;
	private String direccion;
	private String nombre;
	private String telefono;
	private Rol rol;

	// private Number rol;
	// private Rol rolselec;
	@PostConstruct
	public void inicializar() {
		ListaEmpleados = managerEmpleado.findAllEmpleados();
		empleado = new Empleado();
		panelColapsado = true;
	}

	public void actionListenerInsertarEmpleado() {
		try {

			managerEmpleado.insertarEmpleado(empleado, idRol);
			ListaEmpleados = managerEmpleado.findAllEmpleados();
			empleado = new Empleado();
			JSFUtil.crearMensajeINFO("Empleado Ingresado");
			;
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarEmpleado(Integer idempleado) {
		managerEmpleado.eliminarEmpleado(idempleado);
		ListaEmpleados = managerEmpleado.findAllEmpleados();
		JSFUtil.crearMensajeINFO("Empleado eliminado");
	}

	public void actionListenerSeleccionarEmpleado(Empleado empleado) {
		empleadoSeleccionado = empleado;
	}

	public void actionListenerActualizarEmpleado() {
		try {
			managerEmpleado.actualizarEmpleado(empleadoSeleccionado);
			ListaEmpleados = managerEmpleado.findAllEmpleados();
			JSFUtil.crearMensajeINFO("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionLogin() {
		ListaEmpleados = managerEmpleado.findAllEmpleados();
		for (Empleado e : ListaEmpleados) {
			if (e.getCedula().equals(this.cedula) && e.getContrasenia().equals(this.contrasenia)) {
				empleadoLogin = e;
				
			}
		}
		return "Producto";
	}

	public void actionListenerColapsadoPanel() {
		panelColapsado = !panelColapsado;
	}

	public List<Empleado> getListaEmpleados() {
		return ListaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		ListaEmpleados = listaEmpleados;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public Empleado getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Empleado getEmpleadoLogin() {
		return empleadoLogin;
	}

	public void setEmpleadoLogin(Empleado empleadoLogin) {
		this.empleadoLogin = empleadoLogin;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
