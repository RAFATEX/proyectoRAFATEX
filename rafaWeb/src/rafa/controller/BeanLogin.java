package rafa.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import rafa.model.dto.LoginDTO;
import rafa.model.manager.ManagerAuditoria;
import rafa.model.manager.ManagerSeguridad;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cedula;
	private String contrasenia;
	@EJB 
	private ManagerSeguridad managerSeguridad;
	private ManagerAuditoria managerAuditoria;
	
	private LoginDTO loginDTO;
	
	public BeanLogin() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		loginDTO = new LoginDTO();
	}

	public String accederSistemaC() {
		try {
			loginDTO = managerSeguridad.accederSistemaCliente(cedula, contrasenia);
			return "catalogo.xhtml";
			
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
		return "";
	}

	//-----------GET Y SET----------
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}
}
